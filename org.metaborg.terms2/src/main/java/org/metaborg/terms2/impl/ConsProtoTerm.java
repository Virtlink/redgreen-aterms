package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsProtoTerm extends ProtoTerm {

    private final IConstructor constructor;

    public IConstructor getConstructor() {
        return this.constructor;
    }

    protected ConsProtoTerm(TermFactory factory, IConstructor constructor, List<? extends ProtoTerm> subterms) {
        super(factory, subterms);

        assert factory != null;
        assert constructor != null;
        assert subterms != null;
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

    @Override
    public ConsProtoTerm withChild(int index, IProtoTerm newChild) {
        if (index < 0 || index >= this.getChildren().size())
            throw new IllegalArgumentException("The index is out of range.");
        if (!(newChild instanceof ProtoTerm))
            throw new IllegalArgumentException("The child must extend ProtoTerm.");

        return create(this.getFactory(), this.constructor, ListExt.withElement(this.getChildren(), index, newChild));
    }

    @Override
    ConsTerm createTerm(SyntaxTree tree, /* NULLABLE */ Term parent, int parentIndex, int offset) {
        return new ConsTerm(tree, this, parent, parentIndex, offset);
    }

    public String toString() {
        return this.constructor.getName() + "(" + this.getChildren().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ")";
    }
}
