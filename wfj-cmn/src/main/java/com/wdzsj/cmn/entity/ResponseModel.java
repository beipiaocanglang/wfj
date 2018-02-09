package com.wdzsj.cmn.entity;

import java.io.Serializable;

import com.wdzsj.cmn.constant.Status;
/**
 * 服务器响应体模型
 * @author qy
 *
 * @param <T>
 */
public class ResponseModel<T> implements Serializable {

	protected static final long serialVersionUID = -5009847423429712499L;
	
	/**
	 * 状态码
	 */
	protected int status;

	/**
	 * 响应提示信息
	 */
	protected String message;

	/**
	 * 响应的实体
	 */
	protected T body;

	/**
	 * 请求时间戳
	 */
	protected long timestamp;

	/**
	 * 异常信息
	 */
	protected String exception;

	/**
	 * @param body
	 * @return
	 */
	protected static <T> ResponseModel<T> build(T body) {
		ResponseModel<T> response = new ResponseModel<T>();
		if(body != null)
			response.setBody(body);
		if(response.getTimestamp() == 0L)
			response.setTimestamp(System.currentTimeMillis());
		return response;
	}
	/**
	 * 创建ResponseModel
	 * @param body
	 * @param status
	 * @param msg
	 * @return
	 */
	public static <T> ResponseModel<T> build(T body, int status,
			String msg) {
		ResponseModel<T> response = build(body);
		response.setStatus(status);
		response.setMessage(msg);
		return response;
	}
	/**
	 * 创建服务器错误请求失败时的ResponseModel
	 * @param body
	 * @param errMsg
	 * @return
	 */
	public static <T> ResponseModel<T> build2ServerError(T body,
			String errMsg) {
		return build(body, Status.SERVER_ERROR, errMsg);
	}
	
	/**
	 * 创建请求成功时的ResponseModel
	 * @param body
	 * @param msg
	 * @return
	 */
	public static <T> ResponseModel<T> build2Success(T body, String msg) {
		return build(body, Status.SUCCESS, msg);
	}

	public T getBody() {
		return body;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message
				+ ", body=" + body + ", timestamp=" + timestamp
				+ ", exception=" + exception + "]";
	}
}
