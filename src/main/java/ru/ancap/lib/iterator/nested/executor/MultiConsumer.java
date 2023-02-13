package ru.ancap.lib.iterator.nested.executor;

import java.util.List;

public interface MultiConsumer<T>  {

    void accept(List<T> values);

}
