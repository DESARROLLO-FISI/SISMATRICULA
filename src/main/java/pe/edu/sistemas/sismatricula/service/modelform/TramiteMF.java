package pe.edu.sistemas.sismatricula.service.modelform;

import java.util.Date;


public class TramiteMF {
	private String periodoByTramitePeriodoIni;
	private String periodoByTramitePeriodoFin;
	private String alumnoCodigo;
	private String tramiteTipo;
	private Date tramiteFechaIni;
	private Date tramiteFechaFin;
	private String tramiteRd;
	
	TramiteMF(){
		
	}
	
	
	public TramiteMF(String periodoByTramitePeriodoIni, String periodoByTramitePeriodoFin, String alumnoCodigo,
			String tramiteTipo, Date tramiteFechaIni, Date tramiteFechaFin, String tramiteRd) {
		super();
		this.periodoByTramitePeriodoIni = periodoByTramitePeriodoIni;
		this.periodoByTramitePeriodoFin = periodoByTramitePeriodoFin;
		this.alumnoCodigo = alumnoCodigo;
		this.tramiteTipo = tramiteTipo;
		this.tramiteFechaIni = tramiteFechaIni;
		this.tramiteFechaFin = tramiteFechaFin;
		this.tramiteRd = tramiteRd;
	}


	public String getPeriodoByTramitePeriodoIni() {
		return periodoByTramitePeriodoIni;
	}
	public void setPeriodoByTramitePeriodoIni(String periodoByTramitePeriodoIni) {
		this.periodoByTramitePeriodoIni = periodoByTramitePeriodoIni;
	}
	public String getPeriodoByTramitePeriodoFin() {
		return periodoByTramitePeriodoFin;
	}
	public void setPeriodoByTramitePeriodoFin(String periodoByTramitePeriodoFin) {
		this.periodoByTramitePeriodoFin = periodoByTramitePeriodoFin;
	}
	public String getAlumnoCodigo() {
		return alumnoCodigo;
	}
	public void setAlumnoCodigo(String alumnoCodigo) {
		this.alumnoCodigo = alumnoCodigo;
	}
	public String getTramiteTipo() {
		return tramiteTipo;
	}
	public void setTramiteTipo(String tramiteTipo) {
		this.tramiteTipo = tramiteTipo;
	}
	public Date getTramiteFechaIni() {
		return tramiteFechaIni;
	}
	public void setTramiteFechaIni(Date tramiteFechaIni) {
		this.tramiteFechaIni = tramiteFechaIni;
	}
	public Date getTramiteFechaFin() {
		return tramiteFechaFin;
	}
	public void setTramiteFechaFin(Date tramiteFechaFin) {
		this.tramiteFechaFin = tramiteFechaFin;
	}
	public String getTramiteRd() {
		return tramiteRd;
	}
	public void setTramiteRd(String tramiteRd) {
		this.tramiteRd = tramiteRd;
	}
}
