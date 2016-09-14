package org.metaborg.terms2.impl;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * A lazily created list of subterms.
 */
public final class SubtermList extends AbstractList<Term> {

    private final Term owner;
    private final WeakReference<Term>[] children;

    @Override
    public int size() {
        return this.children.length;
    }

    @Override
    public Term get(int index) {
        /* NULLABLE */ WeakReference<Term> ref = children[index];
        Term term = ref != null ? ref.get() : null;
        if (term == null) {
            ProtoTerm protoChild = this.owner.getPrototype().getChildren().get(index);
            term = protoChild.createTerm(owner.getTree(), owner, index, getOffset(index));
            children[index] = new WeakReference(term);
        }
        return term;
    }

    private int getOffset(int index) {
        int offset = this.owner.getOffset();
        for (int i = 0; i < index; i++) {
            offset += this.owner.getPrototype().getChildren().get(index).getWidth();
        }
        assert this.owner.getOffset() <= offset;
        assert offset <= this.owner.getOffset() + this.owner.getPrototype().getWidth();
        return offset;
    }

    SubtermList(Term owner) {
        assert owner != null;

        //noinspection unchecked
        this.children = (WeakReference<Term>[])new WeakReference<?>[owner.getPrototype().getChildren().size()];
        this.owner = owner;
    }
}
