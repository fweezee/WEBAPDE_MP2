<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="./css/homepage-style.css">
		<link href="./css/popupSignUp-style.css" rel="stylesheet">
		<script src="./JS/popupSignUp.js"></script>
	</head>
	<body>
		<div id="topBar">
				<div class="login"> 
						<form action ="login" method = "post">
							<div class="unameFld"> Username: <input type = "text"  name = "uname" size = "15"/> </div>
							<div class="pwordFld">Password: <input type = "password" name = "pword" size = "15"/> </div>			
							<div class="checkbox"><input type="checkbox" value="YES" class="chk" name="remember"> Remember Me</div> 
							<div><input class="lginBtn" type = "submit" value = "Log in" id="login"/> </div>
						</form>
				<button id="signUpBtn" onclick="div_show()">Sign Up</button> 
				</div>
		</div>
		<div id = "publicFeed"> 
		      <div id="signup">
		            <!-- Popup Div Starts Here -->
		            <div id="popupContact">
		                <form  action="register" id="form" method="post">
		                    <img id="close" src="./css/css-photos/xbtn.png" height="20" width="20" onclick ="div_hide()">       
		                   	<div>Username: *<input id="un" name="uname" placeholder="Username" type="text"/> </div>
		                   	<div>Password: *<input id="pw" name="pword" placeholder="Password" type="password"></div>
		                    <div>Description:<textarea id="desc" name="description" placeholder="Description" size = "100"></textarea></div>
		                    <input type = "submit" value = "Sign Up" id="submit" onclick="return check_empty()"/> 
		                </form>
		            </div>
		            <!-- Popup Div Ends Here -->
		        </div>
		        
		        <div id="posts">
		        	PUBLIC PHOTOS
		        </div>
		        
		</div>
		
		
	</body>
</html>