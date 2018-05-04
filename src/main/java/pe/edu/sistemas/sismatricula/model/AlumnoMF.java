package pe.edu.sistemas.sismatricula.model;

public class AlumnoMF {
	private int idAlumno;
	private int idPeriodo;
	private int idTramite;
	private String codigoAlumno;
	private String nombreAlumno;
	private String apPaterno;
	private String apMaterno;
	private String estado;
	private String tramiteRD;
	private String periodoIn;
	private String periodoFin;
	private String fechaIn;
	private String fechaFin;
	
	
	
	public AlumnoMF(){
		
	}
	public AlumnoMF(int idAlumno, int idPeriodo, int idTramite, String codigoAlumno, String nombreAlumno,
			String apPaterno, String apMaterno, String estado, String tramiteRD, String periodoIn, String periodoFin,
			String fechaIn, String fechaFin) {
		super();
		this.idAlumno = idAlumno;
		this.idPeriodo = idPeriodo;
		this.idTramite = idTramite;
		this.codigoAlumno = codigoAlumno;
		this.nombreAlumno = nombreAlumno;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.estado = estado;
		this.tramiteRD = tramiteRD;
		this.periodoIn = periodoIn;
		this.periodoFin = periodoFin;
		this.fechaIn = fechaIn;
		this.fechaFin = fechaFin;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public int getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	public int getIdTramite() {
		return idTramite;
	}
	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}
	public String getCodigoAlumno() {
		return codigoAlumno;
	}
	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estadox) {
		if(estadox.equals("AC")){
			this.estado="1";
		}
		if(estadox.equals("RSV")){
			this.estado="2";
		}
		if(estadox.equals("INAC")){
			this.estado="0";
		}
	}
	public String getTramiteRD() {
		return tramiteRD;
	}
	public void setTramiteRD(String tramiteRD) {
		this.tramiteRD = tramiteRD;
	}
	public String getPeriodoIn() {
		return periodoIn;
	}
	public void setPeriodoIn(String periodoIn) {
		this.periodoIn = periodoIn;
	}
	public String getPeriodoFin() {
		return periodoFin;
	}
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	public String getFechaIn() {
		return fechaIn;
	}
	public void setFechaIn(String fechaIn) {
		this.fechaIn = fechaIn;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
