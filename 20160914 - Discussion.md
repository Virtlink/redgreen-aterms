
Daniel Pelsmaeker [11:02]
@gohla @jeffsmits Last night I was inspired to see what a red-green term library in Java would look like. Here is a proof-of-concept: https://github.com/Virtlink/redgreen-aterms
Of couse, this may be the easy part. Transitioning Stratego and all current term users would be the most work.


Gabriël Konat [11:13]
nice, how would you do sharing, store annotations, and track origin terms after transformation?

Daniel Pelsmaeker [11:22]
Sharing: would decrease memory pressure but it not required. The TermFactory implementation would take care of this through its `intern()` method, which takes a prototype and returns a cached prototype (depending on the implementation). Roslyn keeps a limited cache from which the oldest terms are evicted, and only caches terms with three or less children. So not even Roslyn tries to maximally share the whole tree.

Annotations: similar to `getChildren()` on the terms and proto-terms, there would be a `getAnnotations()` property that works the same way: for proto terms it's simply a list of subterms, for non-proto terms it's a collection that lazily instantiates the term based on the proto subterm.

Origin tracking: this implementation tracks the width of each term (and for constructors the width of the combined terms) and therefore the source location of the term. It is possible to insert zero-width terms (virtual terms), which don't influence the actual source location of subsequent terms. When removing terms, dummy terms must be inserted to account for the width gap.
However, this approach doesn't allow arbitrary transformations with origin preservation like we currently do it. The origin information of each term is unique to each term, and would therefore need to be stored with the proto-term itself. Then no matter where the term ends up in the desugared and transformed AST, the origin is still on the term. Of course, this impedes term sharing as origins make every term unique.

Gabriël Konat [11:37]
right, so the prototerm would have a nullable originating term or something?

Daniel Pelsmaeker [11:38]
Yes that would be the idea. And for ease of use I would define it directly on the prototerm, since origins are so often used.

Gabriël Konat [11:39]
right, which would mean only the parsed AST can be shared

[11:40]
but even on the parsed proto AST, only simple things like strings and ints can be shared, because other terms will have different widths?

Daniel Pelsmaeker [11:41]
In my implementation only leaf-terms (strings, ints) _determine_ a width. Other prototerms higher up _derive_ their widths from the widths of their children. This means that if two constructor terms have the same list of children, they must have the same width. Therefore they can be shared.

Gabriël Konat [11:41]
that only works if a language has 0 layout :stuck_out_tongue:

[11:42]
or if layout is included into the tree

Daniel Pelsmaeker [11:42]
Well, the latter is what Roslyn does.

Gabriël Konat [11:42]
and no keywords

Jeff Smits [11:42]
I guess it needs to be a parse tree, not an AST

Daniel Pelsmaeker [11:42]
This allows it to reconstruct the whole source file (e.g. after a refactoring) including whitespace and comments.

Gabriël Konat [11:42]
but we could actually store the layout indeed, and remove that in the red tree if you don’t want it

Daniel Pelsmaeker [11:42]
Sure, the red tree can skip over it.

Gabriël Konat [11:43]
what about non-terminals? how are those stored in the tree?

Jeff Smits [11:43]
parse trees and views are probably the nicest approach to preserving whitespace info anyway

Daniel Pelsmaeker [11:43]
non-terminals would usually produce a constructor, so in the tree they would be constructor terms with subterms.

Gabriël Konat [11:44]
yes, but they have a width that must be stored as well

Daniel Pelsmaeker [11:47]
If I understand you correctly, then the width of a non-terminal/constructor is the sum of the widths of the terminals and layouts within it. A non-terminal that didn't consume any characters will therefore have a width of 0.

Gabriël Konat [11:48]
`Class.Class = <class <ID> { }>`, `class A { }` how would the green-tree for that look, including widths?

[11:49]
it would have to be a parse-tree I guess :slightly_smiling_face:

Jeff Smits [11:50]
yeah, looks like green trees with this scheme need to be parse trees, and the red trees can be AST views of the parse tree

Daniel Pelsmaeker [11:54]
Something like:
```Class(
    Trivia("class"){@0 +5},
    Layout(" "){@5 +1},
    ID("A"){@6 +1},
    Layout(" "){@7 +1},
    Trivia("{"){@8 +1},
    [
        Layout(" "){@9 +1}
    ]{@9 +1},
    Trivia("}"){@10 +1}
){@0 +11}
```

[11:54]
@jeffsmits Yes I think that would be a feasible approach. I believe Roslyn takes the keywords and layout and puts them in _trivia_ nodes associated with each normal node. We could do something like that as well.

Gabriël Konat [11:55]
makes sense

Daniel Pelsmaeker [11:56]
If we hide the trivia and layout, we get the actual AST we're used to.

Gabriël Konat [11:56]
(maybe a good idea to store this discussion in a notes file in the repo)

Daniel Pelsmaeker [11:56]
(I'm not sure how to export a discussion?)

Gabriël Konat [11:58]
select all, copy :stuck_out_tongue:


Daniel Pelsmaeker [11:59]
Or, even without associating it with normal nodes, we could keep the trivia in the green tree, but for the purposes of the red tree and term children they are ignored. Such that in the previous example the `Class` constructor, which expects two arguments, it viewed like this: `Class(ID("A"), [])` while under the hood it's more like this: `Class(T("class"), L(" "), ID("A"), L(" "), T("{"), [ L(" ") ], T("}"))` (T and L are trivia/layout).

[12:00]
Then the widths are correct and layout is not lost.

[12:01]
Implementing reformatting, refactorings or code completion would then be dead-simple, as it for once wouldn't discard your comments.

[12:01]
And we can programatically insert comments and other layout as trivia/layout nodes.

Gabriël Konat [12:01]
it’s not that simple, you still have to manually/automatically preserve them :slightly_smiling_face:

[12:02]
even in Rascal this gives problems

Daniel Pelsmaeker [12:03]
Well, currently I implemented one operation on terms which replaces a subterm. This would preserve all layout before and after that subterm, so in the worst case the inserted subterm wouldn't have any layout. But the other layout is preserved, I think.

[12:03]
The fact that you say it even gives problems in Rascal makes me think I'm overlooking something.
