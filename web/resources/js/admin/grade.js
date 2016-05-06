/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    
    jQuery(function($) {

        var _oldShow = $.fn.show;

        $.fn.show = function(speed, oldCallback) {
            return $(this).each(function() {
                var obj = $(this),
                newCallback = function() {
                    if ($.isFunction(oldCallback)) {
                        oldCallback.apply(obj);
                    }
                    obj.trigger('afterShow');
                };

                // you can trigger a before show if you want
                obj.trigger('beforeShow');

                // now use the old function to show the element passing the new callback
                _oldShow.apply(obj, [speed, newCallback]);
            });
        }
    });
    
    var table = $('#table-grades').DataTable({
        "columnDefs": [
            { "visible": false, "targets": 0 }
        ],
        "order": [[ 0, 'asc' ]],
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;
 
            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="5">'+group+'</td></tr>'
                    );
 
                    last = group;
                }
            } );
        },
        "language": {
            "lengthMenu": "Mostrar _MENU_ resultados por página",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros guardados",
            "infoFiltered": "(filtrado entre _MAX_ resultados)",
            "loadingRecords": "Cargando...",
            "processing": "Procesando...",
            "search": "Buscar:",
            "paginate": {
                "first": "Primera",
                "last": "Última",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    } );
 
    // Order by the grouping
    $('#table-grades tbody').on( 'click', 'tr.group', function () {
        var currentOrder = table.order()[0];
        if ( currentOrder[0] === 0 && currentOrder[1] === 'asc' ) {
            table.order( [ 0, 'desc' ] ).draw();
        }
        else {
            table.order( [ 0, 'asc' ] ).draw();
        }
    } );
    
    $('.check-grade').change(function() {
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "gradeExam",
                data: this.id,
                value: $(this).is(':checked')
            },
            success: function(){      
            },
            error: function(msg,textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
    
    $('.check-presented').change(function() {
        if($(this).is(':checked')) { 
            $("#graded-"+this.id).removeAttr("disabled"); 
        } else {  
            $("#graded-"+this.id).attr("disabled", true);  
        }
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "disableExam",
                data: this.id,
                value: $(this).is(':checked')
            },
            success: function(){      
            },
            error: function(msg,textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
    
});