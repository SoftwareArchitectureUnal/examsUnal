function checkPassword(){
    var password = $("#inputPassword").val();
    var repPassword = $("#inputRepPassword").val();
    if(password!=repPassword || password.length<=5){
        $("#signUp").prop('disabled',true);
        alert("Las contraseñas no coinciden o son muy cortas");
    }else{
        $("#signUp").prop('disabled',false);
    }
    
}
$(document).ready(function (){
    
});

