public class CatsInfo {
    String id;
    String text;
    String type;
    String user;
    int upvotes;

    public CatsInfo(String id, String text, String type, String user, int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public CatsInfo(){}
}
