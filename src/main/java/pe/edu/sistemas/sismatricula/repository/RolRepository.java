package pe.edu.sistemas.sismatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismatricula.domain.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	Rol findByRol(String rol);

}