package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import javax.annotation.Nullable;
import java.util.Arrays;
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
     * @param subterms The subterms.
     */
    protected ConsProtoTerm(TermFactory factory, IConstructor constructor, List<? extends ProtoTerm> subterms) {
        super(factory, subterms);

        assert subterms.size() == constructor.getArity();

        this.constructor = constructor;
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param constructor The constructor.
     * @param subterms The subterms.
     * @return The created term.
     */
    public static ConsProtoTerm create(TermFactory factory, IConstructor constructor, List<? extends IProtoTerm> subterms) {
        if (subterms.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The subterms must be of type ProtoTerm.");

        //noinspection unchecked
        return factory.intern(new ConsProtoTerm(factory, constructor, (List<ProtoTerm>)subterms));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param constructor The constructor.
     * @param subterms The subterms.
     * @return The created term.
     */
    public static ConsProtoTerm create(TermFactory factory, IConstructor constructor, IProtoTerm... subterms) {
        return create(factory, constructor, Arrays.asList(subterms));
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

        return create(this.getFactory(), this.constructor, ListExt.withElement(this.getChildren(), index, newChild));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    ConsTerm createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, int offset) {
        return new ConsTerm(tree, this, parent, parentIndex, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.constructor.getName() + "(" + this.getChildren().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ")";
    }
}
