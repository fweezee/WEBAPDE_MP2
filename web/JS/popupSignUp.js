// Validating Empty Field
function check_empty() {
	if (document.getElementById('un').value == "" || document.getElementById('pw').value == "" ) {
		alert("Please fill up all the required(*) info!");
		return false;
	}else if(document.getElementById('un').value != "" && document.getElementById('pw').value != "" ){	
		alert("Account successfully created!");
		document.getElementById('form').submit();
	}
}
//Function To Display Popup
function div_show() {
	document.getElementById('signup').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
	document.getElementById('signup').style.display = "none";
}