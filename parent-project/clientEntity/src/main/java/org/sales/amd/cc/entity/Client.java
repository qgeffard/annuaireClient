package org.sales.amd.cc.entity;

public class Client {
	private String name;
	private String entreprise;
	private String email;

	public Client() {
	}
	
	public Client(String name, String entreprise, String email) {
		this.name = new String(name);
		this.entreprise = new String(entreprise);
		this.email = email;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String enterprise) {
		this.entreprise = enterprise;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [name=");
		builder.append(name);
		builder.append(", entreprise=");
		builder.append(entreprise);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
	
	
}
