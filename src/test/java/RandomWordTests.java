import fetcher.RandomWordException;
import fetcher.RandomWordFetcher;
import helpers.FileHelper;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RandomWordTests {

    RandomWordFetcher randomWord;
    FileHelper fileHelper =  new FileHelper();
    String sampleWords = "[\"intender\", \"isling\", \"hunk\", \"washiest\", \"entropically\", \"corporalities\", \"tasteable\", \"bilby\", \"aglycones\", \"loyalist\"]";

    @Test
    public void fetchWordsTest() throws RandomWordException {
    }

    @Test
    public void getTotalFetchedCharsTest() {
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.setWords(sampleWords);
        assertEquals(82, randomWord.getTotalFetchedChars());
    }

    @Test
    public void getFetchedCountTest() {
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.setWords(sampleWords);
        assertEquals(10, randomWord.getFetchedCount());
    }

    @Test
    public void getTotalUniqueCharsTest() {
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.setWords(sampleWords);
        assertEquals(19, randomWord.getTotalUniqueChars());
    }

    @Test
    public void getLongestWordTest() {
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.setWords(sampleWords);
        assertEquals("corporalities", randomWord.getLongestWord());
    }

    @Test
    public void getFetchedTextTest() {
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.setWords(sampleWords);
        assertEquals(
            "Intender isling hunk washiest entropically corporalities tasteable bilby aglycones loyalist.",
            randomWord.getFetchedText()
        );
    }

    @Test
    public void writeFetchedCountTest() {
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
        randomWord = new RandomWordFetcher(Main.API_KEY);
        randomWord.fetchWords(10, 3);
        String fetchedText = randomWord.getFetchedText();
        randomWord.writeFetchedText();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Fetched text: "+ fetchedText);
    }
}