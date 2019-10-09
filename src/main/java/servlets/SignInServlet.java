package servlets;

import Data.DataService;
import Data.DataServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private DataService base;
    public SignInServlet(DataService data)
    {
        base = data;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws  SecurityException,IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(base.getUser(login) != null)
        {
            if(base.getUser(login).getPassword().equals(password)) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            }
            else
            {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        else
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
