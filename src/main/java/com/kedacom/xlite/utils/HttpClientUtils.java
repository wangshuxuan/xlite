package com.kedacom.xlite.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClientUtils
 * @author luocanfeng
 * @date 2016年12月13日
 */
public class HttpClientUtils {

	protected static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class.getName());
	protected static final String UTF8 = "UTF-8";
	protected static final int MAX_RETRY_COUNT = 5;

	/**
	 * RetryHandler，处理重试逻辑
	 */
	protected static final HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

		public boolean retryRequest(IOException ex, int executionCount, HttpContext context) {
			// Do not retry if over max retry count
			if (executionCount >= MAX_RETRY_COUNT) {
				return false;
			}
			if (ex instanceof InterruptedIOException // Timeout
					|| ex instanceof UnknownHostException // Unknown host
					|| ex instanceof ConnectTimeoutException // Connection
																// refused
					|| ex instanceof SSLException // SSL handshake exception
			) {
				return false;
			}
			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			if (!(request instanceof HttpEntityEnclosingRequest)) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};

	/**
	 * 处理HTTP GET请求
	 * @param url 请求的URL地址
	 */
	public static HttpResult doGet(String url) throws IOException {
		return doGet(url, null, null, false);
	}

	/**
	 * 处理HTTP GET请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 */
	public static <T> HttpResult doGet(String url, Map<String, T> params) throws IOException {
		return doGet(url, params, null, false);
	}

	/**
	 * 处理HTTP GET请求
	 * @param url 请求的URL地址
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static <T> HttpResult doGet(String url, String[] cookies) throws IOException {
		return doGet(url, null, cookies);
	}

	/**
	 * 处理HTTP GET请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static <T> HttpResult doGet(String url, Map<String, T> params, String[] cookies) throws IOException {
		return doGet(url, params, cookies, false);
	}

	/**
	 * 处理HTTP GET请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 * @param retry 请求未得到响应时是否允许重试；默认不重试；设置为是则重试5遍
	 */
	public static <T> HttpResult doGet(String url, Map<String, T> params, String[] cookies, boolean retry) throws IOException {
		CloseableHttpClient httpClient = retry ? HttpClients.custom().setRetryHandler(retryHandler).build() : HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(concatQueryString(url, params));
		httpGet.setHeader("Connection", "close");

		if (ArrayUtils.isNotEmpty(cookies)) { // set Cookies
			for (String cookie : cookies) {
				httpGet.addHeader("Cookie", cookie);
			}
		}

		CloseableHttpResponse response = httpClient.execute(httpGet);

		try {
			return processResponse(response, cookies);
		} finally {
			httpClient.close();
			response.close();
		}
	}

	/**
	 * 处理HTTP POST请求
	 * @param url 请求的URL地址
	 */
	public static HttpResult doPost(String url) throws IOException {
		return doPost(url, null, null);
	}

	/**
	 * 处理HTTP POST请求
	 * @param url 请求的URL地址
	 * @param params POST请求的请求参数，会以Form表单的方式进行传参
	 */
	public static <T> HttpResult doPost(String url, Map<String, T> params) throws IOException {
		return doPost(url, params, null, false);
	}

	/**
	 * 处理HTTP POST请求
	 * @param url 请求的URL地址
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static HttpResult doPost(String url, String[] cookies) throws IOException {
		return doPost(url, null, cookies, false);
	}

	/**
	 * 处理HTTP POST请求
	 * @param url 请求的URL地址
	 * @param params POST请求的请求参数，会以Form表单的方式进行传参
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static <T> HttpResult doPost(String url, Map<String, T> params, String[] cookies) throws IOException {
		return doPost(url, params, cookies, false);
	}

	/**
	 * 处理HTTP POST请求
	 * @param url 请求的URL地址
	 * @param params POST请求的请求参数，会以Form表单的方式进行传参
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
			 * @param retry 请求未得到响应时是否允许重试；默认不重试；设置为是则重试5遍
	 */
	public static <T> HttpResult doPost(String url, Map<String, T> params, String[] cookies, boolean retry) throws IOException {
		CloseableHttpClient httpClient = retry ? HttpClients.custom().setRetryHandler(retryHandler).build() : HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Connection", "close");

		if (ArrayUtils.isNotEmpty(cookies)) { // set Cookies
			for (String cookie : cookies) {
				httpPost.addHeader("Cookie", cookie);
			}
		}

		if (params != null && params.size() > 0) {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, T> item : params.entrySet()) {
				formParams.add(new BasicNameValuePair(item.getKey(), item.getValue().toString()));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, UTF8);
			httpPost.setEntity(formEntity);
		}

		CloseableHttpResponse response = httpClient.execute(httpPost);

		try {
			return processResponse(response, cookies);
		} finally {
			httpClient.close();
			response.close();
		}
	}


    /**
     * 处理HTTP POST请求
     * @param url 请求的URL地址
     * @param jsonParam POST请求的请求参数，会以APPLICATION/JSON的方式进行传参
     */
    public static <T> HttpResult doJsonPost(String url, String jsonParam, String[] cookies, boolean retry) throws IOException {
        CloseableHttpClient httpClient = retry ? HttpClients.custom().setRetryHandler(retryHandler).build() : HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Connection", "close");

        //if (ArrayUtils.isNotEmpty(cookies)) { // set Cookies
        //    for (String cookie : cookies) {
        //       httpPost.addHeader("Cookie", cookie);
        //    }
        //}

        //if (params != null && params.size() > 0) {
        //    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        //    for (Map.Entry<String, T> item : params.entrySet()) {
        //        formParams.add(new BasicNameValuePair(item.getKey(), item.getValue().toString()));
        //    }
        //    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, UTF8);
        //    httpPost.setEntity(formEntity);
        //}
        StringEntity entity = new StringEntity(jsonParam,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            return processResponse(response, cookies);
        } finally {
            httpClient.close();
            response.close();
        }
    }


    /**
	 * 处理HTTP DELETE请求
	 * @param url 请求的URL地址
	 */
	public static HttpResult doDelete(String url) throws IOException {
		return doDelete(url, null, null, false);
	}

	/**
	 * 处理HTTP DELETE请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 */
	public static <T> HttpResult doDelete(String url, Map<String, T> params) throws IOException {
		return doDelete(url, params, null, false);
	}

	/**
	 * 处理HTTP DELETE请求
	 * @param url 请求的URL地址
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static <T> HttpResult doDelete(String url, String[] cookies) throws IOException {
		return doDelete(url, null, cookies);
	}

	/**
	 * 处理HTTP DELETE请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 */
	public static <T> HttpResult doDelete(String url, Map<String, T> params, String[] cookies) throws IOException {
		return doDelete(url, params, cookies, false);
	}

	/**
	 * 处理HTTP DELETE请求
	 * @param url 请求的URL地址
	 * @param params GET请求的请求参数，会以URL传参的方式进行传递
	 * @param cookies 请求时需要带上（写入到请求头）的Cookies
	 * @param retry 请求未得到响应时是否允许重试；默认不重试；设置为是则重试5遍
	 */
	public static <T> HttpResult doDelete(String url, Map<String, T> params, String[] cookies, boolean retry) throws IOException {
		CloseableHttpClient httpClient = retry ? HttpClients.custom().setRetryHandler(retryHandler).build() : HttpClients.createDefault();
		HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
		// HttpDelete httpDelete = new HttpDelete(concatQueryString(url, params));
		httpDelete.setHeader("Connection", "close");

		if (ArrayUtils.isNotEmpty(cookies)) { // set Cookies
			for (String cookie : cookies) {
				httpDelete.addHeader("Cookie", cookie);
			}
		}

		if (params != null && params.size() > 0) {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, T> item : params.entrySet()) {
				formParams.add(new BasicNameValuePair(item.getKey(), item.getValue().toString()));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, UTF8);
			httpDelete.setEntity(formEntity);
		}

		CloseableHttpResponse response = httpClient.execute(httpDelete);

		try {
			return processResponse(response, cookies);
		} finally {
			httpClient.close();
			response.close();
		}
	}

	/**
	 * 图片上传
	 * @param url
	 * @param targetFile
	 * @param f
	 * @param contentType
	 * @param params
	 * @param cookies
	 * @param retry
	 */
	public static HttpResult doPostUpload(String url, String targetFile, File f, String contentType,
                                          Map<String,String> params, String[] cookies, boolean retry) throws IOException {

		CloseableHttpClient httpClient = retry ? HttpClients.custom().setRetryHandler(retryHandler).build()
				: HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Connection", "close");
		if (contentType == null) {
			contentType = "image/pjpeg";
		}
		if (ArrayUtils.isNotEmpty(cookies)) { // set Cookies
			for (String cookie : cookies) {
				httpPost.addHeader("Cookie", cookie);
			}
		}

		FileBody file = new FileBody(f, ContentType.create(contentType), targetFile);

		HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("uploadFile", file)
				/*.addTextBody("account_token", params.get("account_token")).addTextBody("x1", params.get("x1"))
				.addTextBody("y1", params.get("y1")).addTextBody("width", params.get("width"))
				.addTextBody("height", params.get("height"))*/
				.build();

		httpPost.setEntity(reqEntity);

		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus
					.SC_TEMPORARY_REDIRECT){
				Header header = response.getFirstHeader("location");
				if (header != null) {
					String location = header.getValue();
					if (location == null || location.equals("")) {
						location = "/";
					}
					return doGet(location, ArrayUtils.addAll(cookies, readCookies(response)));
				}
			}

			HttpEntity entity = response.getEntity();
			return new HttpResult(readEntity(entity), ArrayUtils.addAll(cookies, readCookies(response)));
		} finally {
			httpClient.close();
			response.close();
		}
	}

	public static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

		public static final String METHOD_NAME = "DELETE";

		public String getMethod() {
			return METHOD_NAME;
		}

		public HttpDeleteWithBody(final String uri) {
			super();
			setURI(URI.create(uri));
		}

		public HttpDeleteWithBody(final URI uri) {
			super();
			setURI(uri);
		}

		public HttpDeleteWithBody() {
			super();
		}
	}

	/**
	 * 拼接URL和查询参数
	 * @param url 请求的URL地址
	 * @param queryParams GET请求的请求参数，会以URL传参的方式进行传递
	 */
	protected static <T> String concatQueryString(String url, Map<String, T> queryParams) {
		String fullUrl = url;
		StringBuilder queryString = new StringBuilder();
		try {
			if (queryParams != null && queryParams.size() > 0) {
				for (Map.Entry<String, T> param : queryParams.entrySet()) {
					if (param.getValue() != null) {
						queryString.append("&").append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue().toString(), UTF8));
					}
				}

				if (fullUrl.contains("?")) {
					fullUrl = fullUrl + queryString.toString();
				} else {
					fullUrl = fullUrl + "?" + queryString.deleteCharAt(0).toString();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fullUrl;
	}

	/** 处理Response */
	protected static HttpResult processResponse(CloseableHttpResponse response, String[] cookies) throws IOException {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_SEE_OTHER
				|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
			Header header = response.getFirstHeader("location");
			if (header != null) {
				String location = header.getValue();
				if (location == null || location.equals("")) {
					location = "/";
				}
				return doGet(location, ArrayUtils.addAll(cookies, readCookies(response)));
			}
		}

		HttpEntity entity = response.getEntity();
		return new HttpResult(readEntity(entity), ArrayUtils.addAll(cookies, readCookies(response)));
	}

	/**
	 * 从Response对象中读取服务器返回的Cookies值列表
	 * @param response http请求返回的Response对象
	 */
	protected static String[] readCookies(HttpResponse response) {
		Header[] headers = response.getHeaders("Set-Cookie");
		if (headers != null) {
			String[] cookies = new String[headers.length];
			int i = 0;
			for (Header header : headers) {
				cookies[i++] = header.getValue();
			}
			return cookies;
		}
		return null;
	}

	/**
	 * 从Response entity中读取返回的超文本内容
	 * @param entity http请求返回的Response对象中的entity实体对象
	 */
	protected static String readEntity(HttpEntity entity) throws IOException {
		String result = null;
		if (entity != null) {
			long len = entity.getContentLength();
			if (len != -1 && len < 2048) {
				result = EntityUtils.toString(entity, UTF8);
			} else {
				InputStream inputStream = entity.getContent();
				StringBuilder content = new StringBuilder();
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
				try {
					String readLine = null;
					while ((readLine = bufferReader.readLine()) != null) {
						content.append(readLine);
					}
					result = content.toString();
				} finally {
					bufferReader.close();
					inputStream.close();
				}
			}
		}
		return result;
	}

}
