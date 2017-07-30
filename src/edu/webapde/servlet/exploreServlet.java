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

        String hello = request.getParameter("explore");

        System.out.println("HELLO");

        int ctr = 0;
        dbCon con = new dbCon();
        request.getSession().setAttribute("asdf" ,"asdf");


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

        }catch(Exception e){ System.out.println(e);}

        request.getSession().setAttribute("length", ctr);

        request.getRequestDispatcher("publicFeed.jsp").forward(request,  response);

    }


}
