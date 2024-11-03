package org.db;

import org.db.enums.MetaCommandResultE;

public class MetaCommandResult {
    private InputBuffer inputBuffer;

    public MetaCommandResult(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public MetaCommandResultE doMetaCommand() {
        if(".exit".equals(inputBuffer.getBuffer())){
            System.exit(0);
            return MetaCommandResultE.META_COMMAND_SUCCESS;
        } else {
            return MetaCommandResultE.META_COMMAND_UNRECOGNIZED_COMMAND;
        }
    }
}
