package main.mod;

public class Niveles {

	Integer id;
	Integer nivel;
	Integer juegoId;

	public Niveles(Integer id, Integer nivel, Integer juegoId) {
		this.id = id;
		this.nivel = nivel;
		this.juegoId = juegoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getJuegoId() {
		return juegoId;
	}

	public void setJuegoId(Integer juegoId) {
		this.juegoId = juegoId;
	}

}
