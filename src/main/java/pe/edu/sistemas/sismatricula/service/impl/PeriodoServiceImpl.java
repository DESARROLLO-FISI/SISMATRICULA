package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.service.PeriodoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.repository.PeriodoRepository;

@Service
public class PeriodoServiceImpl implements PeriodoService{

	@Autowired
	protected PeriodoRepository periodoRepository;

	Periodo p;
	


	@Override
	public Periodo buscarPeriodo(String periodo){
		try{
			p=periodoRepository.findByPeriodoNombre(periodo);
			return p;

		}
		catch(Exception e){
			System.out.println("Error buscando el periodo Inicial");
			return null;
		}


	}

	@Override
	public Periodo obtenerUltimoPeriodo() {
		return periodoRepository.findFirstByOrderByIdPeriodoDesc();

	}

	@Override
	public Periodo obtenerPeriodoActual() {
		return periodoRepository.findByPeriodoActual(1);
	}

	@Override
	public List<Periodo> listarperiodos() {
		List<Periodo> listaPeriodos = periodoRepository.findAll();
		for(Periodo x:listaPeriodos){
			System.out.println(x.getPeriodoNombre());
		}
		return listaPeriodos;
	}

	@Override
	public List<String> obtenerNombresPeriodos(List<Periodo> periodo) {
		List<String> nombres = new ArrayList<>();		
		for(int i=periodo.size()-1;i>=0;i--)
			nombres.add(periodo.get(i).getPeriodoNombre());
		return nombres;
	}

	@Override
	public List<Periodo> listarperiodosini() {
		int n;
		int encontrado=0;
		List<Periodo> listaPeriodosIni = periodoRepository.findAll();
		List<Periodo> listafiltrada= new ArrayList<>();
		n=0;
		while(encontrado !=1 && n<=listaPeriodosIni.size()-1){
			if(listaPeriodosIni.get(n).getPeriodoActual()==1){
				listafiltrada.add(listaPeriodosIni.get(n));
				encontrado=1;
			}
			else{
			listafiltrada.add(listaPeriodosIni.get(n));
			n=n+1;
			}
		}
		return listafiltrada;
	}

}
