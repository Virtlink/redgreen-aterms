package org.metaborg.terms2.impl;

// This would be generated.

public final class NameAgeTerm extends ConsTerm {

    @Override
    public NameAgeProtoTerm getPrototype() {
        return (NameAgeProtoTerm)super.getPrototype();
    }

    public StringTerm getNameTerm() {
        return (StringTerm)this.getChildren().get(0);
    }

    public String getName() { return this.getPrototype().getName(); }

    public NameAgeTerm setNameTerm(StringProtoTerm value) {
        return (NameAgeTerm)this.withChild(0, value);
    }

    public NameAgeTerm setName(String value) {
        return setNameTerm(StringProtoTerm.create(this.getPrototype().getFactory(), value));
    }

    public IntTerm getAgeTerm() {
        return (IntTerm)this.getChildren().get(1);
    }

    public int getAge() { return this.getPrototype().getAge(); }

    public NameAgeTerm setAgeTerm(IntProtoTerm value) {
        return (NameAgeTerm)this.withChild(1, value);
    }

    public NameAgeTerm setAge(int value) {
        return setAgeTerm(IntProtoTerm.create(this.getPrototype().getFactory(), value));
    }

    NameAgeTerm(SyntaxTree tree, NameAgeProtoTerm prototype, Term parent, int parentIndex, int offset) {
        super(tree, prototype, parent, parentIndex, offset);
    }


}
