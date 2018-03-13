package pe.edu.sistemas.sismatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismatricula.domain.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer>{

	public abstract Periodo findFirstByOrderByIdPeriodoDesc();
	
	public abstract Periodo findByPeriodoNombre(String periodonombre);

	public abstract Periodo findByPeriodoActual(Integer estado);

}
