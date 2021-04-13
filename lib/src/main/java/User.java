
public class User{
    public int id;
    public String userName;
    public String password;
    public boolean pLevel;
    public int restrictions;

    public User(int id, String userName, String password, boolean pLevel, int restrictions){
        this.id = id;
        this.pLevel = pLevel;
        this.password = password;
        this.userName = userName;
        this.restrictions = restrictions;

    }
}