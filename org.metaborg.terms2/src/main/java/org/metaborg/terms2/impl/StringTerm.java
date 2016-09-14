package org.metaborg.terms2.impl;

import org.metaborg.terms2.ITermVisitor;

public class StringTerm extends Term {

    @Override
    public StringProtoTerm getPrototype() { return (StringProtoTerm)super.getPrototype(); }

    public String getValue() {
        return this.getPrototype().getValue();
    }

    StringTerm(SyntaxTree tree, StringProtoTerm prototype, Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }

    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitStringTerm(this);
    }

}
