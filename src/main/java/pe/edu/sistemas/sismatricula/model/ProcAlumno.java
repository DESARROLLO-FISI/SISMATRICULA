package pe.edu.sistemas.sismatricula.model;

public class ProcAlumno {
	
	private Integer tramiteId;
	private String pUltMatricula;
	private String fechaAbandono;
	private String pRegMatricula;
	private String fechaRegreso;
	private String rd;
	private String tramite;
	
	public ProcAlumno() {}
	
	public ProcAlumno(Integer tramiteId, String pUltMatricula, String fechaAbandono, String pRegMatricula, String fechaRegreso, String rd,
			String tramite) {
		super();
		this.tramiteId = tramiteId;
		this.pUltMatricula = pUltMatricula;
		this.fechaAbandono = fechaAbandono;
		this.pRegMatricula = pRegMatricula;
		this.fechaRegreso = fechaRegreso;
		this.rd = rd;
		this.tramite = tramite;
	}

	public String getpUltMatricula() {
		return pUltMatricula;
	}

	public void setpUltMatricula(String pUltMatricula) {
		this.pUltMatricula = pUltMatricula;
	}

	public String getFechaAbandono() {
		return fechaAbandono;
	}

	public void setFechaAbandono(String fechaAbandono) {
		this.fechaAbandono = fechaAbandono;
	}

	public String getpRegMatricula() {
		return pRegMatricula;
	}

	public void setpRegMatricula(String pRegMatricula) {
		this.pRegMatricula = pRegMatricula;
	}

	public String getFechaRegreso() {
		return fechaRegreso;
	}

	public void setFechaRegreso(String fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public Integer getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Integer tramiteId) {
		this.tramiteId = tramiteId;
	}
	
	
	
}
