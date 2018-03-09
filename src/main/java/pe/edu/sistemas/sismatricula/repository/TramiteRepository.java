package pe.edu.sistemas.sismatricula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.sistemas.sismatricula.domain.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Integer>{
	List<Tramite> findByAlumnoAlumnoCodigo(String alumnoCodigo);
}
