package model;

/**
 *
 * @author Nhat
 */
public class Emperor {
    // Declare private fields for the table attributes
    private int id;
    private String username;
    private String password;
    private String name;
    private int kingdom_id;

    // Create a no-argument constructor
    public Emperor() {}

    // Create a parameterized constructor
    public Emperor(int id, String username, String password, String name, int kingdom_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.kingdom_id = kingdom_id;
    }

    // Create getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKingdom_id() {
        return kingdom_id;
    }

    public void setKingdom_id(int kingdom_id) {
        this.kingdom_id = kingdom_id;
    }
}
