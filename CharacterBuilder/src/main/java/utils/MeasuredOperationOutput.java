package utils;

public record MeasuredOperationOutput<T>(String name, long time, T output) implements MeasuredOperation {

}
