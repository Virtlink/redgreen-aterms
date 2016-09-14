package org.metaborg.terms2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListExt {

    public static <T> List<? extends T> withElement(List<? extends T> list, int index, T element) {
        if (list == null)
            throw new IllegalArgumentException("The list must not be null.");
        if (index < 0 || index >= list.size())
            throw new IllegalArgumentException("The index is out of bounds.");

        return Stream.concat(Stream.concat(
                list.stream().limit(index),
                Stream.of(element)),
                list.stream().skip(index + 1))
                .collect(Collectors.toList());
    }

}
