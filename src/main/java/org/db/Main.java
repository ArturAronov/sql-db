package org.db;

import org.db.enums.MetaCommandResultE;

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
        Statement statement = new Statement(inputBuffer);
        MetaCommandResult metaCommandResult = new MetaCommandResult(inputBuffer);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            printPrompt();
            readInput(inputBuffer, reader);

            if(inputBuffer.startsWith(".")) {
                switch (metaCommandResult.doMetaCommand()) {
                    case MetaCommandResultE.META_COMMAND_SUCCESS -> {
                        continue;
                    } case MetaCommandResultE.META_COMMAND_UNRECOGNIZED_COMMAND -> {
                        System.out.println("Unrecognized command " + inputBuffer.getBuffer());
                        continue;
                    }
                }
            }

            switch (statement.prepareStatement()) {
                case PREPARE_SUCCESS -> {
                    statement.executeStatement();
                }
                case PREPARE_UNRECOGNIZED_STATEMENT -> {
                    System.out.println("Unrecognized keyword at start of '" +
                            inputBuffer.getBuffer() + "'");
                }
            }
        }
    }
}