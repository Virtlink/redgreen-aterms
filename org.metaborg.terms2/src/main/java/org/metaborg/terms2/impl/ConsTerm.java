package org.metaborg.terms2.impl;

import org.metaborg.terms2.IConstructor;
import org.metaborg.terms2.ITermVisitor;

public class ConsTerm extends Term {

    @Override
    public ConsProtoTerm getPrototype() {
        return (ConsProtoTerm)super.getPrototype();
    }

    public IConstructor getConstructor() {
        return this.getPrototype().getConstructor();
    }

    ConsTerm(SyntaxTree tree, ConsProtoTerm prototype, Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }

    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitConsTerm(this);
    }

}
