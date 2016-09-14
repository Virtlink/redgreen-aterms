package org.metaborg.terms2;

import org.metaborg.terms2.impl.ConsTerm;
import org.metaborg.terms2.impl.IntTerm;
import org.metaborg.terms2.impl.StringTerm;

/**
 * A term visitor.
 */
public interface ITermVisitor {

    /**
     * Visits a term.
     *
     * @param term The term.
     */
    default void visitTerm(ITerm term) {
        // Does nothing by default.
    }

    /**
     * Visits a constructor term.
     *
     * @param term The term.
     */
    void visitConsTerm(ConsTerm term);

    /**
     * Visits an integer term.
     *
     * @param term The term.
     */
    void visitIntTerm(IntTerm term);

    /**
     * Visits a string term.
     *
     * @param term The term.
     */
    void visitStringTerm(StringTerm term);

}
