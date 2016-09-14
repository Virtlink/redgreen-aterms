package org.metaborg.terms2.impl;

import org.metaborg.terms2.ISyntaxTree;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;

/**
 * A syntax tree.
 */
public class SyntaxTree implements ISyntaxTree {

    private final ProtoTerm protoRoot;
    @Nullable private WeakReference<Term> root;
    @Nullable private final String filename;

    /**
     * {@inheritDoc}
     */
    @Override
    public Term getRoot() {
        @Nullable Term rootTerm = this.root != null ? this.root.get() : null;
        if (rootTerm == null) {
            rootTerm = this.protoRoot.createTerm(this, null, 0, false, 0);
            this.root = new WeakReference<>(rootTerm);
        }
        return rootTerm;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getFilename() {
        return this.filename;
    }

    /**
     * Initializes a new instance of the {@see SyntaxTree} class.
     *
     * @param filename The filename of the file described by this syntax tree; or null.
     * @param root The root proto-term.
     */
    public SyntaxTree(@Nullable String filename, ProtoTerm root) {
        this.filename = filename;
        this.protoRoot = root;
    }

    /**
     * Creates a copy of this syntax tree with the specified root.
     *
     * @param newRoot The new root of the syntax tree.
     * @return The created syntax tree.
     */
    SyntaxTree withRoot(ProtoTerm newRoot) {
        return new SyntaxTree(this.filename, newRoot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getFilename() + ": " + this.getRoot().toString();
    }
}
