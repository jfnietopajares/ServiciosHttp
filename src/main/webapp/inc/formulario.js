
/*
Inicialización de objetos de pantalla del formulario
    Diseño de tabla con campos y cabeceras
    Creación de constantes de los campos del formulario
    Creación de combos y su inicialización de valores por defecto
    Creación de eventos

*/
function doHazFormulario() {
    /*
        Creata datatable
    */
    var columnas = [
        { "data": "id" },
        { "data": "servicios" },
        { "data": "dato1" },
        { "data": "dato2" },
        { "data": "dato3" },
        { "data": "dato4" },
    ];
    var titulos = [
        { "title": "id", "targets": 0 },
        { "title": "servicios", "targets": 1 },
        { "title": "dato1", "targets": 2 },
        { "title": "dato2", "targets": 3 },
        { "title": "dato3", "targets": 4 },
        { "title": "dato4", "targets": 5 }
    ];
    var tabla = getTabla("#tabla", columnas, titulos);

    const $id = $("#id"),
        $centros = $("#centro"),
        $servicios = $("#servicios"),
        $dato1 = $("#dato1"),
        $dato2 = $("#dato2"),
        $dato3 = $("#dato3"),
        $dato4 = $("#dato4"),
        $motivo = $("#motivo"),
        $btnGraba = $("#btnGraba"),
        $btnBorra = $("#btnBorra"),
        $btnLimpia = $("#btnLimpia"),
        $accion = $("#accion");

    /*
    carga combos 
    */
    $.getJSON("../dao/centrodao.php", function (result) {
        $.each(result, function (id, value) {
            $("#centros").append('<option value="' + id + '">' + value + '</option>');
        });
        $("#centros option:selected").each(function () {
            centro = $(this).val();
            doActulizaComboServicios(centro);
            doActulizaTabla(centro);
        });
    });
    /*
        Eventos click de componentes
    */
    $('#tabla tbody').on('click', 'tr', function () {
        var data = tabla.row(this).data();
        getRegistro(data['id']);
    });

      $btnGraba.click(function () {
        $accion = "graba";
        doMandaFormulario();
      });
    
    $("#btnConsulta").click(function () {
            $("#accion").val("consulta");
            $.ajax({
                type: 'POST',
                url: 'formulario.php',
                data: $('#formulario').serialize(),
                success: function (respuesta) {
                    if (respuesta == 'ok') {
                        alert('enviado');
                    }
                    else {
                        alert('error');
                    }
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                doMuestraError("formulario.js", "btnConsulta.click ", jqXHR, textStatus, errorThrown);
            });
    });
    
    /*
    Eventos change
    */
    $("#centros").on('change', function () {
        $("#centros option:selected").each(function () {
            centro = $(this).val();
            doActulizaComboServicios(centro);
            doActulizaTabla(centro);
        });
    });
    $("#motivo").on('change', function () {
        if ($(this).val().length < 25) {
            alert(" hasta 25");
            $("#motivo").focus();
            return false;
        }
    });
}

function doMandaFormulario() {
    $respuesta.html("Cargando...");
    $.post("..dao/formulario.php", {
        id: $id.val(),
        centros: $centros.val(),
        servicios: $servicios.val(),
        dato1: $dato1,
        dato2: $dato2,
        dato3: $dato3,
        dato4: $dato4,
        motivo: $motivo,
        $accion: $accion,
    }, function (respuestaComoJson) {
        // La decodificamos
        let respuesta = JSON.parse(respuestaComoJson);
        $respuesta.html(respuesta);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        doMuestraError("formulario.js", "doMandaFormulario ", jqXHR, textStatus, errorThrown);
    });
}

function doActulizaComboServicios(centro) {
    $('#servicios').children().remove().end()
    $.getJSON("../dao/serviciosdao1.php?centro=" + centro, function (result) {
        $.each(result, function (id, value) {
            $("#servicios").append('<option value="' + value + '">' + value + '</option>');
        });
    })
        .fail(function (jqXHR, textStatus, errorThrown) {
            doMuestraError("formulario.js", "doActulizaComboServicios \n ../dao/serviciosdao1.php ? centro =" + centro, jqXHR, textStatus, errorThrown);
        });
}

function getRegistro(id) {
    $.ajax({
        type: "POST",
        url: "../dao/getRegistrodao.php?id=" + id,
        contentType: 'application/json; charset=utf-8',
        dataType: "json",

        beforeSend: function () {

        },
        success: function (result) {
            alert(result['servicios']);
            $('#id').val(result['id']);
            $('#centro').val(result['centro']);
            $('#servicios').val(result['servicios']);
            $('#dato1').val(result['dato1']);
            $('#dato2').val(result['dato2']);
            $('#dato3').val(result['dato3']);
            $('#dato4').val(result['dato4']);
            $('#motivo').val(result['motivo']);
            return;
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        doMuestraError("formulario.js", "getRegistro \n ./dao/getRegistrodao.php?id=" + id, jqXHR, textStatus, errorThrown);
    });
}


function doActulizaTabla(centro) {
    var tabla = $('#tabla').DataTable();

    var data = tabla.rows();
    data.each(function () {
        this.remove();
    });


    $.ajax({
        type: "POST",
        url: "../dao/formulario.php?accion=consulta&centro=" + centro,
        contentType: 'application/json; charset=utf-8',
        dataType: "json",

        beforeSend: function () {
            $respuesta.html("Cargando...");
        },
        success: function (result) {
            tabla.rows.add(result).draw();
            tabla.draw();
            $respuesta.html("");
            return;
        },
        error: function (xhr, status, error) {
            doMuestraError("formulario.js", "doActulizaTabla \n ../dao/formulario.php?accion=consulta&centro=" + centro
                , xhr, status, error);
        }
    });

    
}






