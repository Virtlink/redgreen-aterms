# Red-green Aterms

This library is a proof-of-concept of an Aterm library (well, *Term library at the moment, annotations are not implemented) based on the idea of Red-Green trees found in the Roslyn compiler. In this implementation the green tree is called the prototype tree (with prototype terms) and the red tree is the syntax tree (with normal terms). You can navigate the tree using either the red or the green tree, but the red tree contains more information and allows you to navigate to ancestors. Both trees are immutable, so they are thread-safe. The green tree allows its nodes to be shared, whereas the red tree does not. The red tree is discarded upon every change, the green tree is kept. Nodes in the red tree have weak references to their child terms, such that when a reference to a term is not held by any code, the term may be garbage collected (reducing memory pressure). The red tree can be reconstructed at any time.

In the `SimpleTests` class in the tests folder there are three examples: building a tree, manipulating a tree, and using a class generated for a custom constructor type. This shows how relatively easy this library is to use from languages such as Java.

From the command-line, invoke `./gradlew test` to execute the three examples and show their output.
