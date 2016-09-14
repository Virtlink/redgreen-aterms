package org.metaborg.terms2.impl;

import org.metaborg.terms2.IProtoTerm;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A proto-term (i.e. green term).
 */
public abstract class ProtoTerm implements IProtoTerm {

    private final TermFactory factory;
    private final List<? extends ProtoTerm> children;
    private final List<? extends ProtoTerm> annotations;
    private final int width;

    /**
     * Gets the factory that created this term.
     *
     * @return The factory.
     */
    public TermFactory getFactory() {
        return this.factory;
    }

    /**
     * Gets the child terms.
     *
     * @return The child terms.
     */
    public List<? extends ProtoTerm> getChildren() {
        return this.children;
    }

    /**
     * Gets the annotation terms.
     *
     * @return The annotation terms.
     */
    public List<? extends ProtoTerm> getAnnotations() {
        return this.annotations;
    }

    /**
     * Gets the width of the term.
     *
     * @return The width of the term.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param factory The term factory.
     * @param children The subterms.
     */
    /* package */ ProtoTerm(TermFactory factory, List<? extends ProtoTerm> children, List<? extends ProtoTerm> annotations) {
        this.factory = factory;
        this.children = children;   // TODO: Safety copy.
        this.annotations = annotations;   // TODO: Safety copy.
        this.width = calculateWidth(this.children);
    }

    /**
     * Calculates the width of the term based on its children.
     *
     * @param children The children.
     * @return The width.
     */
    private int calculateWidth(List<? extends ProtoTerm> children) {
        return children.stream().mapToInt(ProtoTerm::getWidth).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ProtoTerm withChild(int index, IProtoTerm newChild);

    /**
     * Creates the associated red term.
     *
     * @param tree The syntax tree.
     * @param parent The parent term; or null.
     * @param parentIndex The parent index; or 0.
     * @param parentAnnotation Whether the parent has this term as a child (false) or an annotation (true).
     * @param offset The offset; or 0.
     * @return The term.
     */
    /* package */ abstract Term createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of the annotations.
     *
     * @return The string representation.
     */
    protected String annotationsToString() {
        if (this.getAnnotations().isEmpty())
            return "";

        return "{" + this.getAnnotations().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + "}";
    }
}
