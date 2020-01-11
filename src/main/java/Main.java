import fetcher.RandomWordFetcher;
import fetcher.RandomWord;
import fetcher.RandomWordException;

public class Main {

    static final String API_KEY = "RPGS9YWT";

    public static void main(String[] args) throws RandomWordException {
        RandomWord randomWord = new RandomWordFetcher(API_KEY);

        randomWord.fetchWords(10, 3);
        randomWord.writeFetchedCount();
        randomWord.writeTotalFetchedChars();
        randomWord.writeTotalUniqueChars();
        randomWord.writeLongestWord();
        randomWord.writeFetchedText();
    }

    static void log(String s) {
        System.out.println(s);
    }
}
