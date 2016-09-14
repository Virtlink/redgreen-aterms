package org.metaborg.terms2;

import static org.junit.Assert.*;
import org.junit.Test;
import org.metaborg.terms2.impl.*;

import java.util.Arrays;

public class SimpleTests {
    @Test
    public void BuildingATree() {

        // Arrange
        IConstructor Cons = new Constructor("Cons", 2);
        IConstructor Nil = new Constructor("Nil", 0);
        IConstructor Pair = new Constructor("Pair", 2);

        TermFactory factory = new TermFactory();
        SyntaxTree tree = new SyntaxTree("myfile.txt",
                ConsProtoTerm.create(factory, Pair,
                        ConsProtoTerm.create(factory, Cons,
                                StringProtoTerm.create(factory, "MyString", 8),
                                ConsProtoTerm.create(factory, Nil)),
                IntProtoTerm.create(factory, 42, 2))
        );

        // Act
        tree.getRoot().accept(new TestTermVisitor());
        System.out.println();
        tree.getRoot().accept(new OffsetTermVisitor());
        System.out.println();
    }

    @Test
    public void ChangingATree() {
        // Arrange
        IConstructor Cons = new Constructor("Cons", 2);
        IConstructor Nil = new Constructor("Nil", 0);
        IConstructor Pair = new Constructor("Pair", 2);

        TermFactory factory = new TermFactory();
        SyntaxTree tree = new SyntaxTree("myfile.txt",
                ConsProtoTerm.create(factory, Pair,
                        ConsProtoTerm.create(factory, Cons,
                                StringProtoTerm.create(factory, "MyString", 8),
                                ConsProtoTerm.create(factory, Nil)),
                        IntProtoTerm.create(factory, 42, 2))
        );

        // Act
        Term currentTerm = tree.getRoot().getChildren().get(0);
        Term newTerm = currentTerm.withChild(0,
                ConsProtoTerm.create(factory, Pair,
                        StringProtoTerm.create(factory, "Different"),
                        IntProtoTerm.create(factory, 123)
                ));

        // Assert
        assertNotEquals(currentTerm, newTerm);
        assertNotEquals(currentTerm.getTree(), newTerm.getTree());

        currentTerm.getTree().getRoot().accept(new TestTermVisitor());
        System.out.println();
        newTerm.getTree().getRoot().accept(new TestTermVisitor());
        System.out.println();
    }

    @Test
    public void UsingGeneratedTermTypes() {
        // Arrange
        IConstructor Pair = new Constructor("Pair", 2);

        TermFactory factory = new TermFactory();
        SyntaxTree tree = new SyntaxTree("customers.txt",
                ConsProtoTerm.create(factory, Pair,
                        NameAgeProtoTerm.create(factory, "William", 42),
                        NameAgeProtoTerm.create(factory, "Kees", 32))
        );

        // Act
        NameAgeTerm william = ((NameAgeTerm)tree.getRoot().getChildren().get(0));
        NameAgeTerm william2 = william.setName("Bill");
        NameAgeTerm william3 = william2.setAge(38);
        SyntaxTree newTree = william3.getTree();


        // Assert
        assertEquals("William", ((NameAgeTerm)tree.getRoot().getChildren().get(0)).getName());
        assertEquals(42, ((NameAgeTerm)tree.getRoot().getChildren().get(0)).getAge());
        assertEquals("Kees", ((NameAgeTerm)tree.getRoot().getChildren().get(1)).getName());
        assertEquals(32, ((NameAgeTerm)tree.getRoot().getChildren().get(1)).getAge());

        tree.getRoot().accept(new TestTermVisitor());
        System.out.println();

        assertEquals("Bill", ((NameAgeTerm)newTree.getRoot().getChildren().get(0)).getName());
        assertEquals(38, ((NameAgeTerm)newTree.getRoot().getChildren().get(0)).getAge());
        assertEquals("Kees", ((NameAgeTerm)newTree.getRoot().getChildren().get(1)).getName());
        assertEquals(32, ((NameAgeTerm)newTree.getRoot().getChildren().get(1)).getAge());

        newTree.getRoot().accept(new TestTermVisitor());
        System.out.println();
    }

    @Test
    public void CreateAnnotatedTree() {
        // Arrange
        IConstructor Cons = new Constructor("Cons", 2);
        IConstructor Nil = new Constructor("Nil", 0);
        IConstructor Pair = new Constructor("Pair", 2);

        TermFactory factory = new TermFactory();
        SyntaxTree tree = new SyntaxTree("myfile.txt",
                ConsProtoTerm.create(factory, Pair,
                        Arrays.asList(
                                ConsProtoTerm.create(factory, Cons,
                                        StringProtoTerm.create(factory, "MyString", 8),
                                        ConsProtoTerm.create(factory, Nil)),
                                IntProtoTerm.create(factory, 42, 2)
                        ),
                        Arrays.asList(
                                ConsProtoTerm.create(factory, Pair,
                                        StringProtoTerm.create(factory, "X"),
                                        StringProtoTerm.create(factory, "Y"))
                        ))
        );

        // Act
        tree.getRoot().accept(new TestTermVisitor());
        System.out.println();
    }

}
