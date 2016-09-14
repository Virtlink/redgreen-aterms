package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;

import java.util.Collections;

public class StringProtoTerm extends ProtoTerm {

    private final String value;
    private final int width;

    public String getValue() {
        return this.value;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    private StringProtoTerm(TermFactory factory, String value, int width) {
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
    public static StringProtoTerm create(TermFactory factory, String value, int width) {
        return factory.intern(new StringProtoTerm(factory, value, width));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @return The created term.
     */
    public static StringProtoTerm create(TermFactory factory, String value) {
        return create(factory, value, 0);
    }

    @Override
    public StringProtoTerm withChild(int index, IProtoTerm newChild) {
        throw new IllegalArgumentException("The index is out of range.");
    }

    @Override
    StringTerm createTerm(SyntaxTree tree, /* NULLABLE */ Term parent, int parentIndex, int offset) {
        return new StringTerm(tree, this, parent, parentIndex, offset);
    }

    public String toString() {
        return "\"" + this.value + "\""; // TODO: Escape string for presentation.
    }
}
