/**
 * @author wangshuchun
 */
package com.kedacom.xlite.exception;


import com.kedacom.xlite.constants.ApiErrorConstants;

/**
 * @author wangshuchun
 * @date 2017年12月4日
 */
public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = -8136680376488716506L;
	
	private String errorCode;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ApiRequestException(String code) {
		this.setErrorCode(code);
	}
	
	@Override
	public String getMessage() {
		String errorCode = String.valueOf(this.getErrorCode());
		String message = ApiErrorConstants.ApiErrorMessage.get(errorCode);
		if (null != message) {
			return message;
		}else {
			return "未知错误代码："+this.getErrorCode();
		}
	}
}
