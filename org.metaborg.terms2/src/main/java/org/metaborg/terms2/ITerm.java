package org.metaborg.terms2;

import javax.annotation.Nullable;
import java.util.List;

/**
 * A red term, that is, a term that contains data derived from its green term and its parent terms.
 *
 * Red trees are build top-down on demand, and thrown away at every edit. Each term has weak references
 * to their children, such that the red children may be garbage collected when they are not currently reachable.
 *
 * Red terms are immutable, thread-safe, but not shared.
 */
public interface ITerm {

    /**
     * Gets the syntax tree to which this term belongs.
     *
     * @return The syntax tree.
     */
    ISyntaxTree getTree();

    /**
     * Gets the parent term.
     *
     * @return The parent term; or null when there is none.
     */
    @Nullable
    ITerm getParent();

    /**
     * Gets the children of the term.
     *
     * @return The children of the term.
     */
    List<? extends ITerm> getChildren();

    /**
     * Gets the offset of this term.
     *
     * @return The number of input characters this term is from the start of the input.
     */
    int getOffset();

    /**
     * Gets the width of this term.
     *
     * @return The number of input characters covered by this term,
     * which may be 0 for artificially created terms.
     */
    int getWidth();

    /**
     * Gets the prototype of this term.
     *
     * @return The prototype.
     */
    IProtoTerm getPrototype();

    /**
     * Returns a new term with the specified child term replaced.
     *
     * Use the {@see org.metaborg.terms2.ITermFactory} to create new proto-terms.
     *
     * @param index The zero-based index of the child to replace.
     * @param newChild The new child.
     * @return The new term.
     */
    ITerm withChild(int index, IProtoTerm newChild);

    /**
     * Accepts the specified visitor.
     *
     * @param visitor The visitor to accept.
     */
    void accept(ITermVisitor visitor);

}
