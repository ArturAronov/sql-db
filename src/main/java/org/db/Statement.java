package org.db;

import org.db.enums.PrepareResultE;
import org.db.enums.StatementE;

public class Statement {
    private StatementE statement;
    private InputBuffer inputBuffer;

    public Statement(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public PrepareResultE prepareStatement(StatementE statement) {
        if(inputBuffer.startsWith("insert")) {
            this.statement = statement;
            return PrepareResultE.PREPARE_SUCCESS;
        }

        if(inputBuffer.startsWith("select")) {
            this.statement = statement;
            return PrepareResultE.PREPARE_SUCCESS;
        }

        return PrepareResultE.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    public void executeStatement(StatementE statement) {
        switch (statement) {
            case STATEMENT_INSERT -> {
                System.out.println("This is where we would do an insert");
                break;
            } case STATEMENT_SELECT -> {
                System.out.println("This is where we would do a select");
                break;
            }
        }
    }
}
