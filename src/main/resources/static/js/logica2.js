
class RegAlumno{
	constructor(codAlumno) {
		this.codAlumno = codAlumno;
	}
}

function obtenerRegistros(jsonObj){
	$.ajax({
		url: "/consulta",
		type: "POST",
		contentType:"application/json; chaset=utf-8",
		datatype: "json",
		data: jsonObj,
		success: function(data){
			$("#txtNombre").val(data.nombre);

			let tableData = data.listaProcAlumno;

			if(tableData !=null && $.isArray(tableData)){
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
