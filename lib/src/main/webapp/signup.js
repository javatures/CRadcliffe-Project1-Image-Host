function verifyName(input){
    if(input.value != document.getElementById("1").value){
        input.setCustomValidity('User names must match...')
    }
    else{
        input.setCustomValidity('');
    }

}
function verifyPass(input){
    if(input.value != document.getElementById("3").value){
        input.setCustomValidity('Passwords must match...')
    }
    else{
        input.setCustomValidity('');
    }
}