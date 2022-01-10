package main.mod;

import java.util.Date;

public class Participa {
	
	private Integer idParticipacion;
	private String nick;
	private Integer nivel;
	private Integer idJuego;
	private Integer puntuacion;
	private Date fecha;
	public Participa(Integer idParticipacion, String nick, Integer nivel, Integer idJuego, Integer puntuacion, Date fecha) {
		this.idParticipacion = idParticipacion;
		this.nick = nick;
		this.nivel = nivel;
		this.idJuego = idJuego;
		this.puntuacion = puntuacion;
		this.fecha = fecha;
	}
	public Integer getIdParticipacion() {
		return idParticipacion;
	}
	public void setIdParticipacion(Integer idParticipacion) {
		this.idParticipacion = idParticipacion;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getnivel() {
		return nivel;
	}
	public void setnivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}
