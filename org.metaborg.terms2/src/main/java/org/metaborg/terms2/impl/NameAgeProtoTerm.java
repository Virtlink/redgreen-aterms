package org.metaborg.terms2.impl;

import org.metaborg.terms2.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
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
     * @param children The child terms.
     * @param annotations The annotation terms.
     */
    private NameAgeProtoTerm(TermFactory factory, List<? extends ProtoTerm> children, List<? extends ProtoTerm> annotations) {
        super(factory, CONSTRUCTOR, children, annotations);
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param children The child terms.
     * @param annotations The annotation terms.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, List<? extends IProtoTerm> children, List<? extends IProtoTerm> annotations) {
        if (children.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The child terms must be of type ProtoTerm.");
        if (annotations.stream().anyMatch(o -> !(o instanceof ProtoTerm)))
            throw new IllegalArgumentException("The annotation terms must be of type ProtoTerm.");

        //noinspection unchecked
        return factory.intern(new NameAgeProtoTerm(factory, (List<ProtoTerm>)children, (List<ProtoTerm>)annotations));
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param children The child terms.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, List<? extends IProtoTerm> children) {
        return create(factory, children, Collections.emptyList());
    }

    /**
     * Creates a new instance of this term.
     *
     * @param factory The term factory to use.
     * @param children The child terms.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, IProtoTerm... children) {
        return create(factory, Arrays.asList(children));
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
     * @param name The name.
     * @param age The age.
     * @return The created term.
     */
    public static NameAgeProtoTerm create(TermFactory factory, String name, int age) {
        return create(factory, StringProtoTerm.create(factory, name), IntProtoTerm.create(factory, age));
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
