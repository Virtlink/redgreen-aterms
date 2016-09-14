package org.metaborg.terms2.impl;

import java.util.List;

/**
 * A lazily created list of annotation terms.
 */
/* package */ final class AnnotationList extends SubtermList {

    /**
     * Initializes a new instance of the {@see AnnotationList} class.
     *
     * @param owner The owner of the list.
     */
    /* package */ AnnotationList(Term owner) {
        super(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends ProtoTerm> getSubterms(ProtoTerm prototype) {
        return prototype.getAnnotations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Term createSubterm(ProtoTerm prototype, SyntaxTree tree, Term owner, int index) {
        return prototype.createTerm(tree, owner, index, true, 0);
    }
}
