<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		 <link rel="stylesheet" href="./css/main-style.css">
		 <link rel="stylesheet" href="./css/navbar-style.css">
		<title>Insert title here</title>
		
	</head>
	
	<body>
		<div id="topBar">
			<div class="username"> 
					<div class="text">Hi, ${sessionScope.un}</div>
			</div>

			<a href="profile.jsp">
				<div class="profile"> 
					<div class="overlay">
						<div class="text"  align = "center">Profile</div>
					</div>
				</div>
			</a>
				
			<a href="logout">
				<div class="logout">
					<div class="overlay">
						<div class="text" align = "center">Logout</div>
					</div>
				</div>
			</a>
		</div>
	
		 	<br><br>
			${sessionScope.rbr} 
			<br> Description : ${sessionScope.desc} 
			<br><br> 
	
			<a href="userFeed.jsp">Go to news feed</a>
	</body>
	
</html>