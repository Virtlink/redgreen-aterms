# Red-green ATerms

This library is a proof-of-concept of an ATerm library based on the idea of Red-Green trees found in the Roslyn compiler. You can navigate the tree using either the red or the green tree, but the red tree contains more information and allows you to navigate to ancestors.

In the `SimpleTests` class in the tests folder there are three examples: building a tree, manipulating a tree, and using a class generated for a custom constructor type. This shows how relatively easy this library is to use from languages such as Java.

From the command-line, invoke `./gradlew test` to execute the three examples and show their output.

## Concept
In a normal AST each tree node has references only to its child nodes, and this allows common subtrees to be shared. Due to this an AST must be constructed bottom-up, leaves first. This is called the _green tree_, or _proto-tree_ in this implementation. The nodes in the green tree are only allowed to contain information that is local to the node or derivable from its children, such as the node's width. The green tree is immutable and therefore thread-safe: any change to the green tree creates a new tree, but parts of the old and the new tree are invisibly shared as much as possible.

However, the green tree contains more information than can be gleamed from a single node. This extra information depends not only on the node itself or its children, but also its ancestors. For example, the file that contains the node and the node's exact position in it can be derived when we know a node's ancestors. To enable this, a secondary _view_ of the green tree is constructed, and this is the _red tree_ (or the _syntax tree_ in this implementation). The red tree allows you to navigate both up and down and sideways, and is constructed on-demand as the tree is visited. Upon any change to the green tree the red tree is discarded. The nodes in the red tree have weak references to their child nodes, so they can be garbage collected when not referenced.

## Sources
The concept of red-green trees was initially created by the folks at Microsoft for their Roslyn compiler for .NET.

- Eric Lippert — [Persistence, Facades and Roslyn’s Red-Green Trees](https://blogs.msdn.microsoft.com/ericlippert/2012/06/08/persistence-facades-and-roslyns-red-green-trees/)
- [Roslyn Overview](https://github.com/dotnet/roslyn/wiki/Roslyn%20Overview)
- [Getting Started: C# Syntax Transformation](https://github.com/dotnet/roslyn/wiki/Getting-Started-C%23-Syntax-Transformation)
- [Roslyn's performance](https://roslyn.codeplex.com/discussions/541953)
- [How does the release version of Roslyn implement immutable trees?](http://stackoverflow.com/q/25963328/146622)
