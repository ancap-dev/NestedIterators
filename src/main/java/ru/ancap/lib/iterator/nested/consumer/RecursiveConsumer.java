package ru.ancap.lib.iterator.nested.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public abstract class RecursiveConsumer<T> implements Consumer<T> {

    @Getter
    private final List<T> accumulator;

    public RecursiveConsumer() {
        this.accumulator = new ArrayList<>();
    }

    public List<T> recurse(T t) {
        List<T> list = new ArrayList<>(accumulator);
        list.add(t);
        return list;
    }

}
