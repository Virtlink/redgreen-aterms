package org.metaborg.terms2.impl;

// This would be generated.

import javax.annotation.Nullable;

/**
 * The NameAge term.
 */
public final class NameAgeTerm extends ConsTerm {

    /**
     * {@inheritDoc}
     */
    @Override
    public NameAgeProtoTerm getPrototype() {
        return (NameAgeProtoTerm)super.getPrototype();
    }

    /**
     * Gets the name term.
     *
     * @return The name term.
     */
    public StringTerm getNameTerm() {
        return (StringTerm)this.getChildren().get(0);
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() { return this.getPrototype().getName(); }

    /**
     * Sets the name term.
     *
     * @param value The name term.
     * @return The resulting new term.
     */
    public NameAgeTerm setNameTerm(StringProtoTerm value) {
        return (NameAgeTerm)this.withChild(0, value);
    }

    /**
     * Sets the name.
     *
     * @param value The name.
     * @return The resulting new term.
     */
    public NameAgeTerm setName(String value) {
        return setNameTerm(StringProtoTerm.create(this.getPrototype().getFactory(), value));
    }

    /**
     * Gets the age term.
     *
     * @return The age term.
     */
    public IntTerm getAgeTerm() {
        return (IntTerm)this.getChildren().get(1);
    }

    /**
     * Gets the age.
     *
     * @return The age.
     */
    public int getAge() { return this.getPrototype().getAge(); }

    /**
     * Sets the age term.
     *
     * @param value The age term.
     * @return The resulting new term.
     */
    public NameAgeTerm setAgeTerm(IntProtoTerm value) {
        return (NameAgeTerm)this.withChild(1, value);
    }

    /**
     * Sets the age.
     *
     * @param value The age.
     * @return The resulting new term.
     */
    public NameAgeTerm setAge(int value) {
        return setAgeTerm(IntProtoTerm.create(this.getPrototype().getFactory(), value));
    }

    /**
     * Initializes a new instance of the {@see ConsProtoTerm} class.
     *
     * @param tree The syntax tree.
     * @param prototype The prototype.
     * @param parent The parent term, or null.
     * @param parentIndex The parent term index, or zero.
     * @param offset The term offset, or zero.
     */
    /* package */ NameAgeTerm(SyntaxTree tree, NameAgeProtoTerm prototype, @Nullable Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }


}
