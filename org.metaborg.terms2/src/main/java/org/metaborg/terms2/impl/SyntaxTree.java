package org.metaborg.terms2.impl;

import org.metaborg.terms2.ISyntaxTree;

import java.lang.ref.WeakReference;

public class SyntaxTree implements ISyntaxTree {

    private final ProtoTerm protoRoot;
    private WeakReference<Term> root;
    private final String filename;

    @Override
    public Term getRoot() {
        Term rootTerm = this.root != null ? this.root.get() : null;
        if (rootTerm == null) {
            rootTerm = this.protoRoot.createTerm(this, null, 0, 0);
            this.root = new WeakReference<>(rootTerm);
        }
        return rootTerm;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    public SyntaxTree(String filename, ProtoTerm root) {
        this.filename = filename;
        this.protoRoot = root;
    }

    SyntaxTree withRoot(ProtoTerm newRoot) {
        return new SyntaxTree(this.filename, newRoot);
    }
}
