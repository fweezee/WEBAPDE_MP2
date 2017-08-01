
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="./css/homepage-style.css">
		<link href="./css/popupSignUp-style.css" rel="stylesheet">
		<script src="./JS/popupSignUp.js"></script>

		<link rel="stylesheet" type="text/css" href="./css/main-style.css">
		<link rel="stylesheet" href="./css/navbar-style.css">
		<link rel="stylesheet" href="./css/upload-page.css">

		<script type="text/javascript" src="./JS/jquery-3.2.1.js"></script>
		<script type="text/javascript" src="./JS/popupSignUp.js"></script>
		<script type="text/javascript" src="./JS/userFeed.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
			* {font-family: 'Segoe UI';}
			.tabbable .tabs {list-style: none; margin: 0 10px; padding: 0;}
			.tabbable .tabs li {list-style: none; margin: 0; padding: 0; display: inline-block; position: relative; z-index: 1;}
			.tabbable .tabs li a {text-decoration: none; color: #000; border: 1px solid #ccc; padding: 5px; display: inline-block; border-radius: 5px 5px 0 0; background: #f5f5f5;}
			.tabbable .tabs li a.active, .tabbable .tabs li a:hover {border-bottom-color: #fff; background: #fff;}
			.tabcontent {border: 1px solid #ccc; margin-top: -1px; padding: 10px;}
			* {
				box-sizing: border-box;
			}
		</style>


		<script type="text/javascript">
            $( document ).ready(function() {
                $(".tabbable").find(".tab").hide();
                $(".tabbable").find(".tab").first().show();
                $(".tabbable").find(".tabs li").first().find("a").addClass("active");
                $(".tabbable").find(".tabs").find("a").click(function(){
                    tab = $(this).attr("href");
                    $(".tabbable").find(".tab").hide();
                    $(".tabbable").find(".tabs").find("a").removeClass("active");
                    $(tab).show();
                    $(this).addClass("active");
                    return false;
                });
            });
		</script>

		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.css">

		<script src="http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>

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
		      <div id="signup">A
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


			<div id = "containerfeed">
		        <div id="posts">
		        			<div class="tabbable">
			<ul class="tabs">
				<li><a href="#feed">Feed</a></li>
			</ul>
			<div class="tabcontent">
				<div id="feed" class="tab">
 
					<ul id="myUL">
					<%try{ %>
						<%for(int i = 0; i < (int)session.getAttribute("lengthpost"); i++){%>
						<%if(session.getAttribute("type" + i).equals("public")){%>
						<li>
							<a href="#<%=session.getAttribute("pic" + i)%>">
								<img src="<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200">
							</a>
							<div style="display: none;"><%=session.getAttribute("tags" + i)%>
							</div>
						</li>
						<div id="<%=session.getAttribute("pic" + i)%>" class="modalDialog">
							<a href="#close" title="Close" class="close">X</a>
							<div class = "pageholder">

								<div class="left">
									<img class = "smolprofpic" src = "./css/css-photos/1437684956845.png">
								</div>
								<div class="right">
									<%for(int j = 0; j < (int)session.getAttribute("lengthuser"); j++){%>
									<%if(session.getAttribute("puserId" + i).equals(session.getAttribute("uId" + j))){%>
									 <%=session.getAttribute("username" + j)%>
									<%}}%>
								</div>

								<div style="clear:both;">
									<h2><%=session.getAttribute("title" + i)%></h2>
									<p><%=session.getAttribute("description" + i)%></p>
									<img src = "<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>">
									Tags: <input type="text" data-role="tagsinput" name = "tags" value = "<%=session.getAttribute("tags" + i)%>"/>
								</div>

							</div>
						</div>
						<%}%>
						<%}%>
				
					</ul> 

				</div>
			</div>
		</div>
			<%}catch(Exception e){} %>
		        </div>

		        

		</div>

		<form action ="explore" method = "post" enctype="multipart/form-data">
			</div>
		</form>


	</body>
</html>