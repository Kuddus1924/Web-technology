package Data;

public interface DataService {
    public void create();
    public DataBase getUser(String login);
    public long addUser(DataBase user);
    public void check();
}
