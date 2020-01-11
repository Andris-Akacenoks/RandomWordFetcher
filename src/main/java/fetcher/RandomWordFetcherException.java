package fetcher;

public class RandomWordFetcherException extends RuntimeException {

    public RandomWordFetcherException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public RandomWordFetcherException(String errorMessage) {
        super(errorMessage);
    }
}