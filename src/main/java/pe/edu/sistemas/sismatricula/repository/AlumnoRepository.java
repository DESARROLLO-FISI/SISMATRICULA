package pe.edu.sistemas.sismatricula.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismatricula.domain.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	public abstract Alumno findByAlumnoCodigo(String codigo);

	public abstract Boolean existsByAlumnoCodigo(String codigo);
}
