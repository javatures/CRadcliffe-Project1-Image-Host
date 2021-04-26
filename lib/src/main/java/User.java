
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean ispLevel() {
        return pLevel;
    }

    public void setpLevel(boolean pLevel) {
        this.pLevel = pLevel;
    }

    public int getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(int restrictions) {
        this.restrictions = restrictions;
    }
}