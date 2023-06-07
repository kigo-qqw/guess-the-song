package ru.guess_the_song.core.dto;

import lombok.ToString;

import java.io.Serializable;
import java.util.NoSuchElementException;

@ToString(callSuper = true)
public class Result<T extends Serializable> implements Serializable {
    private final T value;

    public static <T extends Serializable> Result<T> empty() {
        return new Result<>(null);
    }

    public static <T extends Serializable> Result<T> of(T value) throws NullPointerException {
        if (value == null) throw new NullPointerException();
        return new Result<>(value);
    }

    public static <T extends Serializable> Result<T> ofNullable(T value) {
        return new Result<>(value);
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T get() throws NoSuchElementException {
        if (!isPresent()) throw new NoSuchElementException();
        return this.value;
    }

    private Result(T value) {
        this.value = value;
    }
}
