package fetcher;

public interface RandomWord {
    void fetchWords(int numberOfWords, int maxNumberOfAttempts) throws RandomWordException;
    void writeFetchedCount();
    void writeTotalFetchedChars();
    void writeTotalUniqueChars();
    void writeLongestWord();
    void writeFetchedText();
}
