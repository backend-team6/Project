package constant;

public enum DBUser {
    ROOT("jdbc:mysql://127.0.0.1:3306/daiso", "root", "1234");
    private final String url;
    private final String id;
    private final String password;

    DBUser(String url, String id, String password) {
        this.url = url;
        this.id = id;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
