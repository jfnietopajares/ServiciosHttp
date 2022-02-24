/*
Ficheros: index.js
Autor: Juan Nieto
Fecha: 18-11-2021
Descripción:  Funciones y métosdos js y jquery  asociados a index.html
Version:
Comentarios y cambios:
Gestión de usuario.    getUsuario.php recupera el objeto usuario de la sesión de php. Si es nulo muestra formulario de login 
en la barrar superior de navegación, si hay usuario registrado muestra dni.

La pantalla index.html del sitio web, carga de forma dinámica (jquery) el códgio html de las páginas referenciadas 
en el menú (evento onclick de cada id del menú)


*/



/*
Muestra una alerta con los datos de un error ajax generado.
Párametros:
fichero: nombre del fichero java scritp que contiene el método o la gunción

metodo: nombre de la función o método que lanza el error incluidos los parámetros de llamada si tiene

jqXHR: es un objeto jqXHR que contiene todos los datos de la solicitud Ajax realizada, incluyendo la propiedad jqXHR.status que contiene, entre otros posibles, el código de estado HTTP de la respuesta.

textStatus: texto que describe el tipo de error, que puede ser, además de null, «abort», «timeout», «No Transport» o «parseerror».

errorThrown: si hay un error HTTP, errorThrown contiene el texto de la cabecera HTTP de estado. Por ejemplo, para un error HTTP 404, errorThrown es «Not found»; para error un HTTP 500 es «Internal Server Error».

*/

var dni ='06384936K';
function doMuestraError(fichero, metodo, jqXHR, textStatus, errorThrown) {
    var errorDescripción;
    if (jqXHR.status === 0) {
            errorDescripción='Not connect: Verify Network.';
        } else if (jqXHR.status == 404) {
            errorDescripción='Requested page not found [404]';
        } else if (jqXHR.status == 500) {
            errorDescripción='Internal Server Error [500].';
        } else if (textStatus === 'parsererror') {
            errorDescripción='Requested JSON parse failed.';
        } else if (textStatus === 'timeout') {
            errorDescripción='Time out error.';
        } else if (textStatus === 'abort') {
            errorDescripción='Ajax request aborted.';
        } else {
            errorDescripción=' Error: ' + jqXHR.responseText;
        }
    alert(" Error : " + fichero + "\n" + metodo + "\n" + jqXHR.responseText + "\n" + textStatus + "\n" + errorThrown +"\n " +  errorDescripción);
}

/*

*/
$(document).ready(function () {

    $respuesta = $("#respuesta");

    /*
        Eventos on clicl por cada opción de menú
    */
    $("#noticias").click(function () {
        $("#paginahtml").html("");
        $("#paginahtml").load("html/noticias.html");
    });
    $("#inicio").click(function () {
        $("#paginahtml").html("");
        $("#paginahtml").load("html/noticias.html");
    });
    $("#ejemplo1").click(function () {
        $("#paginahtml").html("");
        $("#paginahtml").load("html/formulario.html");
        $.getScript('inc/formulario.js', function () {
            doHazFormulario();
        });
    });

    $("#urgencias").click(function () {
        $("#paginahtml").html("");
        $("#paginahtml").load("html/pacientesUrgencias.html");
       $.getScript('inc/urgencias.js', function () {
            doHazFormulariIngrsadoo();
        });
    });

    /*

    */
    $.getJSON("dao/getUsuario.php", function (result) {

        if (result != null && result['dni'] != '') {
            $('#usuario').text(result['dni']);
            $('#apellidosnombre').text(result['apellidos']);
            $.each(result['grupos'], function (id, value) {
                $("#side-menu").append('<li><a href=\"htmml/noticias.html\"><i class="fa fa-newspaper-o fa-fw"></i>' + value + '</a></li>');
            });
            $('#conectado').show();
            $('#sinconexion').hide();
        } else {
            $('#conectado').hide();
            $('#sinconexion').show();
        }

        return;
    })
        .error(function () { alert("error"); });



});











