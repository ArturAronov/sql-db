package org.db;

public class Row {
    public int id;
    public String email;
    public String username;

    public Row(int id, String username, String email) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
}
