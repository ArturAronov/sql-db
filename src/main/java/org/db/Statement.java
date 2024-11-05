package org.db;

import org.db.enums.PrepareResultE;
import org.db.enums.StatementE;

import java.util.regex.Matcher;

public class Statement {
    public StatementE type;
    private InputBuffer inputBuffer;

    public Statement(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public PrepareResultE prepareStatement() {
        if(inputBuffer.getBuffer().startsWith("insert")) {
            Matcher matcher = InputBuffer.match("insert (\\d+) (\\S+) (\\S+)", inputBuffer.getBuffer());

            if(matcher.find()) {
//                Integer id = Integer.parseInt(matcher.group(1));
//                String userName = matcher.group(2);
//                String email = matcher.group(3);
//
//                Row row = new Row(id, userName, email);
                this.type = StatementE.STATEMENT_INSERT;
                return PrepareResultE.PREPARE_SUCCESS;
            } else {
                return PrepareResultE.PREPARE_SYNTAX_ERROR;
            }
        }

        if(inputBuffer.getBuffer().startsWith("select")) {
            this.type = StatementE.STATEMENT_SELECT;
            return PrepareResultE.PREPARE_SUCCESS;
        }

        return PrepareResultE.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    public void executeStatement() {
        switch (type) {
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
