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
