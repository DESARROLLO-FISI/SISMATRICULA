
class RegAlumno{
	constructor(codAlumno) {
		this.codAlumno = codAlumno;
	}
}

$("input[placeholder]").each(function () {
    $(this).attr('size', $(this).attr('placeholder').length);
});

function obtenerRegistros(jsonObj){
	$.ajax({
		url: "/consulta",
		type: "POST",
		contentType:"application/json; chaset=utf-8",
		datatype: "json",
		data: jsonObj,
		success: function(data){
			
			$("#txtNombre").text(data.nombre);
			$("#resvUsada").text(data.periodResvUsados);
			$("#reactUsada").text(data.periodUsados);
			if(data.matriculaDisp)
				$("#stMatric").text('matricula disponible');
			else
				$("#stMatric").text('matricula no disponible');
			$("#state").attr("style","display:block");
			$("#divStMatric").addClass("bg-success text-white");

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
		},
    error: function(xhr, status) {
             alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
    }
	});
}

$(document).ready(function(){
	$('#btnBuscar').click(function(){
		let codAlumno = $("#codInput").val().trim();
		let obj = new RegAlumno(codAlumno);
		let jsonObj = JSON.stringify(obj);
		obtenerRegistros(jsonObj);
	});
});
