package com.stocks.user;

import java.util.List;

public class DataResponse {
    private boolean error;
    private String msg;
    private List<Countries>data;
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Countries> getData() {
		return data;
	}
	public void setData(List<Countries> data) {
		this.data = data;
	}
    
}
