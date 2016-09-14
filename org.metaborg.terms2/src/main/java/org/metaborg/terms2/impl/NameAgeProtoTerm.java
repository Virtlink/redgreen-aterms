package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

// This would be generated code.

/**
 * A name-age pair.
 */
public final class NameAgeProtoTerm extends ConsProtoTerm {

    public static final IConstructor CONSTRUCTOR = new Constructor("NameAge", 2);

    /**
     * Gets the name term.
     *
     * @return The name term.
     */
    public StringProtoTerm getNameTerm() {
        return (StringProtoTerm)this.getChildren().get(0);
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() {
        return getNameTerm().getValue();
    }

    /**
     * Sets the name term.
     *
     * @param value The name term.
     * @return The resulting new term.
     */
    public NameAgeProtoTerm setNameTerm(StringProtoTerm value) {
        return this.withChild(0, value);
    }

    /**
     * Sets the name.
     *
     * @param value The name.
     * @return The resulting new term.
     */
    public NameAgeProtoTerm setName(String value) {
        return setNameTerm(StringProtoTerm.create(this.getFactory(), value));
    }

    /**
     * Gets the age term.
     *
     * @return The age term.
     */
    public IntProtoTerm getAgeTerm() {
        return (IntProtoTerm)this.getChildren().get(1);
    }

    /**
     * Gets the age.
     *
     * @return The age.
     */
    public int getAge() {
        return getAgeTerm().getValue();
    }

    /**
     * Sets the age term.
     *
     * @param value The age term.
     * @return The resulting new term.
     */
    public NameAgeProtoTerm setAgeTerm(IntProtoTerm value) {
        return this.withChild(1, value);
    }

    /**
     * Sets the age.
     *
     * @param value The age.
     * @return The resulting new term.
     */
    public NameAgeProtoTerm setAge(int value) {
        return setAgeTerm(IntProtoTerm.create(this.getFactory(), value));
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param factory The term factory.
     * @param subterms The subterms.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    NameAgeTerm createTerm(SyntaxTree tree, @Nullable Term parent, int parentIndex, boolean parentAnnotation, int offset) {
        return new NameAgeTerm(tree, this, parent, parentIndex, parentAnnotation, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NameAgeProtoTerm withChild(int index, IProtoTerm newChild) {
        if (index < 0 || index >= this.getChildren().size())
            throw new IllegalArgumentException("The index is out of range.");
        if (!(newChild instanceof ProtoTerm))
            throw new IllegalArgumentException("The child must extend ProtoTerm.");

        return create(this.getFactory(), ListExt.withElement(this.getChildren(), index, newChild));
    }
}
