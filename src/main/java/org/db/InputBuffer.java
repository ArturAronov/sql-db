package org.db;

public class InputBuffer {
    public String buffer;
    public long inputLength;
    public long bufferLength;

    public InputBuffer() {
        this.buffer = null;
        this.inputLength = 0;
        this.bufferLength = 0;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
        this.inputLength = buffer.length();
        this.bufferLength = buffer.length();
    }

    public boolean startsWith(String input) {
        return buffer.startsWith(input);
    }
}
