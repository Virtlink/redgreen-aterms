package org.metaborg.terms2.impl;

import org.metaborg.terms2.IConstructor;

public class Constructor implements IConstructor {

    private final String name;
    private final int arity;

    public String getName() {
        return this.name;
    }

    public int getArity() {
        return this.arity;
    }

    public Constructor(String name, int arity) {
        assert name != null;
        assert arity >= 0;

        this.name = name;
        this.arity = arity;
    }

    @Override
    public String toString() {
        return this.name + "`" + this.arity;
    }
}
