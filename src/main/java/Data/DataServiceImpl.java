package Data;

import java.sql.Connection;

import ex.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataServiceImpl  implements DataService{
    public Connection connection;
    public DataServiceImpl()
    {
        connection = getH2Connection();
    }
    public void create()
    {
        try {
        (new UserDAO(connection)).createTable();
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
        }
    }
public DataBase getUser(String login)
{
    try {
        UsersDataSet usersDataSet = (new UserDAO(connection)).get(login);
        return new DataBase(usersDataSet.getLogin(),usersDataSet.getPassword());
    }
    catch (SQLException e)
    {
        System.out.println(e.getSQLState());
        return null;
    }
}
public long addUser(DataBase user)
{
    try{
        connection.setAutoCommit(false);
        UserDAO dao = new UserDAO(connection);
        dao.insert(user);
        connection.commit();
        return dao.getUserId(user.getLogin());
    }
    catch (SQLException e)
    {
        System.out.println(e.getSQLState());
        return 0;
    }
}
    public void check()  {
        try {
            System.out.println("Driver name: " + connection.getMetaData().getDriverName());
            System.out.println("Driver version: " + connection.getMetaData().getDriverVersion());

            UserDAO dao = new UserDAO(connection);
            int count = dao.getCount();
            System.out.println("Count of records in users: " + count);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }
    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
