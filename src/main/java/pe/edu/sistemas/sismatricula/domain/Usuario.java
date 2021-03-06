package pe.edu.sistemas.sismatricula.domain;
// Generated 09-mar-2018 16:55:26 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", catalog = "modelomatriculafisi")
public class Usuario implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private Integer activo;
	private String codigo;
	private String apellidos;
	private String nombres;
	private String contrasena;

	public Usuario() {
	}

	public Usuario(String codigo, String apellidos, String nombres, String contrasena) {
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.contrasena = contrasena;
	}

	public Usuario(Integer activo, String codigo, String apellidos, String nombres, String contrasena) {
		this.activo = activo;
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.contrasena = contrasena;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_USUARIO", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "ACTIVO")
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "CODIGO", nullable = false)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "APELLIDOS", nullable = false)
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "NOMBRES", nullable = false)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "CONTRASENA", nullable = false)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
