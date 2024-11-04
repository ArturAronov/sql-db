package org.db;

public class Row {
    public int id;
    public String email;
    public String username;

    private int maxIdLength = 4;    // Bytes
    private int maxEmailLength;     // Bytes
    private int maxUsernameLength;  // Bytes

    public Row(
            int id,
            String username,
            String email,
            int maxUsernameLength,
            int maxEmailLength
    ) {
        this.id = id;
        this.email = email;
        this.username = username;

        this.maxEmailLength = maxEmailLength;
        this.maxUsernameLength = maxUsernameLength;
    }
}
