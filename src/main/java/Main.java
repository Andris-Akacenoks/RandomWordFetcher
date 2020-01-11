import fetcher.RandomWordFetcher;
import fetcher.RandomWordFetcherException;

public class Main {

    static final String API_KEY = "RPGS9YWT";

    public static void main(String[] args) throws RandomWordFetcherException {
        RandomWordFetcher wordFetcher = new RandomWordFetcher(API_KEY);
        log(wordFetcher.fetch(9, 3));
    }

    static void log(String s) {
        System.out.println(s);
    }
}
