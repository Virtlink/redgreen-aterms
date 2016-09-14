package org.metaborg.terms2.impl;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * A lazily created list of subterms.
 */
/* package */ final class SubtermList extends AbstractList<Term> {

    private final Term owner;
    private final WeakReference<Term>[] children;

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.children.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Term get(int index) {
        @Nullable WeakReference<Term> ref = children[index];
        @Nullable Term term = ref != null ? ref.get() : null;
        if (term == null) {
            ProtoTerm protoChild = this.owner.getPrototype().getChildren().get(index);
            term = protoChild.createTerm(owner.getTree(), owner, index, getOffset(index));
            children[index] = new WeakReference<>(term);
        }
        return term;
    }

    /**
     * Gets the offset of the child with the specified index.
     *
     * @param index The zero-based index.
     * @return The zero-based offset of the child.
     */
    private int getOffset(int index) {
        int offset = this.owner.getOffset();
        for (int i = 0; i < index; i++) {
            offset += this.owner.getPrototype().getChildren().get(index).getWidth();
        }
        assert this.owner.getOffset() <= offset;
        assert offset <= this.owner.getOffset() + this.owner.getPrototype().getWidth();
        return offset;
    }

    /**
     * Initializes a new instance of the {@see SubtermList} class.
     *
     * @param owner The owner of the list.
     */
    /* package */ SubtermList(Term owner) {
        //noinspection unchecked
        this.children = (WeakReference<Term>[])new WeakReference<?>[owner.getPrototype().getChildren().size()];
        this.owner = owner;
    }
}
