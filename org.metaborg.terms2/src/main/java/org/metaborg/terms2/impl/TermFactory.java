package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

/**
 * Default implementation of the term factory.
 */
public class TermFactory implements ITermFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IProtoTerm> T intern(T prototype) {
        return prototype;
    }
}
