package fetcher;

public class RandomWordException extends RuntimeException {

    public RandomWordException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public RandomWordException(String errorMessage) {
        super(errorMessage);
    }
}