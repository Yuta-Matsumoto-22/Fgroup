package user;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 123456789;
	private String password;
	private String username;
	
	public User(String p,String n) {
		this.password = p;
		this.username = n;
	}
	
	public String getpassword() {
		return this.password;
	}
	
	public String getusername() {
		return this.username;
	}
	
}
