package org.db;

public class Row {
    public int id;
    public String email;
    public String username;

    public static int ID_SIZE;
    public static int EMAIL_SIZE;
    public static int USERNAME_SIZE;

    public static int ID_OFFSET = 0;
    public static int USERNAME_OFFSET;
    public static int EMAIL_OFFSET;
    public static int ROW_OFFSET;

    public Row(int id, String username, String email) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public static synchronized  void assignSizes(int id, int email, int username) {
        ID_SIZE = id;
        EMAIL_SIZE = email;
        USERNAME_SIZE = username;

        USERNAME_OFFSET = ID_OFFSET + ID_SIZE;
        EMAIL_OFFSET = USERNAME_OFFSET + USERNAME_SIZE;
        ROW_OFFSET = ID_SIZE + EMAIL_SIZE + USERNAME_SIZE;
    }
}
