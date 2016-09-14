package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import java.util.Arrays;
import java.util.List;

// This would be generated code.

/**
 * A name-age pair.
 */
public final class NameAgeProtoTerm extends ConsProtoTerm {

    public static final IConstructor CONSTRUCTOR = new Constructor("NameAge", 2);

    public StringProtoTerm getNameTerm() {
        return (StringProtoTerm)this.getChildren().get(0);
    }

    public String getName() {
        return getNameTerm().getValue();
    }

    public NameAgeProtoTerm setNameTerm(StringProtoTerm value) {
        return this.withChild(0, value);
    }

    public NameAgeProtoTerm setName(String value) {
        return setNameTerm(StringProtoTerm.create(this.getFactory(), value));
    }

    public IntProtoTerm getAgeTerm() {
        return (IntProtoTerm)this.getChildren().get(1);
    }

    public int getAge() {
        return getAgeTerm().getValue();
    }

    public NameAgeProtoTerm setAgeTerm(IntProtoTerm value) {
        return this.withChild(1, value);
    }

    public NameAgeProtoTerm setAge(int value) {
        return setAgeTerm(IntProtoTerm.create(this.getFactory(), value));
    }

    private NameAgeProtoTerm(TermFactory factory, List<? extends ProtoTerm> subterms) {
        super(factory, CONSTRUCTOR, subterms);
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param name The name.
     * @param age The age.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, String name, int age) {
        return create(factory, StringProtoTerm.create(factory, name), IntProtoTerm.create(factory, age));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param name The name.
     * @param age The age.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, StringProtoTerm name, IntProtoTerm age) {
        return create(factory, Arrays.asList(name, age));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param subterms The subterms.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, IProtoTerm... subterms) {
        return create(factory, Arrays.asList(subterms));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param subterms The subterms.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, List<? extends IProtoTerm> subterms) {
        if (subterms.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The subterms must be of type ProtoTerm.");

        //noinspection unchecked
        return factory.intern(new NameAgeProtoTerm(factory, (List<ProtoTerm>)subterms));
    }

    @Override
    NameAgeTerm createTerm(SyntaxTree tree, Term parent, int parentIndex, int offset) {
        return new NameAgeTerm(tree, this, parent, parentIndex, offset);
    }

    @Override
    public NameAgeProtoTerm withChild(int index, IProtoTerm newChild) {
        if (index < 0 || index >= this.getChildren().size())
            throw new IllegalArgumentException("The index is out of range.");
        if (!(newChild instanceof ProtoTerm))
            throw new IllegalArgumentException("The child must extend ProtoTerm.");

        return create(this.getFactory(), ListExt.withElement(this.getChildren(), index, newChild));
    }
}
