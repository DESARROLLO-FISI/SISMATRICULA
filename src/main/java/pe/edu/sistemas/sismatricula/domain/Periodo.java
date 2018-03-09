package pe.edu.sistemas.sismatricula.domain;
// Generated 09/03/2018 02:41:50 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Periodo generated by hbm2java
 */
@Entity
@Table(name = "periodo", catalog = "modelomatriculafisi")
public class Periodo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPeriodo;
	private String periodoNombre;
	private Date periodoFechaIni;
	private Date periodoFechaFin;
	private Integer periodoValor;
	private Integer periodoActual;
	private Set<Tramite> tramitesForTramitePeriodoIni = new HashSet<Tramite>(0);
	private Set<Alumno> alumnos = new HashSet<Alumno>(0);
	private Set<Tramite> tramitesForTramitePeriodoFin = new HashSet<Tramite>(0);

	public Periodo() {
	}

	public Periodo(String periodoNombre, Date periodoFechaIni, Date periodoFechaFin, Integer periodoValor,
			Integer periodoActual, Set<Tramite> tramitesForTramitePeriodoIni, Set<Alumno> alumnos,
			Set<Tramite> tramitesForTramitePeriodoFin) {
		this.periodoNombre = periodoNombre;
		this.periodoFechaIni = periodoFechaIni;
		this.periodoFechaFin = periodoFechaFin;
		this.periodoValor = periodoValor;
		this.periodoActual = periodoActual;
		this.tramitesForTramitePeriodoIni = tramitesForTramitePeriodoIni;
		this.alumnos = alumnos;
		this.tramitesForTramitePeriodoFin = tramitesForTramitePeriodoFin;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_PERIODO", unique = true, nullable = false)
	public Integer getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	@Column(name = "PERIODO_NOMBRE", length = 45)
	public String getPeriodoNombre() {
		return this.periodoNombre;
	}

	public void setPeriodoNombre(String periodoNombre) {
		this.periodoNombre = periodoNombre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PERIODO_FECHA_INI", length = 10)
	public Date getPeriodoFechaIni() {
		return this.periodoFechaIni;
	}

	public void setPeriodoFechaIni(Date periodoFechaIni) {
		this.periodoFechaIni = periodoFechaIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PERIODO_FECHA_FIN", length = 10)
	public Date getPeriodoFechaFin() {
		return this.periodoFechaFin;
	}

	public void setPeriodoFechaFin(Date periodoFechaFin) {
		this.periodoFechaFin = periodoFechaFin;
	}

	@Column(name = "PERIODO_VALOR")
	public Integer getPeriodoValor() {
		return this.periodoValor;
	}

	public void setPeriodoValor(Integer periodoValor) {
		this.periodoValor = periodoValor;
	}

	@Column(name = "PERIODO_ACTUAL")
	public Integer getPeriodoActual() {
		return this.periodoActual;
	}

	public void setPeriodoActual(Integer periodoActual) {
		this.periodoActual = periodoActual;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "periodoByTramitePeriodoIni")
	public Set<Tramite> getTramitesForTramitePeriodoIni() {
		return this.tramitesForTramitePeriodoIni;
	}

	public void setTramitesForTramitePeriodoIni(Set<Tramite> tramitesForTramitePeriodoIni) {
		this.tramitesForTramitePeriodoIni = tramitesForTramitePeriodoIni;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "periodo")
	public Set<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "periodoByTramitePeriodoFin")
	public Set<Tramite> getTramitesForTramitePeriodoFin() {
		return this.tramitesForTramitePeriodoFin;
	}

	public void setTramitesForTramitePeriodoFin(Set<Tramite> tramitesForTramitePeriodoFin) {
		this.tramitesForTramitePeriodoFin = tramitesForTramitePeriodoFin;
	}

}
