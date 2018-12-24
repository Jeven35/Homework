package po;

/**
 * Created by jeven on 2018/12/24.
 */
public class User {
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    String name;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
