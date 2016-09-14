package org.metaborg.terms2;

import org.metaborg.terms2.impl.ConsTerm;
import org.metaborg.terms2.impl.IntTerm;
import org.metaborg.terms2.impl.StringTerm;
import org.metaborg.terms2.impl.Term;

public class TestTermVisitor implements ITermVisitor {

    @Override
    public void visitConsTerm(ConsTerm term) {
        System.out.print(term.getConstructor().getName() + "(");
        if (!term.getChildren().isEmpty()) {
            term.getChildren().get(0).accept(this);
            for (int i = 1; i < term.getChildren().size(); i++) {
                System.out.print(", ");
                ITerm child = term.getChildren().get(i);
                child.accept(this);
            }
        }
        System.out.print(")");
        printAnnotations(term);
    }

    @Override
    public void visitIntTerm(IntTerm term) {
        System.out.print(term.getValue());
        printAnnotations(term);
    }

    @Override
    public void visitStringTerm(StringTerm term) {
        System.out.print("\"" + term.getValue() + "\"");    // TODO: Escape
        printAnnotations(term);
    }

    private void printAnnotations(Term term) {
        if (term.getAnnotations().isEmpty())
            return;
        System.out.print("{");
        term.getAnnotations().get(0).accept(this);
        for (int i = 1; i < term.getAnnotations().size(); i++) {
            System.out.print(", ");
            ITerm child = term.getAnnotations().get(i);
            child.accept(this);
        }
        System.out.print("}");
    }
}