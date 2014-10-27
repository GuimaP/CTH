package model;

import java.io.Serializable;

public class UsuarioEmail implements Serializable {
	private String user;
	private String pass;
	private int port;
	private String host;
	private String hostReceive;
	private int portReceive;
	private boolean isSsl;
	
	public int getPortReceive() {
		return portReceive;
	}
	public void setPortReceive(int portReceive) {
		this.portReceive = portReceive;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHostReceive() {
		return hostReceive;
	}
	public void setHostReceive(String hostReceive) {
		this.hostReceive = hostReceive;
	}
	public boolean isSsl() {
		return isSsl;
	}
	public void setSsl(boolean isSsl) {
		this.isSsl = isSsl;
	}
}
