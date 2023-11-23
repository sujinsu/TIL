package com.example.demo.web.value;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.sql.Time;

/**
 * Client로 보내는 모든 결과 값은 MessageVo 로 Wrapping 되어 전달 된다.
 * 정상 적인 경우 GlobalResponseAdvice.java 에서 객체를 wrapping 한다.
 * exception 이 발생할 경우 GlobalControllerAdvice.java 에서 wrapping 한다.
 */

public class MessageVo implements Vo {

	@ApiModelProperty("HttpStatus")
	private Integer httpStatus;

	/**
	 * work database row or work Object count
	 */
	@ApiModelProperty("list count or affected row Count")
	private Integer rowCount;

	/**
	 * return contents
	 */
	@ApiModelProperty("list or 1row contents")
	private Object contents;

	/**
	 * error message
	 */
	@ApiModelProperty("exception message or server send user message")
	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "api call date")
	private Date date = new Date(System.currentTimeMillis());

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
	@ApiModelProperty(value = "api call time")
	private Time time = new Time(System.currentTimeMillis());

	private Long processTime;

	public MessageVo() {}

	public MessageVo(Integer httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public MessageVo(Integer httpStatus, Object contents) {
		this.httpStatus = httpStatus;
		this.contents = contents;
	}

	public MessageVo(Integer httpStatus, Object contents, String message) {
		this.httpStatus = httpStatus;
		this.contents = contents;
		this.message = message;
	}

	public MessageVo(Integer httpStatus, Integer rowCount, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.rowCount = rowCount;
	}

	public MessageVo(Integer httpStatus, Integer rowCount, Object contents) {
		this.httpStatus = httpStatus;
		this.rowCount = rowCount;
		this.contents = contents;
	}

	public MessageVo(Integer httpStatus, Integer rowCount, Object contents, String message) {
		this.httpStatus = httpStatus;
		this.rowCount = rowCount;
		this.contents = contents;
		this.message = message;
	}

	public MessageVo(int value, Object response) {
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Object getContents() {
		return contents;
	}

	public void setContents(Object contents) {
		this.contents = contents;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}
}