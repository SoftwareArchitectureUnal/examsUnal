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
    
    $("#close-modal").click( function(){
        $("#modal-saved").hide();
        //alert("hola");
    });  
    //setInterval(function(){ alert("Hello"); }, 1000);
    
    charts();
    $('#table-exams').dataTable({
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
    });

    $("#btn-insert").click(function(){
        $("#modal-save").modal();
    });
    $('#btn-save').click(function(){
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "insertExam",
                name: $('#exam-name').val(),
                expeditionDate: $('#exam-start-date').val(),
                realizationDate: $('#exam-date').val(),
                certificationDate: $('#exam-cert-date').val(),
                description: $('#exam-description').val()
            },
            success: function(){      
                $('#modal-save').modal('hide');
                setTimeout(saved, 200);
            },
            error: function(msg,textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
    function saved(){
        $('#modal-saved').modal();
    }
    $('.btn-update').click(function(){
        var id = $(this).attr("id");
        $("#modal-update").modal();
        $("#id-up").text(id);
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "findExam",
                id: id
            },
            success: function( data ){  
                var exam = data.split(",");
                $("#exam-name-up").val(exam[0]);
                $("#exam-start-date-up").val(exam[1]);
                $("#exam-date-up").val(exam[2]);
                $("#exam-cert-date-up").val(exam[3]);
                $("#exam-description-up").val(exam[4]);
            },
            error: function(msg, textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
    $('#btn-update').click(function(){
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "updateExam",
                id: $("#id-up").text(),
                name: $('#exam-name-up').val(),
                expeditionDate: $('#exam-start-date-up').val(),
                realizationDate: $('#exam-date-up').val(),
                certificationDate: $('#exam-cert-date-up').val(),
                description: $('#exam-description-up').val()
            },
            success: function(){      
                $('#modal-update').modal('hide');
                setTimeout(updated, 200);
            },
            error: function(msg,textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
    function updated(){
        $('#modal-updated').modal();
    }
    $('.btn-delete').click(function(){
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "deleteExam",
                id: $(this).attr('id')
            },
            success: function(){ 
                $('#modal-deleted').modal();
            },
            error: function(msg,textStatus, errorThrown){
                alert("Error "+errorThrown);
            }
        });
    });
    $(".datepicker").datepicker({
        showOtherMonths: true, selectOtherMonths: true, changeMonth: true,
        changeYear: true, numberOfMonths: 2, showWeek: true, minDate: 0
    });
    $(".datepicker").datepicker("option", "showAnim", "slideDown");
    $(".datepicker").datepicker("option", "dateFormat", "dd-mm-yy");
    
    /*$.widget( "custom.catcomplete", $.ui.autocomplete, {
      _create: function() {
        this._super();
        this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
      },
      _renderMenu: function( ul, items ) {
        var that = this,
          currentCategory = "";
        $.each( items, function( index, item ) {
          var li;
          if ( item.category != currentCategory ) {
            ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
            currentCategory = item.category;
          }
          li = that._renderItemData( ul, item );
          if ( item.category ) {
            li.attr( "aria-label", item.category + " : " + item.label );
          }
        });
      }
    });*/
    
    var labelsComplete = []; 
    var categoriesComplete = []; 
    
    $.ajax({
        type: "post",
        url: "AdminExamsServlet",
        data: {
            method: "findAllExams"
        },
        success: function(data){ 
            var results = data.split("$$");
            for(var i = 0; i < results.length; i++){
                var values = results[i].split("&&");
                labelsComplete.push( values[0] + ' - ' + values[1] );
                categoriesComplete.push( values[2]);
            }
            autocompleteExams(labelsComplete, categoriesComplete);    
        },
        error: function(msg,textStatus, errorThrown){
            alert("Error "+errorThrown);
        }
    });
    
    function autocompleteExams(labels, categories){
        var data = [];
        for(var i = 0; i < labels.length; i++){
            data.push( { label: labels[i], category: categories[i]});
        }
        $( "#search-exams" ).autocomplete({
            delay: 0,
            source: data
        });
    }
    
    $("#btn-statistics").click(function () {
        
    });
    
    $("#btn-load").click(function () {
        var selectedExam = $("#search-exams").val();
        if( selectedExam == "" )
            alert("Seleccione un exámen");
        else{
            $.ajax({
                type: "post",
                url: "AdminExamsServlet",
                data: {
                    method: "examResult", 
                    id: selectedExam.split(" - ")[0]
                },
                success: function(data){ 
                    var exam = data.split("&&");
                    var approved = 0;
                    var failed = 0;
                    for(var i = 0; i < exam.length; i++){
                        approved = exam[2];
                        failed = exam[3];
                    }
                    examChart(approved, failed);
                },
                error: function(msg,textStatus, errorThrown){
                    alert("Error "+errorThrown);
                }
            });
        }
    });
    
    $('.nav-tabs a').click(function(){
        //$(this).tab('show');
    });
    $('#tab-exams-statistics').click(function(){
        $("#exams-statistics").show();
        $("#certified-users").hide();
        $("#registered-users").hide();
        jQuery('#legend-users-chart').html('');
    });
    
    $('#tab-certified-users').click(function(){
        $("#exams-statistics").hide();
        $("#certified-users").show();
        $("#registered-users").hide();
        jQuery('#legend-users-chart').html('');
        var results;
        var examNames = [];
        var examsApproved = [];
        var examsFailed = [];
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "examsResults"
            },
            success: function(data){ 
                results = data.split("$$");
                for(var i = 0; i < results.length; i++){
                    examNames.push(results[i].split("&&")[1]);
                    examsApproved.push(results[i].split("&&")[2]);
                    examsFailed.push(results[i].split("&&")[3]);
                }
                resultsChar(examNames, examsApproved, examsFailed);
            },
            error: function(msg,textStatus, errorThrown){
                alert("Error "+errorThrown);
            }
        });
    });
    
    $('#tab-registered-users').click(function(){
        $("#exams-statistics").hide();
        $("#certified-users").hide();
        $("#registered-users").show();
        jQuery('#legend-users-chart').html('');
        var examNamesRegistered = [];
        var examsRegistered = [];
        
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "examsRegistered"
            },
            success: function(data){ 
                var results = data.split("$$");
                for(var i = 0; i < results.length; i++){
                    examNamesRegistered.push(results[i].split("&&")[1]);
                    examsRegistered.push(results[i].split("&&")[2]);
                }
                registeredChar(examNamesRegistered, examsRegistered);
            },
            error: function(msg,textStatus, errorThrown){
                alert("Error "+errorThrown);
            }
        });
    });
    
    function charts(){
        var results;
        var examNames = [];
        var examsApproved = [];
        var examsFailed = [];
        $.ajax({
            type: "post",
            url: "AdminExamsServlet",
            data: {
                method: "examsResults"
            },
            success: function(data){ 
                results = data.split("$$");
                for(var i = 0; i < results.length; i++){
                    examNames.push(results[i].split("&&")[1]);
                    examsApproved.push(results[i].split("&&")[2]);
                    examsFailed.push(results[i].split("&&")[3]);
                }
                resultsChar(examNames, examsApproved, examsFailed);
            },
            error: function(msg,textStatus, errorThrown){
                alert("Error "+errorThrown);
            }
        });
    }

    function examChart( approved, failed ){
        var data = [{
                        value: approved,
                        color:"#F7464A",
                        highlight: "#FF5A5E",
                        label: "Aprobaron"
                    },
                    {
                        value: failed,
                        color: "#46BFBD",
                        highlight: "#5AD3D1",
                        label: "Perdieron"
                    }];

        var options = { segmentShowStroke : true, segmentStrokeColor : "#fff", segmentStrokeWidth : 2, percentageInnerCutout : 50,
                        animationSteps : 100, animationEasing : "easeOutBounce", animateRotate : true, animateScale : false, responsive: true,
                        legendTemplate : "<ul class='<%=name.toLowerCase()%>-legend'>" +
                                            "<% for (var i=0; i<segments.length; i++){%>" +
                                                "<li style=\"list-style-type: none;\">" + 
                                                    "<span style=\"color:black;\"><h4 style=\"color:<%=segments[i].fillColor !important;%>;\" class=\"glyphicon glyphicon-stop\" aria-hidden=\"true\"></h4><%if(segments[i].label){%>&nbsp;<%=segments[i].label%><%}%><br/></span>" + 
                                                "</li>" + 
                                            "<%}%>" +
                                         "</ul>"
                      };
        var context = $("#exam-chart").get(0).getContext("2d");
        var chartExam = new Chart(context).Doughnut(data, options);
        var legend = chartExam.generateLegend();
        jQuery('#legend-exam-chart').html('');
        $('#legend-exam-chart').append(legend);
    }
    
    function resultsChar( examNames, examsApproved, examsFailed ){
        var data = {
            labels: examNames,
            datasets: [
                {
                    label: "Certificados",
                    fillColor: "rgba(220,220,220,0.65)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: examsApproved
                },
                {
                    label: "No certificados",
                    fillColor: "rgba(151,187,205,0.65)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: examsFailed
                }
            ]
        };


        var options = { scaleBeginAtZero : true, scaleShowGridLines : true, scaleGridLineColor : "rgba(0,0,0,.05)", scaleGridLineWidth : 1,
                        scaleShowHorizontalLines: true, scaleShowVerticalLines: true, barShowStroke : true, barStrokeWidth : 2,
                        barValueSpacing : 5, barDatasetSpacing : 1, responsive: true,
                        legendTemplate : "<ul class='<%=name.toLowerCase()%>-legend'>" +
                                            "<% for (var i=0; i<datasets.length; i++){%>" +
                                                "<li style=\"list-style-type: none;\">" + 
                                                    "<span style=\"color:black;\"><h4 style=\"color:<%=datasets[i].fillColor%>;\" class=\"glyphicon glyphicon-stop\" aria-hidden=\"true\"></h4><%if(datasets[i].label){%>&nbsp;&nbsp;<%=datasets[i].label%><%}%></span>" + 
                                                "</li>" + 
                                            "<%}%>" +
                                         "</ul>"
                      };
        var context = $("#users-chart").get(0).getContext("2d");
        var chartUsers = new Chart(context).Bar(data, options);
        var legend = chartUsers.generateLegend();
        $('#legend-users-chart').append(legend);
    }
    
    function registeredChar( examNamesRegistered, examsRegistered ){
        var data = {
            labels: examNamesRegistered,
            datasets: [
                {
                    label: "Inscritos",
                    fillColor: "rgba(151,187,205,0.65)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: examsRegistered
                }
            ]
        };

        var options = { scaleBeginAtZero : true, scaleShowGridLines : true, scaleGridLineColor : "rgba(0,0,0,.05)", scaleGridLineWidth : 1,
                        scaleShowHorizontalLines: true, scaleShowVerticalLines: true, barShowStroke : true, barStrokeWidth : 2,
                        barValueSpacing : 20, barDatasetSpacing : 1, responsive: true,
                        legendTemplate : "<ul class='<%=name.toLowerCase()%>-legend'>" +
                                            "<% for (var i=0; i<datasets.length; i++){%>" +
                                                "<li style=\"list-style-type: none;\">" + 
                                                    "<span style=\"color:black;\"><h4 style=\"color:<%=datasets[i].fillColor%>;\" class=\"glyphicon glyphicon-stop\" aria-hidden=\"true\"></h4><%if(datasets[i].label){%>&nbsp;&nbsp;<%=datasets[i].label%><%}%></span>" + 
                                                "</li>" + 
                                            "<%}%>" +
                                         "</ul>"
                      };
        var context = $("#registered-chart").get(0).getContext("2d");
        var chartRegistered = new Chart(context).Bar(data, options);
        var legend = chartRegistered.generateLegend();
        //$('#legend-registered-chart').append(legend);
    }
});

