public class Report {
    int id;
    boolean type;
    int Reason;
    String description;
    int source;
    int handler;
    String response;
    boolean appeal;
    public Report(int id, boolean type, int reason, String description, int source, int handler, String response,
            boolean appeal) {
        this.id = id;
        this.type = type;
        Reason = reason;
        this.description = description;
        this.source = source;
        this.handler = handler;
        this.response = response;
        this.appeal = appeal;
    }

    

}
