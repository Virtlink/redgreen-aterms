package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import java.util.Collections;

public class IntProtoTerm extends ProtoTerm {

    private final int value;
    private final int width;

    public int getValue() {
        return this.value;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    private IntProtoTerm(TermFactory factory, int value, int width) {
        super(factory, Collections.emptyList());

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

    @Override
    public IntProtoTerm withChild(int index, IProtoTerm newChild) {
        throw new IllegalArgumentException("The index is out of range.");
    }

    @Override
    IntTerm createTerm(SyntaxTree tree, /* NULLABLE */ Term parent, int parentIndex, int offset) {
        return new IntTerm(tree, this, parent, parentIndex, offset);
    }

    public String toString() {
        return Integer.toString(this.value); // TODO: Culturally invariant int presentation
    }
}
