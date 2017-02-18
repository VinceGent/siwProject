package elements;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1804544041351509691L;
	private int id;
	private String username;
	private String email;
	private String password;

	public User() {
	}

	public User(String name, String email, String password) {
		this.setUsername(name);
		this.setEmail(email); 
		this.setPassword(password);
	}

	public User(int id,String name, String email, String password){
		this.setId(id);
		this.setUsername(name);
		this.setEmail(email); 
		this.setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
