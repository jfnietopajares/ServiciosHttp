
/*
Inicialización de objetos de pantalla del formulario
    Diseño de tabla con campos y cabeceras
    Creación de constantes de los campos del formulario
    Creación de combos y su inicialización de valores por defecto
    Creación de eventos

*/

function doHazFormulariIngrsadoo() {
    /*
        Creata datatable
    */
    var columnas = [
        { "data": "inicio" },
        { "data": "numerohc" },
        { "data": "paciente" },
        { "data": "edad" },
        { "data": "numicu" },
        { "data": "servicio" },
        { "data": "observaciones" },
        {
            "render": function ( ) {
                return '<img src="../img/probeta.jpg"   width="15" height="15"/>';
            }
        },
        {
            "render": function ( ) {
                return '<img src="../img/probeta.jpg"   width="15" height="15"/>';
            }
        }
    ];
    var titulos = [
        { "title": "Llegada", width: "10px", "targets": 0 },
        { "title": "numerohc", width: "10px", "targets": 1 },
        { "title": "Paciente", width: "100px", "targets": 2 },
        { "title": "Edad",  width: "10px","targets": 3 },
        { "title": "Registro", width: "10px", "targets": 4 },
        { "title": "Serv", width: "10px", "targets": 5 },
        { "title": "Observa", width: "10px", "targets": 6 },
        { "title": "Labo", width: "10px", "targets": 7 },
        { "title": "Rx", width: "10px", "targets": 8 }
    ];

 

    var tabla = getTabla("#tablaIngresados", columnas, titulos);
    doActulizaTablUrgencias();
    
     /*
        Eventos click de componentes
    */
    /*
    Si hace click en la tabla de pacientes urgencias
    recuperea la fila al objeto data
    y recupera los datos
    */
    $('#tablaIngresados tbody').on('click', 'tr', function () {
        var data = tabla.row(this).data();
        var numerohc = data['numerohc'];
        alert(numerohc);
    });
}


function doActulizaTablUrgencias() {
    var tabla = $('#tablaIngresados').DataTable();
    var data = tabla.rows();
    data.each(function () {
        this.remove();
    });
    //  url: "http://10.36.65.10:8080/ServiciosHttp/His?llamada=ENURGENCIAS" ,
    $.ajax({
        type :"GET",
        url: "/ServiciosHttp/His?llamada=ENURGENCIAS" ,
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        beforeSend: function () {
            $respuesta.html("Cargando...");
        },
        success: function (result) {
           // tabla.rows.add(result).draw();
           // tabla.draw();
            $.each( result, function( key, value ) {
                tabla.row.add({
                    "inicio": value.inicio,
                    "numerohc": value.numerohc,
                    "paciente": value.apellidosNombre,
                    "edad": value.edad,
                    "numicu": value.numicu,
                    "servicio": value.servicio,
                    "observaciones": value.observaciones,
                    "labo": '/img/probeta/probeta.jpg',
                    "rx":  '/img/probeta/probeta.jpg'
                }).draw(false);
            });
            $respuesta.html("");
            return;
        },
        error: function (xhr, status, error) {
            doMuestraError("urgencias.js"
                , "doActulizaTablUrgencias \n http://localhost:8080/ServiciosHttp/His?llamada=ENURGENCIAS"
                , xhr, status, error);
        }
    });
}
