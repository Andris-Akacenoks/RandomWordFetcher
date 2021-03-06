package fetcher;

import helpers.FileHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RandomWordFetcher extends RandomWordBase implements RandomWord {

    public RandomWordFetcher(String apiKey) {
        super(apiKey);
    }

    private void appendToWordFile (String content) {
        FileHelper fileHelper = new FileHelper();
        if (getFileLocation() == null) {
            setFileLocation(fileHelper.createNew());
        }
        fileHelper.write(getFileLocation(), content + "\n");
    }

    @Override
    public void fetchWords(int numberOfWords, int maxNumberOfAttempts) throws RandomWordException {
        if (numberOfWords < 10) {
            throw new RandomWordException("Word count of "+numberOfWords+" is too small. We need at least 10.");
        }
        String requestUrl = getBaseUrl()+"/word?key="+getApiKey()+"&number="+numberOfWords;
        String words = null;
        int attempts = 0;
        while (true) {
            try {
                words = rest.get(requestUrl);
                break;
            } catch (IOException e) {
                System.out.println("Failed to fetch words: "+e);
                if (maxNumberOfAttempts > 1) {
                    attempts++;
                    System.out.println("Retrying "+attempts+"/"+maxNumberOfAttempts);
                    if(attempts >= maxNumberOfAttempts) {
                        throw new RandomWordException("Failed to fetch words after "+attempts+" attempts: ", e);
                    }
                } else {
                    throw new RandomWordException("Failed to fetch words: ", e);
                }
            }
        }
        setWords(words);
    }

    @Override
    public int getTotalFetchedChars() {
        int totalCharCount = 0;
        for(String word : getWordsArray()) {
            totalCharCount += word.length();
        }
        return totalCharCount;
    }

    @Override
    public int getFetchedCount() {
        return getWordsArray().length;
    }

    @Override
    public int getTotalUniqueChars() {
        Set<Character> uniqueChars = new HashSet<Character>();
        for(String word : getWordsArray()) {
            for(char c : word.toCharArray()) {
                uniqueChars.add(c);
            }
        }
        return uniqueChars.size();
    }

    @Override
    public String getLongestWord() {
        String longestWord = getWordsArray()[0];
        for(String word : getWordsArray()) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    @Override
    public String getFetchedText() {
        StringBuilder fetchedText = new StringBuilder();
        for(int i=0; i<getWordsArray().length; i++) {
            if(i == 0) {
                String word = getWordsArray()[0] + " ";
                fetchedText.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
            } else if (i == getWordsArray().length - 1) {
                fetchedText.append(getWordsArray()[i]).append(".");
            }
            else {
                fetchedText.append(getWordsArray()[i]).append(" ");
            }
        }
        return fetchedText.toString();
    }

    @Override
    public void writeFetchedCount() {
        appendToWordFile("Count of fetched words: "+ getFetchedCount());
    }

    @Override
    public void writeTotalFetchedChars() {
        appendToWordFile("Total count of all fetched characters: "+ getTotalFetchedChars());
    }

    @Override
    public void writeTotalUniqueChars() {
        appendToWordFile("Total count of unique characters: " + getTotalUniqueChars());
    }

    @Override
    public void writeLongestWord() {
        appendToWordFile("The longest word: " + getLongestWord());
    }

    @Override
    public void writeFetchedText() {
        appendToWordFile("Fetched text: " + getFetchedText());
    }
}
