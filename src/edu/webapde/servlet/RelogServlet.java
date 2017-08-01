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
				String userId = null;

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
						ResultSet rs3=stmt.executeQuery("SELECT username, description, userId FROM user"); 
						
						while(rs3.next()){ 
							if( username.equals(rs3.getString(1)) ){ 
									description = rs3.getString(2);
									userId = rs3.getString(3);
							}
						}
					}catch(Exception e){ System.out.println(e);}

					request.getSession().setAttribute("uId", userId);
					int ctr = 0;
					int i = 0;
					int j =0;

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
		  
				    Boolean checker = false; //True if username and pword matches\

				    int length = 0;

					try{
			            Statement stmt = con.getConnection().createStatement();
			            ResultSet rs2 = stmt.executeQuery("SELECT id, title, description, location, userId FROM posts");

			            while(rs2.next()){
			                if( userId.equals(rs2.getInt(5)) ){
			                    request.getSession().setAttribute("id" + length, rs2.getString(1));
			                    request.getSession().setAttribute("title" + length, rs2.getString(2));
			                    request.getSession().setAttribute("desc" + length, rs2.getString(3));
			                    request.getSession().setAttribute("location" +length, rs2.getString(4));
			                    length ++;
			                }
			            }

			        }catch(Exception e){System.out.println(e);}

					//use cookie value and set in as attr to session
					request.getSession().setAttribute("un", username);
					request.getSession().setAttribute("desc", description);
					//go to userFeed.jsp
					request.getRequestDispatcher("userFeed.jsp").forward(request, response); 
				}else{ 	//user had not visited website, or logged out
					response.sendRedirect("index.html");
				}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
	}
	
	
	
	

}
