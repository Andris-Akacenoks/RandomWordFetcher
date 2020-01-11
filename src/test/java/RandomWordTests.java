import fetcher.RandomWordException;
import fetcher.RandomWordFetcher;
import helpers.FileHelper;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RandomWordTests {

    RandomWordFetcher randomWord;
    FileHelper fileHelper;

    @Test
    public void fetchWordsTest() throws RandomWordException {

    }

    @Test
    public void writeFetchedCountTest() {
        fileHelper = new FileHelper();
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        int wordCount = randomWord.getFetchedCount();
        randomWord.writeFetchedCount();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Count of fetched words: "+ wordCount);
    }

    @Test
    public void writeTotalFetchedCharsTest() {
        fileHelper = new FileHelper();
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        int charCount = randomWord.getTotalFetchedChars();
        randomWord.writeTotalFetchedChars();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Total count of all fetched characters: "+ charCount);
    }

    @Test
    public void writeTotalUniqueCharsTest() {
        fileHelper = new FileHelper();
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        int uniqueCharsCount = randomWord.getTotalUniqueChars();
        randomWord.writeTotalUniqueChars();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Total count of unique characters: "+ uniqueCharsCount);
    }

    @Test
    public void writeLongestWordTest() {
        fileHelper = new FileHelper();
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        String longestWord = randomWord.getLongestWord();
        randomWord.writeLongestWord();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "The longest word: "+ longestWord);
    }

    @Test
    public void writeFetchedTextTest() {
        fileHelper = new FileHelper();
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        String fetchedText = randomWord.getFetchedText();
        randomWord.writeFetchedText();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Fetched text: "+ fetchedText);
    }
}