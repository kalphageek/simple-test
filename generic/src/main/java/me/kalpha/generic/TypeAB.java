package me.kalpha.generic;

public class TypeAB<T> {
    T t;
    public TypeAB(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
