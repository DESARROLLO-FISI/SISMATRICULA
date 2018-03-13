
class RegAlumno{
	constructor(codAlumno) {
		this.codAlumno = codAlumno;
	}
}

var codigo="";

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
								value.pUltMatricula,value.fechaAbandono,
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

function limpiar(){
	$("#txtNombre").text("no existe - nombre");
	$("#state").attr("style","display:none");
	$('#table').dataTable().fnClearTable();
	$("#div-clear").attr("style","display:none");
	$("#Inpt").val("");
}

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
	
	$('#limpiarDatos').click(function(){	
		limpiar();
	})
});
