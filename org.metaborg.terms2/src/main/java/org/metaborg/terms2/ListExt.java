package org.metaborg.terms2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Extra methods for working with lists.
 */
public final class ListExt {

    /**
     * Returns a new list with a single element replaced.
     *
     * @param list The list.
     * @param index The zero-based index of the element to replace.
     * @param element The new element.
     * @param <T> The type of element.
     * @return The resulting list.
     */
    public static <T> List<? extends T> withElement(List<? extends T> list, int index, T element) {
        if (index < 0 || index >= list.size())
            throw new IllegalArgumentException("The index is out of bounds.");

        return Stream.concat(Stream.concat(
                list.stream().limit(index),
                Stream.of(element)),
                list.stream().skip(index + 1))
                .collect(Collectors.toList());
    }

}
