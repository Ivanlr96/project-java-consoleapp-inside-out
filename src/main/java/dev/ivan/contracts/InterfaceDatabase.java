package dev.ivan.contracts;

import java.util.List;

public interface InterfaceDatabase<E> {
    void store(E element);

    List<E> getAll();

    boolean delete(int index);
}