class TramiteMF{
	constructor (periodoByTramitePeriodoIni,periodoByTramitePeriodoFin,alumnoCodigo,tramiteTipo,tramiteFechaIni,tramiteFechaFin,tramiteRd){
	this.periodoByTramitePeriodoIni = periodoByTramitePeriodoIni;
	this.periodoByTramitePeriodoFin = periodoByTramitePeriodoFin;
	this.alumnoCodigo = alumnoCodigo;
	this.tramiteTipo=tramiteTipo;
	this.tramiteFechaIni = tramiteFechaIni;
	this.tramiteFechaFin=tramiteFechaFin;
	this.tramiteRd=tramiteRd;
	
	}
}


function getObjectDP(){
	let codigo = $("#codInput").val();
	return codigo;
}

function RegistrarObjReact(){
	let PeriodoAusente = $("#periodoDejaInputReact").val();
	let PeriodoRegreso = $("#periodoRegInputReact").val();
	let CodigoAlumno =$("#codInput2").val();
	let tipoTramite = $("#reactualizacionB").val();
	let InicPeriodo = $("#fechaPUltMatriculaReact").val();
	let FechaRegreso = $("#fechaRegresoReact").val();
	let RD = $("#RDInputReact").val();
	let tramite = new TramiteMF(PeriodoAusente,PeriodoRegreso,CodigoAlumno,tipoTramite,InicPeriodo,FechaRegreso,RD);
	return tramite;
}


$(document).ready(function() {
    $('#example').DataTable( {
      
      "language": {
        "emptyTable":     "No se hallaron resultados",
        "info":           "Mostrando _START_ - _END_ de _TOTAL_ entradas",
        "infoEmpty":      "Mostrando 0 - 0 de 0 entradas",
        "infoFiltered":   "(filtrado de un total de _MAX_ entradas)",
        "infoPostFix":    "",
        "thousands":      ",",
        "lengthMenu":     "Mostrar _MENU_ entradas",
        "loadingRecords": "Cargando...",
        "processing":     "Procesando...",
        "search":         "Buscar:",
        "paginate": {
            "first":      "Primero",
            "last":       "Ultimo",
            "next":       "Siguiente",
            "previous":   "Anterior"
        },
        "aria": {
            "sortAscending":  ": orden ascendente",
            "sortDescending": ": orden descendente"
        }

      },
      "bFilter":false
    } );
} );

function reactualizacion(){
	document.getElementById('reactualizacion').style.display = 'block';
	document.getElementById('reserva').style.display = 'none';
	}

function reserva(){
	document.getElementById('reserva').style.display = 'block';
	document.getElementById('reactualizacion').style.display = 'none';
	}


function obtenerYmostrarAlumno(objDPjson){
	$.ajax({
		 url: '/jsonDP2',    // cambiar: url: /jsonDP para pruebas 
         type: 'POST', 
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: objDPjson,
         success: function(data){
        	 //console.log(data);        	 
        	 $("#nameInput").val(data.nombreAlumno); //si esta vacio verificar en el back que no se envie nulo
        	 if(data.estado=="1"){
        		 document.getElementById('ActivoOpt').style.display = 'block';
            	 document.getElementById('InactivoOpt').style.display = 'none';
            	 document.getElementById('ReservaOpt').style.display = 'none';
        	 }
        	 else{
        		 if(data.estado=="2"){
        			 document.getElementById('ActivoOpt').style.display = 'none';
                	 document.getElementById('InactivoOpt').style.display = 'none';
                	 document.getElementById('ReservaOpt').style.display = 'block';
        		 }
        		 else{
        			 document.getElementById('ActivoOpt').style.display = 'none';
                	 document.getElementById('InactivoOpt').style.display = 'block';
                	 document.getElementById('ReservaOpt').style.display = 'none';
        		 } 
        	 }
        	 $("#codInput2").val(data.codigoAlumno);
        	 document.getElementById('encontrado').style.display = 'block';
        	 document.getElementById('noencontrado').style.display = 'none';
             document.getElementById('datosAlumno').style.display= 'block';
             document.getElementById('TramiteT').style.display= 'block';
             
         },
         error : function(xhr, status) {
             document.getElementById('noencontrado').style.display = 'block';
             document.getElementById('encontrado').style.display = 'none';
             $("#nameInput").val(null);
        	 $("#stateSelect").val(null)
        	 $("#codInput2").val(null);
        	 document.getElementById('ActivoOpt').style.display = 'none';
        	 document.getElementById('InactivoOpt').style.display = 'none';
        	 document.getElementById('ReservaOpt').style.display = 'none';
        	 document.getElementById('datosAlumno').style.display= 'none';
        	 document.getElementById('TramiteT').style.display= 'none';
         },		
	});
}

function registrarAlumnoReact(ReactobjDPjson){
	$.ajax({
		 url: '/jsonDP',    // cambiar: url: /jsonDP para pruebas 
         type: 'POST', 
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: ReactobjDPjson,
         success: function(data){
        	 //console.log(data);        	 
             
         },
         error : function(xhr, status) {
             alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
         },		
	});
}

$(document).ready(function(){
	$("#buscarCod").click(function(){
		console.log("hola");
		let objDP = getObjectDP();
		let objDPjson = JSON.stringify(objDP);
		console.log("Se envia:"+objDPjson);
		obtenerYmostrarAlumno(objDPjson);		
	});	
});

$(document).ready(function(){
	$("#RegistrarReact").click(function(){
		console.log("holax1");
		let ReactobjDP = RegistrarObjReact();
		console.log("holax2");
		let ReactobjDPjson = JSON.stringify(ReactobjDP);
		console.log("hola");
		registrarAlumnoReact(ReactobjDPjson);		
	});	
});