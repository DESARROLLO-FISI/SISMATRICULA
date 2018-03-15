var string_ws;

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
	let PeriodoAusente = $("#periodoReactIni").val();
	let PeriodoRegreso = $("#periodoReactFin").val();
	let CodigoAlumno =$("#codInput2").val();
	let tipoTramite = $("#reactualizacionB").val();
	let InicPeriodo = $("#fechaPUltMatriculaReact").val();
	let FechaRegreso = $("#fechaRegresoReact").val();
	let RD = $("#RDInputReact").val();
	let tramite = new TramiteMF(PeriodoAusente,PeriodoRegreso,CodigoAlumno,tipoTramite,InicPeriodo,FechaRegreso,RD);
	return tramite;
}

function RegistrarObjRes(){
	let PeriodoAusente = $("#periodoResIni").val();
	let PeriodoRegreso = $("#periodoResFin").val();
	let CodigoAlumno =$("#codInput2").val();
	let tipoTramite = $("#reservaB").val();
	let InicPeriodo = $("#fechaPUltMatriculaRes").val();
	let FechaRegreso = $("#fechaRegresoRes").val();
	let RD = $("#RDInputRes").val();
	let tramite = new TramiteMF(PeriodoAusente,PeriodoRegreso,CodigoAlumno,tipoTramite,InicPeriodo,FechaRegreso,RD);
	return tramite;
}


$(document).ready(function() {
    $('#table').DataTable( {
      
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
    
    $('#table').css("background","white");
    
    
} );

function reactualizacion(){
	document.getElementById('reactualizacion').style.display = 'block';
	document.getElementById('reserva').style.display = 'none';
	}

function reserva(){
	document.getElementById('reserva').style.display = 'block';
	document.getElementById('reactualizacion').style.display = 'none';
	}

function sumar(){
	return 2;
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

function registrarTramite(objDPjson){
	$.ajax({
		 url: '/jsonDP',    // cambiar: url: /jsonDP para pruebas 
         type: 'POST', 
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: objDPjson,
         success: function(data){
        	 //console.log(data);        	 

         },
         error : function(xhr, status) {
             alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
         },		
	});
}
function check_text(input) {  
    if (input.validity.patternMismatch){  
        input.setCustomValidity("Debe ingresar solo NUMEROS");  
    }  
    else {  
        input.setCustomValidity("");  
    }                 
}

function enviarReactualizacion(){
	let ReactobjDP = RegistrarObjReact();
	let ReactobjDPjson = JSON.stringify(ReactobjDP);
	string_ws=ReactobjDPjson;
	
	$.ajax({
        url: '/confirmartramitereact',
        type: 'POST', 
        contentType: "application/json; charset=utf-8",


        dataType: "html",
        data: string_ws,

        success: function(data) {      
        	var response=$('<div/>').html(data);
        	var exito=response.find("#exito");
        	console.log("se entrego datoas");
          	console.log(data);
          	console.log("exito"+exito.text());
          	if(exito.text().length!=0){
          		console.log("exitox2x2x2");
          		$("#todo").html(data);
          	 }
          	else{
          		$("#ValidarRegistro").html(data);
          	}
    
        },
        error : function(xhr, status) {
            alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
        },
      });
}

function enviarReserva(){
	let ResobjDP = RegistrarObjRes();
	let ResobjDPjson = JSON.stringify(ResobjDP);
	string_ws=ResobjDPjson;
	
	$.ajax({
        url: '/confirmartramiteres',
        type: 'POST', 
        contentType: "application/json; charset=utf-8",


        dataType: "html",
        data: string_ws,

        success: function(data) {            	             	 
        	var response=$('<div/>').html(data);
        	var exito=response.find("#exito");
        	console.log("se entrego datoas");
          	console.log(data);
          	console.log("exito"+exito.text());
          	if(exito.text().length!=0){
          		console.log("exitox2x2x2");
          		$("#todo").html(data);
          	 }
          	else{
          		$("#ValidarRegistro").html(data);
          	}
    
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

