package com.fmi.writer;

import com.fmi.observer.BaseObservable;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;

public abstract class BaseObservableStreamWriter extends BaseObservable implements Closeable {
    @NotNull
    protected final OutputStreamWriter streamWriter;
    @NotNull
    protected final OutputStream outputStream;

    public BaseObservableStreamWriter(@NotNull OutputStream outputStream) {
        this.outputStream = outputStream;
        this.streamWriter = new OutputStreamWriter(outputStream);
    }

    @Override
    public void close() {
        try (streamWriter; outputStream) {
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Error occurred while closing BaseObservableStreamWriter: " + streamWriter, e);
        }
    }
}