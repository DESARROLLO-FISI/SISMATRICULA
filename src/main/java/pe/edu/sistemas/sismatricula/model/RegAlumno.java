package pe.edu.sistemas.sismatricula.model;

public class RegAlumno {
	
	private String codAlumno;
	private String nombre;
	private int periodRestantes;
	private int periodUsados;
	private boolean matriculaDisp;
	private int periodResvRestantes;
	private int periodResvUsados;
	private boolean reservaDisp;
	private boolean reactDisp;
	
	public RegAlumno(){}
	
	public RegAlumno(String codAlumno, String nombre, int periodRestantes, int periodUsados, boolean matriculaDisp, boolean reservaDisp, boolean reservaReact) {
		super();
		this.codAlumno = codAlumno;
		this.nombre = nombre;
		this.periodRestantes = periodRestantes;
		this.periodUsados = periodUsados;
		this.matriculaDisp = matriculaDisp;
	}
	
	public String getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPeriodRestantes() {
		return periodRestantes;
	}

	public void setPeriodRestantes(int periodRestantes) {
		this.periodRestantes = periodRestantes;
	}

	public int getPeriodUsados() {
		return periodUsados;
	}

	public void setPeriodUsados(int periodUsados) {
		this.periodUsados = periodUsados;
	}

	public boolean isMatriculaDisp() {
		return matriculaDisp;
	}

	public void setMatriculaDisp(boolean matriculaDisp) {
		this.matriculaDisp = matriculaDisp;
	}

	public int getPeriodResvRestantes() {
		return periodResvRestantes;
	}

	public void setPeriodResvRestantes(int periodResvRestantes) {
		this.periodResvRestantes = periodResvRestantes;
	}

	public int getPeriodResvUsados() {
		return periodResvUsados;
	}

	public void setPeriodResvUsados(int periodResvUsados) {
		this.periodResvUsados = periodResvUsados;
	}

	@Override
	public String toString() {
		return "RegAlumno [codAlumno=" + codAlumno + ", nombre=" + nombre + ", periodRestantes=" + periodRestantes
				+ ", periodUsados=" + periodUsados + ", matriculaDisp=" + matriculaDisp + ", periodResvRestantes="
				+ periodResvRestantes + ", periodResvUsados=" + periodResvUsados + "]";
	}

	public boolean isReactDisp() {
		return reactDisp;
	}

	public void setReactDisp(boolean reactDisp) {
		this.reactDisp = reactDisp;
	}

	public boolean isReservaDisp() {
		return reservaDisp;
	}

	public void setReservaDisp(boolean reservaDisp) {
		this.reservaDisp = reservaDisp;
	}
}