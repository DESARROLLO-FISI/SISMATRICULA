
/**
 * FUNCIONALIDAD: CARGA MASIVA
 */

	//Variables Globales
	var json_ws;
	var string_ws;

	/**
	 * @input excel
	 * @process convertir excel a string
	 * @output String - json con datos Alumnos
	 */
		function convertir (event) {

			loadBinaryFile(event,function(data){

				var workbook = XLSX.read(data,{type:'binary'});

				var first_sheet_name = workbook.SheetNames[0];

				document.getElementById("label-matricula").textContent = (document.getElementById("file-matricula").value).substring(12);

				var worksheet = workbook.Sheets[first_sheet_name];

				json_ws = XLSX.utils.sheet_to_json(worksheet);
            
				string_ws = JSON.stringify(json_ws);

			});
		}

		
	
	    
		function loadBinaryFile(path, success) {
            	var files = path.target.files;
            	var reader = new FileReader();
            	var name = files[0].name;
            	reader.onload = function(e) {
            		var data = e.target.result;
            		success(data);
            	};
            	reader.readAsBinaryString(files[0]);
		}

		/** @input String Datos Alumnos
		 * @process Guardar Alumnos de manera masiva
		 * @output Mensaje de error o exito
		 */
		function enviarDataAlumnos(){
			$("#cargaExterna").html(null);
			document.getElementById('loading').style.display = 'block';
			
			$.ajax({
				url: '/carga',
				type: 'POST',
				contentType: "application/json; charset=utf-8",
				// el tipo de información que se espera de respuesta
				dataType: "html",
				// la información a enviar
				// (también es posible utilizar una cadena de datos)
				data: string_ws,
				// código a ejecutar si la petición es satisfactoria;
				// la respuesta es pasada como argumento a la función
				success: function(data) {
					document.getElementById('loading').style.display = 'none';
					console.log("se entrego datos");
					$("#file-matricula").val("");
					$("#cargaExterna").html(data);
				},
				error : function(xhr, status) {
					alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
				},
			});
		}

/**
* FUNCIONALIDAD: CONSULTAR TRAMITES ALUMNOS
*/
		//Clase utilizada para enviar datos al controller
/*		class RegAlumno{
			constructor(codAlumno) {
				this.codAlumno = codAlumno;
			}
		}
		
		//Variable global
		var codigo="";
		
	*/

		$(document).ready(function() {
		   $('#dataTable').DataTable( {
			   
		      
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
		      "bFilter":false,
		      
		    } );
		   
		    $('#dataTable').css("background","white");
		} );
		
		/*
		function obtenerRegistros(jsonObj){
			$.ajax({
				url: "/consulta",
				type: "POST",
				contentType:"application/json; chaset=utf-8",
				datatype: "json",
				data: jsonObj,
				success: function(data){
					if(data.nombre!="no existe - nombre"){
						$("#div-clear").attr("style","display:block");
						$("#txtNombre").text(data.nombre);
						$("#resvUsada").text(data.periodResvUsados);
						$("#reactUsada").text(data.periodUsados);
						if(data.matriculaDisp){
							$("#divStMatric").removeClass("bg-danger text-white");
							$("#stMatric").text('matricula disponible');$("#divStMatric").addClass("bg-success text-white");}
						else{
							$("#divStMatric").removeClass("bg-success text-white");
							$("#stMatric").text('matricula no disponible');$("#divStMatric").addClass("bg-danger text-white");}
						
						
							$("#state").attr("style","display:block");
			
						let tableData = data.listaProcAlumno;
			
						if(tableData !=null && $.isArray(tableData)){
							$('#table').dataTable().fnClearTable();
							$.each(tableData,function(index,value){
								$('#table').dataTable().fnAddData(
									[
										value.rd,value.pUltMatricula,value.fechaAbandono,
										value.pRegMatricula,value.fechaRegreso,
										value.rd,value.tramite
									]
								);
							});
						}
					}
				},
		    error: function(xhr, status) {
		             alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
		    }
			});
		}
*/		$(document).ready(function(){
	
			$('#btnBuscar').click(function(){	
				console.log("limpiar tabla");
				$('#dataTable').dataTable().fnClearTable();
			});
});
		
		function limpiar(){
			$("#txtNombre").text("no existe - nombre");
			$("#state").attr("style","display:none");
			$('#table').dataTable().fnClearTable();
			$("#div-clear").attr("style","display:none");
			$("#Inpt").val("");
		}
/*
		function busqueda(){
			let codAlumno = $("#Inpt").val().trim();
			if(codAlumno!="" && codigo!=codAlumno){
				codigo=codAlumno;
				limpiar();
				$("#Inpt").val("");
				let obj = new RegAlumno(codAlumno);
				let jsonObj = JSON.stringify(obj);
				obtenerRegistros(jsonObj);
			}
		}

		$(document).ready(function(){
			$('#btnBuscar').click(function(){
				busqueda();
			});
			
			$('#Inpt').keyup(function(event){
				if(event.which==13){
					busqueda();
				}
			});
			
			
			$('#table tbody').on( 'click', 'button', function () {
		        var data = $('#table').DataTable().row( $(this).parents('tr') ).data();
		        alert( data[0] +"es el id. " );
		    } );
		});
		
		
		
/**
* FUNCIONALIDAD: REGISTRO DE TRAMITE
*/

		var string_rt;

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
		
		class ProcAlumno2{
			constructor (idTramite,PeriodoInic,FechaPeriodoInic,PeriodoFin,FechaPeriodoFin,RDCambio,TipoTramite,CodigoAlumno){
			this.idTramite = idTramite;
			this.PeriodoInic = PeriodoInic;
			this.FechaPeriodoInic = FechaPeriodoInic;
			this.PeriodoFin=PeriodoFin;
			this.FechaPeriodoFin = FechaPeriodoFin;
			this.RDCambio=RDCambio;
			this.TipoTramite=TipoTramite;
			this.CodigoAlumno=CodigoAlumno;
			
			}
		}


		function getObjectAlumnoMF(){
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
		
		function RegistrarCambiosobj(){
			let PeriodoInic = $("#pUltMatricula").val();
			let PeriodoFin = $("#pRegMatricula").val();
			let idTramite =  $("#tramiteId").val();
			let TipoTramite =  $("#tramite").val();
			let RDCambio =  $("#rdx").val();
			let FechaPeriodoInic =  $("#fechaAbandono").val();
			let FechaPeriodoFin=  $("#fechaRegreso").val();
			let CodigoAlumno=$("#Inpt").val();
			console.log(PeriodoInic);
			console.log(PeriodoFin);
			console.log(idTramite);
			console.log(RDCambio);
			console.log(TipoTramite);;
			console.log(FechaPeriodoInic);
			console.log("hik");
			
			let ProcAlumno= new ProcAlumno2(idTramite,PeriodoInic,FechaPeriodoInic,PeriodoFin,FechaPeriodoFin,RDCambio,TipoTramite,CodigoAlumno);
			return ProcAlumno;
		}


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

		function obtenerYmostrarAlumno(objAMFjson){
			$.ajax({
				 url: '/obtenerAlumno',    // cambiar: url: /jsonDP para pruebas 
		         type: 'POST', 
		         contentType: "application/json; charset=utf-8",
		         dataType: "json",
		         data: objAMFjson,
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
		
		function check_text(input) {  
		    if (input.validity.patternMismatch){  
		        input.setCustomValidity("Debe ingresar solo NUMEROS");  
		    }  
		    else {  
		        input.setCustomValidity("");  
		    }                 
		}

		function enviarReactualizacion(){
			let ReactobjAMF = RegistrarObjReact();
			let ReactobjAMFjson = JSON.stringify(ReactobjAMF);
			string_rt=ReactobjAMFjson;
			
			$.ajax({
		        url: '/confirmartramitereact',
		        type: 'POST', 
		        contentType: "application/json; charset=utf-8",


		        dataType: "html",
		        data: string_rt,

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
		
		function enviarCambios(){
			let Cambiosobj = RegistrarCambiosobj();
			let RegistrarCambiosjson = JSON.stringify(Cambiosobj);
			string_rt=RegistrarCambiosjson;
			console.log(string_rt);
			
			$.ajax({
		        url: '/actualizarTramite',
		        type: 'POST', 
		        contentType: "application/json; charset=utf-8",


		        dataType: "html",
		        data: string_rt,

		        success: function(data) {            	             	 
		        	
		          		$("#ValidarCambios").html(data);
		          	
		    
		        },
		        error : function(xhr, status) {
		            alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
		        },
		      });
			
		}

		function enviarReserva(){
			let ResobjAMF = RegistrarObjRes();
			let ResobjAMFjson = JSON.stringify(ResobjAMF);
			string_rt=ResobjAMFjson;
			
			$.ajax({
		        url: '/confirmartramiteres',
		        type: 'POST', 
		        contentType: "application/json; charset=utf-8",


		        dataType: "html",
		        data: string_rt,

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
				let objAMF = getObjectAlumnoMF();
				let objAMFjson = JSON.stringify(objAMF);
				console.log("Se envia:"+objAMFjson);
				obtenerYmostrarAlumno(objAMFjson);		
			});	
		});
		
		$('#codInput').keyup(function(event){
			if(event.which==13){
				let objAMF = getObjectAlumnoMF();
				let objAMFjson = JSON.stringify(objAMF);
				console.log("Se envia:"+objAMFjson);
				obtenerYmostrarAlumno(objAMFjson);
			}
		});
		
		
/**Datatables**/
		
 $(document).ready(function() {
    $('#dataTable').dataTable();
} );
