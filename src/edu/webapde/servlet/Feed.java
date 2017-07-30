package edu.webapde.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/feed")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class Feed extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.

     */

     public Feed() {
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
     * handles file upload
     *
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        Boolean checker = false; //True if username and pword matches\

        int length = 0;

        System.out.println("CONNECTED TO POSTS");

        dbCon con = new dbCon();

        try{
            Statement stmt = con.getConnection().createStatement();
            ResultSet rsl = stmt.executeQuery("SELECT id, title, description, location, userId FROM posts");

            while(rsl.next()){
                if(userId == rsl.getInt(5)){
                    request.getSession().setAttribute("id" + length, rsl.getString(1));
                    request.getSession().setAttribute("title" + length, rsl.getString(2));
                    request.getSession().setAttribute("desc" + length, rsl.getString(3));
                    request.getSession().setAttribute("location" +length, rsl.getString(4));
                    length ++;
                }
            }

        }catch(Exception e){
            System.out.println(e);
        }

        request.getSession().setAttribute("length", length);
        response.sendRedirect("userFeed.jsp");



    }

}