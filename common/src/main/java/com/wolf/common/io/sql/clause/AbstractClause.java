package com.wolf.common.io.sql.clause;

public abstract class AbstractClause implements Clause {
    protected boolean prepared = true;

    @Override
    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }
}
