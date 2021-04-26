public class Comment {
    int id;
    int iID;
    int uID;
    String comment;
    public Comment(int id, int iID, int uID, String comment) {
        this.id = id;
        this.iID = iID;
        this.uID = uID;
        this.comment = comment;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getiID() {
        return iID;
    }
    public void setiID(int iID) {
        this.iID = iID;
    }
    public int getuID() {
        return uID;
    }
    public void setuID(int uID) {
        this.uID = uID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
}
