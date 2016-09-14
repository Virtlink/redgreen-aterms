package org.metaborg.terms2;

import java.util.List;

/**
 * A green term, that is, a term that only contains local data: its own data and (data derived from) its children.
 *
 * Green trees are build bottom-up.
 *
 * Green terms are immutable, thread-safe, and can be maximally shared.
 */
public interface IProtoTerm {

    /**
     * Gets the factory used to create this term.
     *
     * @return The factory.
     */
    ITermFactory getFactory();

    /**
     * Gets the children of the term.
     *
     * @return The children of the term.
     */
    List<? extends IProtoTerm> getChildren();

    /**
     * Gets the width of the term.
     *
     * @return The number of input characters covered by this term,
     * which may be 0 for artificially created terms.
     */
    int getWidth();

    /**
     * Returns a new term with the specified child term replaced.
     *
     * Use the {@see org.metaborg.terms2.ITermFactory} to create new proto-terms.
     *
     * @param index The zero-based index of the child to replace.
     * @param newChild The new child.
     * @return The new term.
     */
    IProtoTerm withChild(int index, IProtoTerm newChild);

}
