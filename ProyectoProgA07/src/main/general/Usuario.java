package main.general;

import java.util.Date;

public class Usuario {

	public enum Genero {
		MUJER, HOMBRE, NO_BINAR
	}

	private String nick;
	private String password;
	private Date fechaNacimiento;
	private Genero genero;

	
	public Usuario(String nick, String password, Date fechaNacimiento, Genero genero) {
		super();
		this.nick = nick;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
