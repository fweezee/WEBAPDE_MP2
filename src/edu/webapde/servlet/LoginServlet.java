package edu.webapde.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	String username;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		this.username = username;
		String password = request.getParameter("pword");
		String remember =request.getParameter("remember");
		String imagePath = "C:/Users/Jords/Desktop/Anime/HOMEWORK/14590073_1265798113480645_4126802654257941886_o.jpg";
		String userId = null;

		System.out.println("Hello");

		Boolean checker = false; //True if username and pword matches
		String description = null;
		dbCon con = new dbCon();

		request.getSession().setAttribute("db", con);

		try { 
			Statement stmt=con.getConnection().createStatement();
			ResultSet rs1=stmt.executeQuery("SELECT username, password, description, userId FROM user");
			
			while(rs1.next()){ 
				if( username.equals(rs1.getString(1)) && password.equals(rs1.getString(2)) ){
						checker = true;
						description = rs1.getString(3);
						userId  = rs1.getString(4);
				}
			}
		}catch(Exception e){ System.out.println(e);}

			displayPosts(request, response, userId);


		//System.out.println(userId + " " + rs1.getString(1));


		if(checker && remember!= null) {
			request.getSession().setAttribute("un", username);
			request.getSession().setAttribute("desc", description);
			request.getSession().setAttribute("image", imagePath);
			request.getSession().setAttribute("uId", userId);

			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60*60*24*21);
			response.addCookie(cookie);	
			request.getRequestDispatcher("userFeed.jsp").forward(request,  response);
		}else if(checker && remember == null) {
			request.getSession().setAttribute("un", username);
			request.getSession().setAttribute("desc", description);
			request.getSession().setAttribute("image", imagePath);
			request.getSession().setAttribute("uId", userId);
			request.getRequestDispatcher("userFeed.jsp").forward(request,  response);
		}else{
			request.getSession().setAttribute("image", imagePath);
			response.sendRedirect("index.html");	
			
		}
	}

	protected void displayPosts(HttpServletRequest request, HttpServletResponse response, String userId) throws ServletException, IOException{

		int ctr = 0;
		int i = 0;
		int j =0;
		dbCon con = new dbCon();

		try {
			Statement stmt=con.getConnection().createStatement();
			ResultSet rs1=stmt.executeQuery("SELECT userId, title, description, location, id, type, tags FROM posts");


			//System.out.println(userId + " " + rs1.getString(1));

			while(rs1.next()){
					request.getSession().setAttribute("puserId" + ctr, rs1.getString(1));
					request.getSession().setAttribute("pic"+ctr,rs1.getString(5));
					request.getSession().setAttribute("title"+ctr, rs1.getString(2));
					request.getSession().setAttribute("description"+ctr, rs1.getString(3));
					request.getSession().setAttribute("location"+ctr, rs1.getString(4));
					request.getSession().setAttribute("type"+ctr, rs1.getString(6));
					request.getSession().setAttribute("tags"+ctr, rs1.getString(7));
					ctr++;
			}

			rs1=stmt.executeQuery("SELECT userId, shareusername, photoId FROM share");
			while(rs1.next()){
				request.getSession().setAttribute("sharepId"+i, rs1.getString(2));
				request.getSession().setAttribute("sphotoId" +i, rs1.getString(3));
				i++;
			}

			rs1=stmt.executeQuery("SELECT tag_id, photo_id, tagname FROM tags");
			while(rs1.next()){
				request.getSession().setAttribute("tagId"+j, rs1.getString(1));
				request.getSession().setAttribute("tphotoId"+j, rs1.getString(2));
				request.getSession().setAttribute("tagname" +j, rs1.getString(3));
				j++;
			}
		}catch(Exception e){ System.out.println(e);}


		request.getSession().setAttribute("length", ctr);
		request.getSession().setAttribute("sharelength", i);
		request.getSession().setAttribute("taglength", j);
		System.out.println(j);

	}


}
