package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * A string proto-term.
 */
public class StringProtoTerm extends ProtoTerm {

    private final String value;
    private final int width;

    /**
     * Gets the value.
     *
     * @return The value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Initializes a new instance of the {@see StringProtoTerm} class.
     *
     * @param factory The term factory.
     * @param value The value.
     * @param width The width, in characters.
     * @param annotations The annotation terms.
     */
    private StringProtoTerm(TermFactory factory, String value, int width, List<? extends ProtoTerm> annotations) {
        super(factory, Collections.emptyList(), annotations);

        this.value = value;
        this.width = width;
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @param width The width of the term.
     * @param annotations The annotation terms.
     * @return The created term.
     */
    public static StringProtoTerm create(TermFactory factory, String value, int width, List<? extends IProtoTerm> annotations) {
        if (annotations.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The annotation terms must be of type ProtoTerm.");

        //noinspection unchecked
        return factory.intern(new StringProtoTerm(factory, value, width, (List<ProtoTerm>)annotations));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @param width The width of the term.
     * @return The created term.
     */
    public static StringProtoTerm create(TermFactory factory, String value, int width) {
        return factory.intern(new StringProtoTerm(factory, value, width, Collections.emptyList()));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param value The value.
     * @return The created term.
     */
    public static StringProtoTerm create(TermFactory factory, String value) {
        return create(factory, value, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringProtoTerm withChild(int index, IProtoTerm newChild) {
        throw new IllegalArgumentException("The index is out of range.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    StringTerm createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        return new StringTerm(tree, this, parent, parentIndex, parentAnnotation, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "\"" + this.value + "\"" + annotationsToString(); // TODO: Escape string for presentation.
    }
}
