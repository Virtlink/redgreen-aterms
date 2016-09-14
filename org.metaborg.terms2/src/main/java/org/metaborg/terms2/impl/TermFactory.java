package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

public class TermFactory implements ITermFactory {

    @Override
    public <T extends IProtoTerm> T intern(T prototype) {
        return prototype;
    }
}
