function checkPassword(){
    var password = $("#inputPassword").val();
    var repPassword = $("#inputRepPassword").val();
    if(password != repPassword || password.length<=5){
        $("#btn-signUp").prop('disabled',true);
        //alert("Las contraseñas no coinciden o son muy cortas");
    }else{
        $("#btn-signUp").prop('disabled',false);
    }
    
}
$(document).ready(function (){
    
});

