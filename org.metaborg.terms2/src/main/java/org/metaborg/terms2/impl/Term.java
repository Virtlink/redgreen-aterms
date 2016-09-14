package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;
import org.metaborg.terms2.ITerm;
import org.metaborg.terms2.ITermVisitor;

import java.util.List;

public abstract class Term implements ITerm {

    private final SyntaxTree tree;
    private final Term parent;
    private final int parentIndex;
    private final int offset;
    private final ProtoTerm prototype;
    private final SubtermList children;

    @Override
    public SyntaxTree getTree() {
        return this.tree;
    }

    @Override
    public Term getParent() {
        return this.parent;
    }

    @Override
    public List<? extends Term> getChildren() {
        return this.children;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public int getWidth() {
        return this.prototype.getWidth();
    }

    @Override
    public ProtoTerm getPrototype() {
        return this.prototype;
    }

    Term(SyntaxTree tree, ProtoTerm prototype, /* NULLABLE */ Term parent, int parentIndex, int offset) {
        assert tree != null;
        assert prototype != null;
        assert parentIndex >= 0;
        assert offset >= 0;

        this.tree = tree;
        this.prototype = prototype;
        this.parent = parent;
        this.parentIndex = parentIndex;
        this.offset = offset;
        this.children = new SubtermList(this);
    }

    @Override
    public Term withChild(int index, IProtoTerm newChild) {
        if (!(newChild instanceof ProtoTerm))
            throw new IllegalArgumentException("The child term must extend the ProtoTerm class.");

        ProtoTerm newPrototype = this.prototype.withChild(index, newChild);

        if (this.parent != null) {
            Term newParent = this.parent.withChild(this.parentIndex, newPrototype);
            return newParent.getChildren().get(this.parentIndex);
        } else {
            SyntaxTree newSyntaxTree = this.tree.withRoot(newPrototype);
            return newSyntaxTree.getRoot();
        }
    }

    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitTerm(this);
    }
}
