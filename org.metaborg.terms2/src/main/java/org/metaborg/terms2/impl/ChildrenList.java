package org.metaborg.terms2.impl;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.List;

/**
 * A lazily created list of subterms.
 */
/* package */ final class ChildrenList extends SubtermList {

    /**
     * Initializes a new instance of the {@see SubtermList} class.
     *
     * @param owner The owner of the list.
     */
    /* package */ ChildrenList(Term owner) {
        super(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends ProtoTerm> getSubterms(ProtoTerm prototype) {
        return prototype.getChildren();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Term createSubterm(ProtoTerm prototype, SyntaxTree tree, Term owner, int index) {
        return prototype.createTerm(tree, owner, index, false, getOffset(owner, index));
    }

    /**
     * Gets the offset of the child with the specified index.
     *
     * @param index The zero-based index.
     * @return The zero-based offset of the child.
     */
    private int getOffset(Term owner, int index) {
        int offset = owner.getOffset();
        for (int i = 0; i < index; i++) {
            offset += getSubterms(owner.getPrototype()).get(index).getWidth();
        }
        assert owner.getOffset() <= offset;
        assert offset <= owner.getOffset() + owner.getPrototype().getWidth();
        return offset;
    }
}
