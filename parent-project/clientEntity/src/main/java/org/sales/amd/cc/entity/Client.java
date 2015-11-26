package org.sales.amd.cc.entity;

public class Client {
	private String name;
	private String enterprise;
	private String email;

	public Client() {
	}
	
	public Client(String name, String city, String email) {
		this.name = new String(name);
		this.enterprise = new String(city);
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", enterprise=" + enterprise
				+ ", email=" + email + "]";
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
