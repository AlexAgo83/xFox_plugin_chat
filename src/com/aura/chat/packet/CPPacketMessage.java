package com.aura.chat.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aura.base.Aura;
import com.aura.base.packet.AbstractPacket;
import com.aura.base.utils.AuraLogger;

public class CPPacketMessage extends AbstractPacket {
	private String message;
	private String login;
	private long time;
	
	public CPPacketMessage(int id) {
		super(id);
		
		this.login = "";
		this.time = 0l;
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
	
	@Override
	protected boolean implWrite(Aura aura, DataOutputStream output) {
		try {
			output.writeUTF(getLogin() != null ? getLogin() : "");
			output.flush();
			output.writeUTF(getMessage());
			output.flush();
			output.writeLong(getTime());
			output.flush();
		} catch (IOException e) {
			AuraLogger.severe(aura.getSide(), "Echec de l'�criture du message "
					+ "login: " + getLogin()
					+ "message: " + getMessage()
					+ "time: " + getTime()
					+ ", sur le stream du paquet ["
					+ this.getClass() + "].", e);
			return false;
		}
		return true;
	}
	@Override
	protected boolean implRead(Aura aura, DataInputStream input) {
		try {
			setLogin(input.readUTF());
			setMessage(input.readUTF());
			setTime(input.readLong());
		} catch (IOException e) {
			AuraLogger.severe(aura.getSide(), "Echec de l'�criture du message sur le stream du paquet ["
					+ this.getClass() + "].", e);
			return false;
		}
		return true;
	}
	@Override
	public boolean isValid() {
		return getMessage() != null;
	}
}
