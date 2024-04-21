package models;

public class UserSessionManager {

    private static UserSessionManager instance;
    private User user = null;

    public UserSessionManager() {}

    public static UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public void reset() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
