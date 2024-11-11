package org.db;

import java.nio.ByteBuffer;

public class Serializer {
    public static int ID_SIZE;
    public static int EMAIL_SIZE;
    public static int USERNAME_SIZE;

    public static int ID_OFFSET = 0;
    public static int USERNAME_OFFSET;
    public static int EMAIL_OFFSET;
    public static int ROW_OFFSET;

    public Serializer() {
        this.ID_OFFSET = 0;
        this.USERNAME_OFFSET = 4;
        this.EMAIL_OFFSET = 36;

        this.ID_SIZE = 4;
        this.USERNAME_SIZE = 32;
        this.EMAIL_SIZE = 255;
    }

    public static synchronized void Serializer(int id, int email, int username) {
        ID_SIZE = id;
        EMAIL_SIZE = email;
        USERNAME_SIZE = username;

        USERNAME_OFFSET = ID_OFFSET + ID_SIZE;
        EMAIL_OFFSET = USERNAME_OFFSET + USERNAME_SIZE;
        ROW_OFFSET = ID_SIZE + EMAIL_SIZE + USERNAME_SIZE;
    }

    public byte[] serializeRow(Row source) {
        // Creates a new byte array to hold the serialized data
        byte[] destination = new byte[ID_SIZE + USERNAME_SIZE + EMAIL_SIZE];
        // if ID_SIZE=4, USERNAME_SIZE=32, EMAIL_SIZE=255
        // destination = new byte[291]

        // Serialize id, username, email
        // Convert the email string to byte array using default encoding
        byte[] emailBytes = source.getEmail().getBytes();
        // Example: if email="test@example.com"
        // emailBytes = [116, 101, 115, 116, 64, 101, 120, 97, 109, 112, 108, 101, 46, 99, 111, 109]


        // Convert the username string to byte array using default encoding
        byte[] usernameBytes = source.getUsername().getBytes();
        // Example: if username="john_doe"
        // usernameBytes = [106, 111, 104, 110, 95, 100, 111, 101]

        // Convert the integer ID to a 4-byte array
        byte[] idBytes = ByteBuffer.allocate(ID_SIZE).putInt(source.getId()).array();
        // Example: if id=12345
        // idBytes = [0, 0, 48, 57]

        // Copy ID bytes to destination starting at ID_OFFSET
        System.arraycopy(idBytes, 0, destination, ID_OFFSET, ID_SIZE);
        // Example: destination[0-3] = [0, 0, 48, 57]

        // Copy username bytes to destination starting at USERNAME_OFFSET
        System.arraycopy(usernameBytes, 0, destination, USERNAME_OFFSET, Math.min(usernameBytes.length, USERNAME_SIZE));
        // Example: destination[4-11] = [106, 111, 104, 110, 95, 100, 111, 101]

        // Copy email bytes to destination starting at EMAIL_OFFSET
        System.arraycopy(emailBytes, 0, destination, EMAIL_OFFSET, Math.min(emailBytes.length, EMAIL_SIZE));
        // Example: destination[36-51] = [116, 101, 115, 116, 64, 101, 120, 97, 109, 112, 108, 101, 46, 99, 111, 109]


        return destination;
    }

    public Row deserializeRow(byte[] source) {
        int id = ByteBuffer.wrap(source, ID_OFFSET, ID_SIZE).getInt();
        String email = new String(source, EMAIL_OFFSET, EMAIL_SIZE).trim();
        String username = new String(source, USERNAME_OFFSET, USERNAME_SIZE).trim();

        Row row = new Row(id, username, email);
        return row;
    }
}
