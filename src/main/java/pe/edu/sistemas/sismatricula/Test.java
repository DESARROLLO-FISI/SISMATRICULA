package pe.edu.sistemas.sismatricula;

import pe.edu.sistemas.sismatricula.controller.TareasController;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
import pe.edu.sistemas.sismatricula.model.RegAlumno;

public class Test {
	
	
	
	public static void main(String[] args) {
		RegAlumno alumno=new RegAlumno();
		alumno.setCodAlumno("16200002");
		new TareasController().consultarHistorialAlumno(alumno);
		System.out.println("Codigo:"+alumno.getCodAlumno()+
				"\nNombre:"+alumno.getNombre()+
				"\nPUsad: "+alumno.getPeriodUsados()+
				"\nPRest: "+alumno.getPeriodRestantes());
		
		System.out.println("Tramites:");
		for (ProcAlumno pa : alumno.getListaProcAlumno()) {
			System.out.println("Fecha Abandono: "+pa.getFechaAbandono()+
					"\nPeriodo Ultima Matricula: "+pa.getpUltMatricula()+
					"\nFecha Regreso: "+pa.getFechaRegreso()+
					"\nPeriodo Regreso Matricula: "+pa.getpRegMatricula()+
					"\nRD: "+pa.getRd()+
					"\nTipo Tramite: "+pa.getTramite());
		}
	}
}
