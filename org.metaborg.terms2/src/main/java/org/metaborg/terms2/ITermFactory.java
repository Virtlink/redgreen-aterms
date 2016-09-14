package org.metaborg.terms2;

/**
 * Used to build green proto-terms.
 */
public interface ITermFactory {

    /**
     * Attempts to return a cached version of the same term.
     *
     * This method may or may not return a different instance of a term with the same values.
     *
     * @param prototype The proto term to intern.
     * @param <T> The type of term to intern.
     * @return The interned term; or the original term.
     */
    <T extends IProtoTerm> T intern(T prototype);

}
