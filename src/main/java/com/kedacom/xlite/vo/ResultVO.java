package com.kedacom.xlite.vo;


/**
 * 响应 值对象.
 * @author: wangshuxuan 
 * @date: 2018年9月17日
 */

public class ResultVO<T> {
	
	private Integer status;    //// -99未登录 200成功 406失败
	private String description;
	private T data;

	public ResultVO() {
	}

	public ResultVO(Integer status) {
		this.status = status;
	}

	public ResultVO(Integer status, String description) {
		this.status = status;
		this.description = description;
	}

	public ResultVO(Integer status, T data) {
		this.status = status;
		this.data = data;
	}

	public ResultVO(Integer status, String description, T data) {
		this.status = status;
		this.description = description;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
