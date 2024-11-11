package org.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static Matcher match(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
