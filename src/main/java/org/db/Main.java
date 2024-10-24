package org.db;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    private static void printPrompt() {
        System.out.print("db > ");
    }

    private static void readInput(InputBuffer inputBuffer, BufferedReader reader) {
        try{
            String input = reader.readLine();
            inputBuffer.setBuffer(input);
        } catch(IOException error) {
            System.out.println("Error reading input");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        InputBuffer inputBuffer = new InputBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            printPrompt();
            readInput(inputBuffer, reader);

            if(".exit".equals(inputBuffer.getBuffer())) {
                System.exit(0);
            } else if(inputBuffer.bufferLength > 0) {
                System.out.printf("Unrecognized command '%s'. \n", inputBuffer.getBuffer());
            } else {
                System.out.println("Input is empty");
            }
        }
    }
}