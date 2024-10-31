package org.db;

import org.db.enums.StatementType;

public class Statement {
    public StatementType type;

    public Statement(StatementType type) {
        this.type = type;
    }

    public StatementType getType() {
        return type;
    }
}
