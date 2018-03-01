package pe.edu.sistemas.sismatricula.domain;
// Generated 27/02/2018 02:20:40 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Reserva generated by hbm2java
 */
@Entity
@Table(name = "reserva", catalog = "modelomatriculafisi")
public class Reserva implements java.io.Serializable {

	private int idReserva;
	private Periodo periodoByIdPeriodoRetorno;
	private Periodo periodoByIdPeriodoDejado;
	private Date fechaReserva;
	private Date fechaCancelacionReserva;

	public Reserva() {
	}

	public Reserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Reserva(int idReserva, Periodo periodoByIdPeriodoRetorno, Periodo periodoByIdPeriodoDejado,
			Date fechaReserva, Date fechaCancelacionReserva) {
		this.idReserva = idReserva;
		this.periodoByIdPeriodoRetorno = periodoByIdPeriodoRetorno;
		this.periodoByIdPeriodoDejado = periodoByIdPeriodoDejado;
		this.fechaReserva = fechaReserva;
		this.fechaCancelacionReserva = fechaCancelacionReserva;
	}

	@Id

	@Column(name = "ID_RESERVA", unique = true, nullable = false)
	public int getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERIODO_RETORNO")
	public Periodo getPeriodoByIdPeriodoRetorno() {
		return this.periodoByIdPeriodoRetorno;
	}

	public void setPeriodoByIdPeriodoRetorno(Periodo periodoByIdPeriodoRetorno) {
		this.periodoByIdPeriodoRetorno = periodoByIdPeriodoRetorno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERIODO_DEJADO")
	public Periodo getPeriodoByIdPeriodoDejado() {
		return this.periodoByIdPeriodoDejado;
	}

	public void setPeriodoByIdPeriodoDejado(Periodo periodoByIdPeriodoDejado) {
		this.periodoByIdPeriodoDejado = periodoByIdPeriodoDejado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_RESERVA", length = 10)
	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CANCELACION_RESERVA", length = 10)
	public Date getFechaCancelacionReserva() {
		return this.fechaCancelacionReserva;
	}

	public void setFechaCancelacionReserva(Date fechaCancelacionReserva) {
		this.fechaCancelacionReserva = fechaCancelacionReserva;
	}

}