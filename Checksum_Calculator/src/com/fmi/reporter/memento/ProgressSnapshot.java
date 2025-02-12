package com.fmi.reporter.memento;

public record ProgressSnapshot(long readBytes, long expectedBytesToRead, long elapsedMilliseconds) {
}
