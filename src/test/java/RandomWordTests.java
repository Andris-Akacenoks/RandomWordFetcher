import fetcher.RandomWordException;
import fetcher.RandomWordFetcher;
import helpers.FileHelper;
import helpers.RestHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomWordTests {
    RandomWordFetcher randomWord;
    FileHelper fileHelper =  new FileHelper();
    String sampleWords = "[\"intender\", \"isling\", \"hunk\", \"washiest\", \"entropically\", \"corporalities\", \"tasteable\", \"bilby\", \"aglycones\", \"loyalist\"]";
    String apiKey = "5E0SCOBD";
    int numberOfWords = 10;
    int maxRetryCount = 3;

    @Before
    public void mockRestResponse() throws IOException {
        randomWord = new RandomWordFetcher(apiKey);
        RestHelper mockedRest = mock(RestHelper.class);
        when(mockedRest.get(randomWord.getBaseUrl()+"/word?key="+apiKey+"&number="+numberOfWords)).thenReturn(sampleWords);
        randomWord.setRestHelper(mockedRest);
    }

    @Test
    public void fetchWordsTest() throws RandomWordException {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        assertEquals(sampleWords, randomWord.getWords());
    }

    @Test
    public void getTotalFetchedCharsTest() {
        randomWord.setWords(sampleWords);
        assertEquals(82, randomWord.getTotalFetchedChars());
    }

    @Test
    public void getFetchedCountTest() {
        randomWord.setWords(sampleWords);
        assertEquals(numberOfWords, randomWord.getFetchedCount());
    }

    @Test
    public void getTotalUniqueCharsTest() {
        randomWord.setWords(sampleWords);
        assertEquals(19, randomWord.getTotalUniqueChars());
    }

    @Test
    public void getLongestWordTest() {
        randomWord.setWords(sampleWords);
        assertEquals("corporalities", randomWord.getLongestWord());
    }

    @Test
    public void getFetchedTextTest() {
        randomWord.setWords(sampleWords);
        assertEquals(
            "Intender isling hunk washiest entropically corporalities tasteable bilby aglycones loyalist.",
            randomWord.getFetchedText()
        );
    }

    @Test
    public void writeFetchedCountTest() {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        int wordCount = randomWord.getFetchedCount();
        randomWord.writeFetchedCount();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Count of fetched words: "+ wordCount);
    }

    @Test
    public void writeTotalFetchedCharsTest() {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        int charCount = randomWord.getTotalFetchedChars();
        randomWord.writeTotalFetchedChars();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Total count of all fetched characters: "+ charCount);
    }

    @Test
    public void writeTotalUniqueCharsTest() {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        int uniqueCharsCount = randomWord.getTotalUniqueChars();
        randomWord.writeTotalUniqueChars();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Total count of unique characters: "+ uniqueCharsCount);
    }

    @Test
    public void writeLongestWordTest() {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        String longestWord = randomWord.getLongestWord();
        randomWord.writeLongestWord();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "The longest word: "+ longestWord);
    }

    @Test
    public void writeFetchedTextTest() {
        randomWord.fetchWords(numberOfWords, maxRetryCount);
        String fetchedText = randomWord.getFetchedText();
        randomWord.writeFetchedText();
        String fileContents = fileHelper.read(randomWord.getFileLocation());
        fileHelper.delete(randomWord.getFileLocation());
        assertEquals(fileContents, "Fetched text: "+ fetchedText);
    }
}