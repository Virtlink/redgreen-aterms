package org.metaborg.terms2.impl;

import org.metaborg.terms2.IConstructor;
import org.metaborg.terms2.ITermVisitor;

import javax.annotation.Nullable;

/**
 * A constructor term.
 */
public class ConsTerm extends Term {

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsProtoTerm getPrototype() {
        return (ConsProtoTerm)super.getPrototype();
    }

    /**
     * Gets the constructor.
     *
     * @return The constructor.
     */
    public IConstructor getConstructor() {
        return this.getPrototype().getConstructor();
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
    /* package */ ConsTerm(SyntaxTree tree, ConsProtoTerm prototype, @Nullable Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitConsTerm(this);
    }

}
