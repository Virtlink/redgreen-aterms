package org.metaborg.terms2.impl;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * A lazily created list of subterms.
 */
/* package */ abstract class SubtermList extends AbstractList<Term> {

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
        @Nullable WeakReference<Term> ref = this.children[index];
        @Nullable Term term = ref != null ? ref.get() : null;
        if (term == null) {
            ProtoTerm protoChild = getSubterms(this.owner.getPrototype()).get(index);
            term = createSubterm(protoChild, this.owner.getTree(), this.owner, index);
            this.children[index] = new WeakReference<>(term);
        }
        return term;
    }

    /**
     * Initializes a new instance of the {@see SubtermList} class.
     *
     * @param owner The owner of the list.
     */
    /* package */ SubtermList(Term owner) {
        //noinspection unchecked
        this.children = (WeakReference<Term>[])new WeakReference<?>[getSubterms(owner.getPrototype()).size()];
        this.owner = owner;
    }

    /**
     * Gets the list of prototype terms that this list shadows.
     *
     * @param prototype The prototype.
     * @return The list of prototype subterms.
     */
    protected abstract List<? extends ProtoTerm> getSubterms(ProtoTerm prototype);

    /**
     * Creates a subterm for a prototype subterm.
     * @param prototype The prototype subterm.
     * @param tree The syntax tree.
     * @param owner The owner.
     * @param index The zero-based index.
     * @return The created subterm.
     */
    protected abstract Term createSubterm(ProtoTerm prototype, SyntaxTree tree, Term owner, int index);
}
