package pe.edu.sistemas.sismatricula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismatricula.domain.Tramite;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Integer>{

	List<Tramite> findByAlumnoAlumnoCodigo(String alumnoCodigo);


	void findByidTramite(Integer tram);

}
