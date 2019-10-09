package servlets;

import Data.DataBase;
import Data.DataService;
import Data.DataServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private DataService base;
    public SignUpServlet(DataService data)
    {
        base = data;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws  SecurityException,IOException
    {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
        System.out.println(base.addUser(new DataBase(login,password)));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("SignedUp");
            response.setStatus(HttpServletResponse.SC_OK);
    }
}
