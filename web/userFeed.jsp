<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
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
			#myInput {
				background-image: url('/css/searchicon.png');
				background-position: 10px 12px;
				background-repeat: no-repeat;
				width: 100%;
				font-size: 16px;
				padding: 12px 20px 12px 40px;
				border: 1px solid #ddd;
				margin-bottom: 12px;
			}
			#myUL {
				list-style-type: none;
				padding: 0;
				margin: 0;
			}
			#myUL li a {
				border: 1px solid #ddd;
				margin-top: -1px; /* Prevent double borders */
				background-color: #f6f6f6;
				padding: 12px;
				text-decoration: none;
				font-size: 18px;
				color: black;
				display: block
			}
			#myUL li a.header {
				background-color: #e2e2e2;
				cursor: default;
			}
			#myUL li a:hover:not(.header) {
				background-color: #eee;
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

                function myFunction() {
                    var input, filter, ul, li, a, i;
                    input = document.getElementById("myInput");
                    filter = input.value.toUpperCase();
                    ul = document.getElementById("myUL");
                    li = ul.getElementsByTagName("li");
                    for (i = 0; i < li.length; i++) {
                        a = li[i].getElementsByTagName("div")[0];
                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                            li[i].style.display = "";
                        } else {
                            li[i].style.display = "none";
                        }
                    }
                }
            });
		</script>


		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.css">

		<script src="http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>



		<title>Insert title here</title>



	</head>
	
	<body action = "image" >



	<form method="post" action="UploadServlet" enctype="multipart/form-data">

		<div id="topBar">
			<div class="username">
					<div class="text" name = "username">Hi, ${sessionScope.un} ${sessionScope.uId}</div>
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


		<input type="hidden" name="userId" value="${sessionScope.uId}">

		<a href="#uploadpic">Upload</a>
		<div id="uploadpic" class="modalDialog">
			<div>
				<a href="#close" title="Close" class="close">X</a>
			<h1>File Upload</h1>
			Select file to upload: <input type="file" name="file" size="60" /><br />
				<%--<br /> <input type="submit" value="Upload" />--%>

			<input type="radio" name=myradio value="public"/>public
			<input type="radio" name=myradio value="private"/>private
			Tags: <input type="text" data-role="tagsinput" name = "tags"/>
			Share with: <input type="text" data-role="tagsinput" name = "share"/>
			<div class= "un">Title: *<input id="un" name="title" placeholder="Username" type="text" size="15"/> </div>
			<div class= "dsc">Description:<textarea id="desc" name="description" placeholder="A short description about yourself (Not longer than 100 words)"></textarea></div>

			<input type = "submit" value = "Upload" id="submit"/>
			</div>
		</div>
		<input type="hidden" name="userId" value="${sessionScope.uId}">

		<div class="tabbable">
			<ul class="tabs">
				<li><a href="#feed">Feed</a></li>
				<li><a href="#uploads">Uploads</a></li>
				<li><a href="#share">Shared</a></li>
			</ul>
			<div class="tabcontent">
				<div id="feed" class="tab">


					<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

					<ul id="myUL">
						<%for(int i = 0; i < (int)session.getAttribute("length"); i++){%>
						<%if(session.getAttribute("type" + i).equals("public") || Integer.parseInt((String) session.getAttribute("puserId" + i)) == Integer.parseInt((String) session.getAttribute("uId"))){%>
						<li><a href="#<%=session.getAttribute("pic" + i)%>"><img src="<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200"></a><div style="display: none;><%=session.getAttribute("tags" + i)%></div></li>
						<div id="<%=session.getAttribute("pic" + i)%>" class="modalDialog">
							<div>
								<a href="#close" title="Close" class="close">X</a>
								<h2><%=session.getAttribute("title" + i)%></h2>
								<img src = "<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200">
								<p><%=session.getAttribute("description" + i)%></p>
							</div>
						</div>
						<%}%>
						<%}%>
					</ul>

					<!-- The Modal -->
					<%--<%for(int i = 0; i < (int)session.getAttribute("length"); i++){%>--%>
					<%--<%if(session.getAttribute("type" + i).equals("public") || Integer.parseInt((String) session.getAttribute("puserId" + i)) == Integer.parseInt((String) session.getAttribute("uId"))){%>--%>
					<%--<a href="#<%=session.getAttribute("pic" + i)%>"><img src="<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200"></a>--%>
					<%--<div id="<%=session.getAttribute("pic" + i)%>" class="modalDialog">--%>
						<%--<div>--%>
							<%--<a href="#close" title="Close" class="close">X</a>--%>
							<%--<h2><%=session.getAttribute("title" + i)%></h2>--%>
							<%--<img src = "<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200">--%>
							<%--<p><%=session.getAttribute("description" + i)%></p>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%--<%}%>--%>
					<%--<%}%>--%>
				</div>
				<div id="uploads" class="tab">
					<!-- The Modal -->
					<%for(int i = 0; i < (int)session.getAttribute("length"); i++){%>
					<%if(Integer.parseInt((String) session.getAttribute("puserId" + i)) == Integer.parseInt((String) session.getAttribute("uId"))){%>
					<a href="#<%=session.getAttribute("pic" + i)%>"><img src="<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200"></a>
					<div id="<%=session.getAttribute("pic" + i)%>" class="modalDialog">
						<div>
							<a href="#close" title="Close" class="close">X</a>
							<h2><%=session.getAttribute("title" + i)%></h2>
							<img src = "<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200">
							<p><%=session.getAttribute("description" + i)%></p>
						</div>
					</div>
					<%}%>
					<%}%>
				</div>
				<div id="share" class="tab">
					<!-- The Modal -->
					<%for(int i = 0; i < (int)session.getAttribute("length"); i++){%>
					<%for (int j = 0; j < (int)session.getAttribute("sharelength"); j++){
						if (session.getAttribute("pic" + i).equals(session.getAttribute("sphotoId"+j)) && session.getAttribute("un").equals(session.getAttribute("sharepId" + j))) {%>
								<a href="#<%=session.getAttribute("pic" + i)%>"><img src="<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200"></a>
								<div id="<%=session.getAttribute("pic" + i)%>" class="modalDialog">
									<div>
										<a href="#close" title="Close" class="close">X</a>
										<h2><%=session.getAttribute("title" + i)%></h2>
										<img src = "<%=request.getContextPath()%>/photos/<%=session.getAttribute("location" + i)%>" width="300" height="200">
										<p><%=session.getAttribute("description" + i)%></p>
									</div>
								</div>
							<%}
						}%>
					<%}%>
				</div>
			</div>
		</div>
		</form>

	</body>



</html>