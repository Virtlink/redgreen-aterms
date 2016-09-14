package org.metaborg.terms2;

import org.metaborg.terms2.impl.ConsTerm;
import org.metaborg.terms2.impl.IntTerm;
import org.metaborg.terms2.impl.StringTerm;

public class OffsetTermVisitor implements ITermVisitor {

    @Override
    public void visitTerm(ITerm term) {

    }

    @Override
    public void visitConsTerm(ConsTerm term) {
        System.out.print(term.getConstructor().getName() + "(");
        if (term.getChildren().size() > 0) {
            term.getChildren().get(0).accept(this);
            for (int i = 1; i < term.getChildren().size(); i++) {
                System.out.print(", ");
                ITerm child = term.getChildren().get(i);
                child.accept(this);
            }
        }
        System.out.print("){" + term.getTree().getFilename() + "@" + term.getOffset() + "+" + term.getWidth() + "}");
    }

    @Override
    public void visitIntTerm(IntTerm term) {
        System.out.print(term.getValue() + "{" + term.getTree().getFilename() + "@" + term.getOffset() + "+" + term.getWidth() + "}");
    }

    @Override
    public void visitStringTerm(StringTerm term) {
        System.out.print("\"" + term.getValue() + "\"" + "{" + term.getTree().getFilename() + "@" + term.getOffset() + "+" + term.getWidth() + "}");    // TODO: Escape
    }
}