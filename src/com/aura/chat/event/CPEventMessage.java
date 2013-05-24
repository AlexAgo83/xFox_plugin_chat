package com.aura.chat.event;

import com.aura.base.event.AbstractEvent;

public class CPEventMessage extends AbstractEvent {
	private String message;
	private String login;
	private long time;
	
	public CPEventMessage(int id) {
		super(id);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}