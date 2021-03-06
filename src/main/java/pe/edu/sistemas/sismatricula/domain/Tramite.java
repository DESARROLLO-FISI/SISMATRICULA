package pe.edu.sistemas.sismatricula.domain;
// Generated 09-mar-2018 16:55:26 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tramite generated by hbm2java
 */
@Entity
@Table(name = "tramite", catalog = "modelomatriculafisi")
public class Tramite implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer idTramite;
	private Alumno alumno;
	private Periodo periodoByTramitePeriodoIni;
	private Periodo periodoByTramitePeriodoFin;
	private String tramiteTipo;
	private Date tramiteFechaIni;
	private Date tramiteFechaFin;
	private String tramiteRd;

	public Tramite() {
	}

	public Tramite(Alumno alumno) {
		this.alumno = alumno;
	}

	public Tramite(Alumno alumno, Periodo periodoByTramitePeriodoIni, Periodo periodoByTramitePeriodoFin,
			String tramiteTipo, Date tramiteFechaIni, Date tramiteFechaFin, String tramiteRd) {
		this.alumno = alumno;
		this.periodoByTramitePeriodoIni = periodoByTramitePeriodoIni;
		this.periodoByTramitePeriodoFin = periodoByTramitePeriodoFin;
		this.tramiteTipo = tramiteTipo;
		this.tramiteFechaIni = tramiteFechaIni;
		this.tramiteFechaFin = tramiteFechaFin;
		this.tramiteRd = tramiteRd;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_TRAMITE", unique = true, nullable = false)
	public Integer getIdTramite() {
		return this.idTramite;
	}

	public void setIdTramite(Integer idTramite) {
		this.idTramite = idTramite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAMITE_ID_ALUMNO", nullable = false)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAMITE_PERIODO_INI")
	public Periodo getPeriodoByTramitePeriodoIni() {
		return this.periodoByTramitePeriodoIni;
	}

	public void setPeriodoByTramitePeriodoIni(Periodo periodoByTramitePeriodoIni) {
		this.periodoByTramitePeriodoIni = periodoByTramitePeriodoIni;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAMITE_PERIODO_FIN")
	public Periodo getPeriodoByTramitePeriodoFin() {
		return this.periodoByTramitePeriodoFin;
	}

	public void setPeriodoByTramitePeriodoFin(Periodo periodoByTramitePeriodoFin) {
		this.periodoByTramitePeriodoFin = periodoByTramitePeriodoFin;
	}

	@Column(name = "TRAMITE_TIPO", length = 15)
	public String getTramiteTipo() {
		return this.tramiteTipo;
	}

	public void setTramiteTipo(String tramiteTipo) {
		this.tramiteTipo = tramiteTipo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAMITE_FECHA_INI", length = 10)
	public Date getTramiteFechaIni() {
		return this.tramiteFechaIni;
	}

	public void setTramiteFechaIni(Date tramiteFechaIni) {
		this.tramiteFechaIni = tramiteFechaIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAMITE_FECHA_FIN", length = 10)
	public Date getTramiteFechaFin() {
		return this.tramiteFechaFin;
	}

	public void setTramiteFechaFin(Date tramiteFechaFin) {
		this.tramiteFechaFin = tramiteFechaFin;
	}

	@Column(name = "TRAMITE_RD", length = 45)
	public String getTramiteRd() {
		return this.tramiteRd;
	}

	public void setTramiteRd(String tramiteRd) {
		this.tramiteRd = tramiteRd;
	}

	@Override
	public String toString() {
		return "Tramite [idTramite=" + idTramite + ", alumno=" + alumno + ", periodoByTramitePeriodoIni="
				+ periodoByTramitePeriodoIni + ", periodoByTramitePeriodoFin=" + periodoByTramitePeriodoFin
				+ ", tramiteTipo=" + tramiteTipo + ", tramiteFechaIni=" + tramiteFechaIni + ", tramiteFechaFin="
				+ tramiteFechaFin + ", tramiteRd=" + tramiteRd + "]";
	}

}
