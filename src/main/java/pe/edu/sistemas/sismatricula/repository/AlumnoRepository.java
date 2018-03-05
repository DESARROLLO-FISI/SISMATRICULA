package pe.edu.sistemas.sismatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.sistemas.sismatricula.domain.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}
