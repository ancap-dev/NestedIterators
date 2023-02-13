package ru.ancap.lib.iterator.nested.executor;

import lombok.AllArgsConstructor;
import ru.ancap.lib.iterator.nested.consumer.RecursiveConsumer;

import java.util.Iterator;

@AllArgsConstructor
public class NestedLoopExecutor<T> implements Runnable {

    private final Iterator< Iterator<T> > iterators;
    private final MultiConsumer<T> consumer;

    
    @Override
    public void run() {
        this.iterate(consumer);
    }

    public void iterate(MultiConsumer<T> finalConsumer) {
        this.iterators.next().forEachRemaining(new RecursiveConsumer<>() {
            @Override
            public void accept(T t) {
                nextAccept(this, t, finalConsumer);
            }
        });
    }

    private void nextAccept(RecursiveConsumer<T> higher, T t, MultiConsumer<T> finalConsumer) {
        if (iterators.hasNext()) {
            iterators.next().forEachRemaining(new RecursiveConsumer<>(higher.recurse(t)) {
                @Override
                public void accept(T t) {
                    nextAccept(this, t, finalConsumer);
                }
            });
        } else {
            finalConsumer.accept(higher.recurse(t));
        }
    }
}
