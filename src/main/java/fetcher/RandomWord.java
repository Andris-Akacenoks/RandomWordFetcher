package fetcher;

public interface RandomWord {
    void fetchWords(int numberOfWords, int maxNumberOfAttempts) throws RandomWordException;
    int getTotalFetchedChars();
    int getFetchedCount();
    int getTotalUniqueChars();
    String getLongestWord();
    String getFetchedText();
    void writeFetchedCount();
    void writeTotalFetchedChars();
    void writeTotalUniqueChars();
    void writeLongestWord();
    void writeFetchedText();
}
