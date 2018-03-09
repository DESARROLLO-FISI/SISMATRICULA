package pe.edu.sistemas.sismatricula.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismatricula.domain.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer>{

	Periodo findByperiodoNombre(String periodonombre);
}
