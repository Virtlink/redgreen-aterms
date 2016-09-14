package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;
import org.metaborg.terms2.ITerm;
import org.metaborg.terms2.ITermVisitor;

import javax.annotation.Nullable;
import java.util.List;

/**
 * A term.
 */
public abstract class Term implements ITerm {

    private final SyntaxTree tree;
    @Nullable private final Term parent;        // The parent term.
    private final int parentIndex;              // The index of this term in the parent.
    private final boolean parentAnnotation;     // Whether this term is a child or an annotation.
    private final int offset;
    private final ProtoTerm prototype;
    private final SubtermList children;

    /**
     * {@inheritDoc}
     */
    @Override
    public SyntaxTree getTree() {
        return this.tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Term getParent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Term> getChildren() {
        return this.children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOffset() {
        return this.offset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.prototype.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProtoTerm getPrototype() {
        return this.prototype;
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
    /* package */ Term(SyntaxTree tree, ProtoTerm prototype, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        assert parentIndex >= 0;
        assert offset >= 0;

        this.tree = tree;
        this.prototype = prototype;
        this.parent = parent;
        this.parentIndex = parentIndex;
        this.parentAnnotation = parentAnnotation;
        this.offset = offset;
        this.children = new ChildrenList(this);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ITermVisitor visitor) {
        visitor.visitTerm(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getPrototype().toString();
    }
}
