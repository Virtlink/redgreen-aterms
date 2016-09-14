package org.metaborg.terms2.impl;

import org.metaborg.terms2.ITermVisitor;

public class IntTerm extends Term {

    @Override
    public IntProtoTerm getPrototype() { return (IntProtoTerm)super.getPrototype(); }

    public int getValue() {
        return this.getPrototype().getValue();
    }

    IntTerm(SyntaxTree tree, IntProtoTerm prototype, Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }

    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitIntTerm(this);
    }

}
