package edu.webapde.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/explore")
public class exploreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public exploreServlet() {
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

        Boolean checker = false; //True if username and pword matches
        String description = null;
        String userId = null;
        dbCon con = new dbCon();

        Statement stmt;
        ResultSet rs1;
        int ctr = 0;
        try {
            stmt=con.getConnection().createStatement();
            rs1=stmt.executeQuery("SELECT username, password, description, userId FROM user");
            while(rs1.next()){
                    request.getSession().setAttribute("uId"+ctr, rs1.getString(4));
                request.getSession().setAttribute("username"+ctr, rs1.getString(1));
                    ctr++;
            }
            rs1.close();
            request.getSession().setAttribute("lengthuser", ctr);


            ctr = 0;
            rs1=stmt.executeQuery("SELECT userId, title, description, location, id, type, tags FROM posts");
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
            rs1.close();
            request.getSession().setAttribute("lengthpost", ctr);


            ctr = 0;
            rs1=stmt.executeQuery("SELECT userId, shareusername, photoId FROM share");
            while(rs1.next()){
                request.getSession().setAttribute("sharepId"+ctr, rs1.getString(2));
                request.getSession().setAttribute("sphotoId" +ctr, rs1.getString(3));
                ctr++;
            }
            rs1.close();
            request.getSession().setAttribute("lengthshare", ctr);

            ctr = 0;
            rs1=stmt.executeQuery("SELECT tag_id, photo_id, tagname FROM tags");
            while(rs1.next()){
                request.getSession().setAttribute("tagId"+ctr, rs1.getString(1));
                request.getSession().setAttribute("tphotoId"+ctr, rs1.getString(2));
                request.getSession().setAttribute("tagname" +ctr, rs1.getString(3));
                ctr++;
            }
            rs1.close();
            request.getSession().setAttribute("lengthtags", ctr);



        }catch(Exception e){ System.out.println(e);}

        response.sendRedirect("publicFeed.jsp");
	}
}
