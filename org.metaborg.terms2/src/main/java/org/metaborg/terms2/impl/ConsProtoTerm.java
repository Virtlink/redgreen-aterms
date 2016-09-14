package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A constructor proto-term.
 */
public class ConsProtoTerm extends ProtoTerm {

    private final IConstructor constructor;

    /**
     * Gets the constructor.
     *
     * @return The constructor.
     */
    public IConstructor getConstructor() {
        return this.constructor;
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param factory The term factory.
     * @param constructor The constructor.
     * @param children The child terms.
     * @param annotations The annotation terms.
     */
    protected ConsProtoTerm(TermFactory factory, IConstructor constructor, List<? extends ProtoTerm> children, List<? extends ProtoTerm> annotations) {
        super(factory, children, annotations);

        if (children.size() != constructor.getArity())
            throw new IllegalArgumentException("The number of children must be " + constructor.getArity());

        this.constructor = constructor;
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param constructor The constructor.
     * @param children The child terms.
     * @param annotations The annotation terms.
     * @return The created term.
     */
    public static ConsProtoTerm create(TermFactory factory, IConstructor constructor, List<? extends IProtoTerm> children, List<? extends IProtoTerm> annotations) {
        if (children.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The child terms must be of type ProtoTerm.");
        if (annotations.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The annotation terms must be of type ProtoTerm.");

        //noinspection unchecked
        return factory.intern(new ConsProtoTerm(factory, constructor, (List<ProtoTerm>)children, (List<ProtoTerm>)annotations));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param constructor The constructor.
     * @param children The child terms.
     * @return The created term.
     */
    public static ConsProtoTerm create(TermFactory factory, IConstructor constructor, IProtoTerm... children) {
        return create(factory, constructor, Arrays.asList(children), Collections.emptyList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsProtoTerm withChild(int index, IProtoTerm newChild) {
        if (index < 0 || index >= this.getChildren().size())
            throw new IllegalArgumentException("The index is out of range.");
        if (!(newChild instanceof ProtoTerm))
            throw new IllegalArgumentException("The child must extend ProtoTerm.");

        return create(this.getFactory(), this.constructor, ListExt.withElement(this.getChildren(), index, newChild), this.getAnnotations());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    ConsTerm createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        return new ConsTerm(tree, this, parent, parentIndex, parentAnnotation, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.constructor.getName() + "(" + this.getChildren().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ")" + annotationsToString();
    }
}
