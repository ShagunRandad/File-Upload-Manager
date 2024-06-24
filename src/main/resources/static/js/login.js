$(document).ready(function() {
            var message = $('meta[name="message"]').attr('content');
            var error = $('meta[name="error"]').attr('content');
            if (message) {
                swal( message);
            }
            if (error) {
                swal( error);
            }
        });

function registrationForm(){
	$("#registration_Modal").modal('show');
}

function userRegistration(){
	let password = $("#user_Password_Modal").val();
	let confirmPassword = $("#confirm_Pass").val();
	
	if(password==confirmPassword){
	
	}else{
		swal("Password and Confirm password does not match");
		return false;
	}
}