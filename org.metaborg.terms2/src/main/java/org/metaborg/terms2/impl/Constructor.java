package org.metaborg.terms2.impl;

import org.metaborg.terms2.IConstructor;

/**
 * A term constructor.
 */
public class Constructor implements IConstructor {

    private final String name;
    private final int arity;

    /**
     * Gets the name of the constructor.
     *
     * @return The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the arity of the constructor.
     *
     * @return The arity.
     */
    public int getArity() {
        return this.arity;
    }

    /**
     * Initializes a new instance of the {@see Constructor} class.
     *
     * @param name The name of the constructor.
     * @param arity The arity of the constructor.
     */
    public Constructor(String name, int arity) {
        if (arity < 0)
            throw new IllegalArgumentException("The arity must be positive or zero.");

        this.name = name;
        this.arity = arity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name + "`" + this.arity;
    }
}
