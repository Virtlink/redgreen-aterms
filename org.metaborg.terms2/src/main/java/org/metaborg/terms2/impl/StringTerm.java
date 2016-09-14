package org.metaborg.terms2.impl;

import org.metaborg.terms2.ITermVisitor;

import javax.annotation.Nullable;

/**
 * A string term.
 */
public final class StringTerm extends Term {

    /**
     * {@inheritDoc}
     */
    @Override
    public StringProtoTerm getPrototype() { return (StringProtoTerm)super.getPrototype(); }

    /**
     * Gets the value.
     *
     * @return The value.
     */
    public String getValue() {
        return this.getPrototype().getValue();
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param tree The syntax tree.
     * @param prototype The prototype.
     * @param parent The parent term, or null.
     * @param parentIndex The parent term index, or zero.
     * @param parentAnnotation Whether the parent has this term as a child (false) or an annotation (true).
     * @param offset The term offset, or zero.
     */
    /* package */ StringTerm(SyntaxTree tree, StringProtoTerm prototype, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        super(tree, prototype, parent, parentIndex, parentAnnotation, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitStringTerm(this);
    }

}
