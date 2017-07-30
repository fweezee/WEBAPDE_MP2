package edu.webapde.servlet;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/UploadServlet")
public class FileUploadHandler extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */

    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        MultipartRequest multiPartFile = new MultipartRequest(request, "C:/Users/Jords/IdeaProjects/WEBAPDE_MP2/web/photos", 104857600);

        String title = "\""+multiPartFile.getParameter("title")+"\"";
        String description = "\""+multiPartFile.getParameter("description")+"\"";
        String type = "\""+multiPartFile.getParameter("myradio")+"\"";
        String tags = multiPartFile.getParameter("tags");
        String share = multiPartFile.getParameter("share");
        String filename = "\""+multiPartFile.getFile("file").getName()+"\"";
        int userId = Integer.parseInt(multiPartFile.getParameter("userId"));
        String allTags = "\""+multiPartFile.getParameter("tags")+"\"";

        System.out.println(title);
        System.out.println(description);
        System.out.println(type);
        System.out.println(tags);
        System.out.println(share);
        System.out.println(userId);
        System.out.println(filename);

        int last_uNum=0;

//        Enumeration files = multiPartFile.getFileNames();
//        while(files.hasMoreElements() ){
//            String fileName = (String)files.nextElement();
//            System.out.println(fileName);
//            String fileSystemName= multiPartFile.getFilesystemName(fileName );
//            System.out.println(fileSystemName);
//        }

        dbCon con = new dbCon();
        try{
            Statement stmt = con.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM posts");
            while(rs.next()){
                last_uNum=rs.getInt(1);
            }
            last_uNum++;
            stmt.executeUpdate("INSERT INTO posts VALUE("+last_uNum+", "+title+", "+description+", "+filename+", "+userId+", "+type+", "+allTags+")");

            String[] tag = tags.split(",");
            rs = stmt.executeQuery("SELECT tag_id FROM tags");
            int tagctr = 0;
            while(rs.next()){
                tagctr=rs.getInt(1);
            }
            for(int i = 0; i < tag.length; i++){

                tagctr++;
                String temptag = "\""+tag[i]+"\"";
                System.out.println(temptag);
                stmt.executeUpdate("INSERT INTO tags VALUE("+tagctr+", "+last_uNum+", "+temptag+")");
            }

            String[] shares = share.split(",");
            rs = stmt.executeQuery("SELECT shareId FROM share");
            int sharectr = 0;
            while(rs.next()){
                sharectr=rs.getInt(1);
            }
            for(int i = 0; i < shares.length; i++){

                sharectr++;
                String tempshare = "\""+shares[i]+"\"";
                System.out.println(tempshare);
                stmt.executeUpdate("INSERT INTO share VALUE("+sharectr+", "+last_uNum+", "+userId+", "+tempshare+", "+filename+")");
            }


        }catch(Exception e){ System.out.println(e);}
        response.sendRedirect("userFeed.jsp");


    }


}