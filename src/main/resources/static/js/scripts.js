
/**
 * FUNCIONALIDAD: CARGA MASIVA
 */

	//Variables Globales
	var json_ws;
	var string_ws;
	var idModal;

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
				url: '/sismatricula/carga',
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

		$(document).ready(function(){

			$('#btnBuscar').click(function(){
				console.log("limpiar tabla");
				$('#dataTable').dataTable().fnClearTable();
			});

			$('div.modal.fade.out').on('shown.bs.modal',function(){
				idModal=$(this).attr('id');
				console.log(idModal);
			});
		});

		function limpiar(){
			$("#txtNombre").text("no existe - nombre");
			$("#state").attr("style","display:none");
			$('#table').dataTable().fnClearTable();
			$("#div-clear").attr("style","display:none");
			$("#Inpt").val("");
		}

		function deleteTramite(){
			let idTramite = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-header > div > .tramiteId')[0].value;
			let json_data = idTramite;
			$.ajax({
				url:"/sismatricula/deleteTramite",
				type:'POST',
				contentType:"application/json ;charset=utf-8",
				dataType:'text',
				data:json_data,
				success:function (data){
					location.reload(true);
				},
				error: function(xhr, status) {
		      alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
		    }
			});
		}


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
			constructor (tramiteId,pUltMatricula,fechaAbandono,pRegMatricula,fechaRegreso,rd,tramite,codalumno){
			this.tramiteId = tramiteId;
			this.pUltMatricula = pUltMatricula;
			this.fechaAbandono = fechaAbandono;
			this.pRegMatricula=pRegMatricula;
			this.fechaRegreso = fechaRegreso;
			this.rd=rd;
			this.tramite=tramite;
			this.codalumno=codalumno;

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

			 let periodoInic = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > .pUltMatricula')[0].value;
			 let periodoFin = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > .pRegMatricula')[0].value;
			 let idTramite = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > .tramiteId')[0].value;
			 let fechaPeriodoInic = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > .fechaAbandono')[0].value;
			 let fechaPeriodoFin = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div >.fechaRegreso')[0].value;
			 let rdCambio = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > .rdx')[0].value;
			 let tipoTramite = document.querySelectorAll( "#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > .tramite')[0].value;
			 let codigoAlumno=$("#Inpt").val();



		    console.log("pUltMatricula: " + periodoInic);
		    console.log("pRegMatricula: " + periodoFin);
		    console.log("idTramite: " + idTramite);
		    console.log("FechaInic: " + fechaPeriodoInic);
		    console.log("FechaFin: " + fechaPeriodoFin);
		    console.log("rdCambio: " + rdCambio);
		    console.log("tipoTramite: " + tipoTramite);
		    console.log("codigoAlumno: " + codigoAlumno);
			console.log("**********************");


			let ProcAlumno= new ProcAlumno2(idTramite,periodoInic,fechaPeriodoInic,periodoFin,fechaPeriodoFin,rdCambio,tipoTramite,codigoAlumno);
			return ProcAlumno;
		}


		function reactualizacion(){
			document.getElementById('reactualizacion').style.display = 'block';
			document.getElementById('reserva').style.display = 'none';
			document.getElementById('ValidarRegistro').style.display='none';
			}

		function reserva(){
			document.getElementById('reserva').style.display = 'block';
			document.getElementById('reactualizacion').style.display = 'none';
			document.getElementById('ValidarRegistro').style.display='none';
			}

		function sumar(){
			return 2;
		}

		function obtenerYmostrarAlumno(objAMFjson){
			$.ajax({
				 url: '/sismatricula/obtenerAlumno',    // cambiar: url: /jsonDP para pruebas
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

		            string_rt = null;
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
			document.getElementById('enviarReact').style.display='none';
			document.getElementById('ValidarRegistro').style.display ='block';
			$("#ValidarRegistro").html(null);
			let ReactobjAMF = RegistrarObjReact();
			let ReactobjAMFjson = JSON.stringify(ReactobjAMF);
			string_rt=ReactobjAMFjson;

			$.ajax({
		        url: '/sismatricula/confirmartramitereact',
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
		          		document.getElementById('enviarReact').style.display ='block';
		          		$("#ValidarRegistro").html(data);
		          	}
		          	string_rt = null;
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
			console.log("chau");

			$.ajax({
		        url: '/sismatricula/actualizarTramite',
		        type: 'POST',
		        contentType: "application/json; charset=utf-8",


		        dataType: "html",
		        data: string_rt,

		        success: function(data) {

		        	//criterio de busqueda
		        	console.log("entroo");
		        	var response=$('<div/>').html(data);
		        	var exito=response.find("#btnConfirma");
		        	console.log("se entrego datoas");
		          	console.log(data);
		          	console.log("exito"+exito.text());
		          	if(exito.text().length!=0){
		          		console.log("exitox2x2x2");
		          		$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > #ValidarCambios').html(data);
		          		$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > #confirma').hide();
		          		$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-header > #spancerrar').hide();
		          		/*document.getElementById('confirma').style.display = 'none';
		          		document.getElementById('spancerrar').style.display = 'none';*/
		          	 }
		          	else{
		          		$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > #ValidarCambios').html(data);
		          	}

		          	string_rt = null;

		        },
		        error : function(xhr, status) {
		            alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
		        },
		      });

		}
		function cambiarTramite() {
		    var option_value = $("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > #tramite')[0].value;
		    console.log(option_value);
		    if (option_value == "Reactualizacion") {
		    	$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > #labelReact').text("Periodo Ultima Matricula:");
		    	$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > #labelRes').text("Periodo Ultima Matricula:");
		    }
		    else{
			    if (option_value == "Reserva") {
			    	$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > #labelRes').text("Periodo Inicio Reserva:");
			    	$("#" + idModal + ' > .modal-dialog > .modal-content > .modal-body > form > fieldset > div > div > div > #labelReact').text("Periodo Inicio Reserva:");

			    }
		    }
		}

		function enviarReserva(){
			document.getElementById('enviarReserva').style.display ='none';
			document.getElementById('ValidarRegistro').style.display ='block';
			$("#ValidarRegistro").html(null);
			let ResobjAMF = RegistrarObjRes();
			let ResobjAMFjson = JSON.stringify(ResobjAMF);
			string_rt=ResobjAMFjson;

			$.ajax({
		        url: '/sismatricula/confirmartramiteres',
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
		          		document.getElementById('enviarReserva').style.display ='block';
		          		$("#ValidarRegistro").html(data);
		          	}
		        string_rt = null;
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

		$(document).ready(function(){
			$('div.modal.fade.in').on('shown.bs.modal', function(){
			    idModal = $(this).attr('id');
			    console.log("Seleccionado modal: " + idModal);
			});
		});


/**Datatables**/

 $(document).ready(function() {
    $('#dataTable').dataTable();
} );
