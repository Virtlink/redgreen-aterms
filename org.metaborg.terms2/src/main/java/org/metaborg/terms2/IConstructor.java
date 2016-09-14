package org.metaborg.terms2;

/**
 * A term constructor.
 */
public interface IConstructor {

    /**
     * Gets the name of the constructor.
     *
     * @return The name of the constructor.
     */
    String getName();

    /**
     * Gets the arity of the constructor.
     *
     * @return The number of subterms of the constructor.
     */
    int getArity();

}
