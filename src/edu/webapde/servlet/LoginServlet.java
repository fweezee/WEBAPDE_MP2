package edu.webapde.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("pword");
		String remember =request.getParameter("remember");
		
		Boolean checker = false; //True if username and pword matches
		String description = null;
		dbCon con = new dbCon();
		try { 
			Statement stmt=con.getConnection().createStatement();  
			ResultSet rs1=stmt.executeQuery("SELECT username, password, description FROM user"); 
			
			while(rs1.next()){ 
				if( username.equals(rs1.getString(1)) && password.equals(rs1.getString(2)) ){ 
						checker = true;
						description = rs1.getString(3);
				}
			}
		}catch(Exception e){ System.out.println(e);}
		
		if(checker && remember!= null) {
			request.getSession().setAttribute("un", username);
			request.getSession().setAttribute("desc", description);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60*60*24*21);
			response.addCookie(cookie);	
			request.getRequestDispatcher("userFeed.jsp").forward(request,  response);
		}else if(checker && remember == null) {
			request.getSession().setAttribute("un", username);
			request.getSession().setAttribute("desc", description);
			request.getRequestDispatcher("userFeed.jsp").forward(request,  response);
		}else{
			
			response.sendRedirect("index.html");	
			
		}
	}

}
