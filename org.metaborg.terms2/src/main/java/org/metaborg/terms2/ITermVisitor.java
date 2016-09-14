package org.metaborg.terms2;

import org.metaborg.terms2.impl.ConsTerm;
import org.metaborg.terms2.impl.IntTerm;
import org.metaborg.terms2.impl.StringTerm;

/**
 * A term visitor.
 */
public interface ITermVisitor {

    void visitTerm(ITerm term);

    void visitConsTerm(ConsTerm term);

    void visitIntTerm(IntTerm term);

    void visitStringTerm(StringTerm term);

}
