public class Image {
    int id;
    String url;
    String description;
    int user;
    int upvotes;
    int downvotes;
    
    public Image(int id, String url, String description, int user, int upvotes, int downvotes) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.user = user;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    
}
