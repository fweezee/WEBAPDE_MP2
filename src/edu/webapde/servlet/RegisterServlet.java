package edu.webapde.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username = "\""+request.getParameter("uname")+"\"";;
		String password = "\""+request.getParameter("pword")+"\"";;
		String description = "\""+request.getParameter("description")+"\"";;
		Boolean checker = true; //if this is false, there is an existing username already
		int last_uNum=0;
		
		dbCon con = new dbCon();
		try{
			Statement stmt = con.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT userId from user");
			while(rs.next()){
				last_uNum=rs.getInt(1);
			}
			last_uNum++;
			stmt.executeUpdate("INSERT INTO user VALUE("+last_uNum+", "+username+", "+password+", "+description+" )");
		}catch(Exception e){ System.out.println(e);}
		response.sendRedirect("index.html");
			
	}

}
