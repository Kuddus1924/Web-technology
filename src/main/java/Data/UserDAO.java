package Data;


import java.sql.Connection;
import ex.Executor;
import ex.UsersDataSet;

import java.sql.SQLException;

public class UserDAO {
 private Executor executor;
 public  UserDAO(Connection connection)
 {
     executor = new Executor(connection);
 }
 public UsersDataSet get(String login) throws SQLException
 {
     return executor.execQuery("select * from users where login='" + login + "'",result -> {
         result.next();
         return new UsersDataSet(result.getLong(1),result.getString(2),result.getString(3));
         });
 }
 public long getUserId(String login) throws  SQLException
 {
     return executor.execQuery("select * from users where login='" + login + "'",result -> {
         result.next();
         return  result.getLong(1);
     });
 }
    public int getCount() throws  SQLException
    {
        return executor.execQuery("select count(id) from users" ,result -> {
            result.next();
            return  result.getInt(1);
        });
    }
    public void insert(DataBase dataSet) throws SQLException
    {
        executor.execUpdate("insert into users (login,password) values ('" + dataSet.getLogin() + "','" + dataSet.getPassword() +"')");
    }
    public void createTable()throws  SQLException
    {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id))");
    }

}
