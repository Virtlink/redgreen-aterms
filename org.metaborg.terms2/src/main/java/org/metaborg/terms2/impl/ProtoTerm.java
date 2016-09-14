package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;

import java.util.List;

public abstract class ProtoTerm implements IProtoTerm {

    private final TermFactory factory;
    private final List<? extends ProtoTerm> children;
    private final int width;

    public TermFactory getFactory() {
        return this.factory;
    }

    public List<? extends ProtoTerm> getChildren() {
        return this.children;
    }

    public int getWidth() {
        return this.width;
    }

    ProtoTerm(TermFactory factory, List<? extends ProtoTerm> children) {
        assert factory != null;
        assert children != null;

        this.factory = factory;
        this.children = children;   // TODO: Safety copy.
        this.width = calculateWidth(this.children);
    }

    private int calculateWidth(List<? extends ProtoTerm> children) {
        return children.stream().mapToInt(ProtoTerm::getWidth).sum();
    }

    @Override
    public abstract ProtoTerm withChild(int index, IProtoTerm newChild);

    /* package */ abstract Term createTerm(SyntaxTree tree, /* NULLABLE */ Term parent, int parentIndex, int offset);

    @Override
    public abstract String toString();
}
