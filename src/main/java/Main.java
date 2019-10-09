import Data.DataService;
import Data.DataServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String arg[]) throws Exception
    {
        DataService base = new DataServiceImpl();
        base.create();
        SignUpServlet up = new SignUpServlet(base);
        SignInServlet in = new SignInServlet(base);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(up),"/signup");
        contextHandler.addServlet(new ServletHolder(in),"/signin");
        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
