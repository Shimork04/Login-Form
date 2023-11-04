package com.example.registrationform;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    private String message;

//    public void init() {
//        message = "Hello World!";
//    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");

        //getting all the incoming details from the request
        String name = request.getParameter("user_name");
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        out.println(name);
        out.println(email);
        out.println(password);

        // creating connection using database
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/registration_base";
            String uname = "root";
            String passwrd = "1234";

            Connection con = DriverManager.getConnection(url,uname,passwrd);

            // writing query
            // the 3 cols are name, password, email -- 3 fields of table in database
            String query = "insert into userdetail(name,password,email) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            pstmt.setString(2,password);
            pstmt.setString(3,email);

            pstmt.executeUpdate();
            out.println("<h3>Data Updated.</h3>");

        }

        catch(Exception e){
            e.printStackTrace();
            out.println("<h3>Error, please try again</h3>");
        }


        out.println("</body></html>");

    }

    public void destroy() {
    }
}