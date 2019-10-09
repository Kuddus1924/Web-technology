package Data;

import java.util.HashMap;

public class DataBase {
   private String login;
   private String password;
   public DataBase(String login,String password)
   {
       this.login = login;
       this.password = password;
   }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
