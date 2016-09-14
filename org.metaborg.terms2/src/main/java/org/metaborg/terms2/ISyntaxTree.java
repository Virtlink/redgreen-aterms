package org.metaborg.terms2;

import javax.annotation.Nullable;

/**
 * A syntax tree.
 *
 * Syntax trees are immutable and thread-safe.
 */
public interface ISyntaxTree {

    /**
     * Gets the root of the tree.
     *
     * @return The root term.
     */
    ITerm getRoot();

    /**
     * Gets the filename of the file described by this syntax tree.
     *
     * @return The filename; or null when not known.
     */
    @Nullable
    String getFilename();

}
