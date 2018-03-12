<<<<<<< HEAD
/**
class AlumnoModel {
		
		constructor(idAlumno,idPeriodo,alumnoCodigo,alumnoAppaterno,
			alumnoApmaterno,alumnoNombre,alumnoEstado,alumnoIngreso) {
		this.idAlumno = idAlumno;
		this.idPeriodo = idPeriodo;
		this.alumnoCodigo = alumnoCodigo;
		this.alumnoAppaterno = alumnoAppaterno;
		this.alumnoApmaterno = alumnoApmaterno;
		this.alumnoNombre = alumnoNombre;
		this.alumnoEstado = alumnoEstado;
		this.alumnoIngreso = alumnoIngreso;
	}
		
}**/

var json_ws;
var string_ws;


=======
var json_ws;
var string_ws;

>>>>>>> HenryJCM
	function convertir (event) {
	 
        loadBinaryFile(event,function(data){
        	
            var workbook = XLSX.read(data,{type:'binary'});
            
            var first_sheet_name = workbook.SheetNames[0];
<<<<<<< HEAD
=======
            
            document.getElementById("label-matricula").textContent = (document.getElementById("file-matricula").value).substring(12);
>>>>>>> HenryJCM

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
    
<<<<<<< HEAD
    
=======
>>>>>>> HenryJCM
    function enviarDataAlumnos(){
    	$.ajax({
             url: '/carga',
             type: 'POST', 
             contentType: "application/json; charset=utf-8",
             // el tipo de información que se espera de respuesta
<<<<<<< HEAD
             dataType: "json",
             // la información a enviar
             // (también es posible utilizar una cadena de datos)
             data: string_ws,
             timeout: 1000000,
             // código a ejecutar si la petición es satisfactoria;
             // la respuesta es pasada como argumento a la función
             success: function(data) {            	             	 
            	 console.log(data);
=======
             dataType: "html",
             // la información a enviar
             // (también es posible utilizar una cadena de datos)
             data: string_ws,
             // código a ejecutar si la petición es satisfactoria;
             // la respuesta es pasada como argumento a la función
             success: function(data) {            	             	 
            	 console.log("se entrego datos");
            	 $("#file-matricula").val("");
            	 $("#cargaExterna").html(data);
>>>>>>> HenryJCM
             },
             error : function(xhr, status) {
                 alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
             },
<<<<<<< HEAD
             
=======
>>>>>>> HenryJCM
           });
    }	
    	