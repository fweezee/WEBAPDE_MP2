package edu.webapde.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RelogServlet
 */
@WebServlet("/relog")
public class RelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//get cookies
				Cookie[] cookies = request.getCookies();
				String username = null;
				String description = null;
				
				//check is username cookie exists
				if(cookies!=null){
					for(int i = 0; i< cookies.length; i++){
						Cookie currentCookie = cookies[i];
					
						if(currentCookie.getName().equals("username")){
							username = currentCookie.getValue();
							currentCookie.setMaxAge(60*60*24*21);
							response.addCookie(currentCookie);
						}
					}
				}	
				//if exists
				if(username!=null){
					dbCon con = new dbCon();
					try { 
						Statement stmt=con.getConnection().createStatement();  
						ResultSet rs1=stmt.executeQuery("SELECT username, description FROM user"); 
						
						while(rs1.next()){ 
							if( username.equals(rs1.getString(1)) ){ 
									description = rs1.getString(2);
							}
						}
					}catch(Exception e){ System.out.println(e);}
					
					//use cookie value and set in as attr to session
					request.getSession().setAttribute("un", username);
					request.getSession().setAttribute("desc", description);
					//go to userFeed.jsp
					request.getRequestDispatcher("userFeed.jsp").forward(request, response);
				}else{ 	//user had not visited website, or logged out
					//go to index.html
					response.sendRedirect("index.html");
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
