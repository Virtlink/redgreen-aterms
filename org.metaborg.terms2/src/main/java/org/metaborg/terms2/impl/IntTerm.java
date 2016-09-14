package org.metaborg.terms2.impl;

import org.metaborg.terms2.ITermVisitor;

import javax.annotation.Nullable;

/**
 * An integer term.
 */
public class IntTerm extends Term {

    /**
     * {@inheritDoc}
     */
    @Override
    public IntProtoTerm getPrototype() { return (IntProtoTerm)super.getPrototype(); }

    /**
     * Gets the value.
     *
     * @return The value.
     */
    public int getValue() {
        return this.getPrototype().getValue();
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param tree The syntax tree.
     * @param prototype The prototype.
     * @param parent The parent term, or null.
     * @param parentIndex The parent term index, or zero.
     * @param offset The term offset, or zero.
     */
    /* package */ IntTerm(SyntaxTree tree, IntProtoTerm prototype, @Nullable Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitIntTerm(this);
    }

}
