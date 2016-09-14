package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import javax.annotation.Nullable;
import java.util.Collections;

/**
 * An integer proto-term.
 */
public final class IntProtoTerm extends ProtoTerm {

    private final int value;
    private final int width;

    /**
     * Gets the value of the term.
     *
     * @return The value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Initializes a new instance of the {@see IntProtoTerm} class.
     *
     * @param factory The term factory.
     * @param value The value.
     * @param width The width, in characters.
     */
    private IntProtoTerm(TermFactory factory, int value, int width) {
        super(factory, Collections.emptyList());

        if (width < 0)
            throw new IllegalArgumentException("The width must be positive or null.");

        this.value = value;
        this.width = width;
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @param width The width of the term.
     * @return The created term.
     */
    public static IntProtoTerm create(TermFactory factory, int value, int width) {
        if (width < 0)
            throw new IllegalArgumentException("The width must be positive or null.");

        return factory.intern(new IntProtoTerm(factory, value, width));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @return The created term.
     */
    public static IntProtoTerm create(TermFactory factory, int value) {
        return create(factory, value, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntProtoTerm withChild(int index, IProtoTerm newChild) {
        throw new IllegalArgumentException("The index is out of range.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    IntTerm createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        return new IntTerm(tree, this, parent, parentIndex, parentAnnotation, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
