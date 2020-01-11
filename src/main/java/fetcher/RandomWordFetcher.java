package fetcher;

import helpers.FileHelper;
import helpers.RestHelper;

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
        RestHelper rest = new RestHelper();
        String requestUrl = getBaseUrl()+"/word?key="+getApiKey()+"&number="+numberOfWords;
        String words = null;
        boolean fetched = false;
        int attempts = 1;
        while (!fetched && maxNumberOfAttempts > attempts) {
            try {
                words = rest.get(requestUrl);
                fetched = true;
            } catch (IOException e) {
                System.out.println("Failed to fetch words: "+e);
                attempts++;
                System.out.println("Retrying... Attempt: "+attempts+"/"+maxNumberOfAttempts);
                if(attempts >= maxNumberOfAttempts) {
                    throw new RandomWordException("Failed to fetch words: ", e);
                }
            }
        }
        setWords(words);
    }

    @Override
    public void writeFetchedCount() {
        appendToWordFile("Count of fetched words: "+ getWordsArray().length);
    }

    @Override
    public void writeTotalFetchedChars() {
        int totalCharCount = 0;
        for(String word : getWordsArray()) {
            totalCharCount += word.length();
        }
        appendToWordFile("Total count of all fetched characters: "+ totalCharCount);
    }

    @Override
    public void writeTotalUniqueChars() {
        Set<Character> uniqueChars = new HashSet<Character>();
        for(String word : getWordsArray()) {
            for(char c : word.toCharArray()) {
                uniqueChars.add(c);
            }
        }
        appendToWordFile("Total count of unique characters: " + uniqueChars.size());
    }

    @Override
    public void writeLongestWord() {
        String longestWord = getWordsArray()[0];
        for(String word : getWordsArray()) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        appendToWordFile("The longest word: " + longestWord);
    }

    @Override
    public void writeFetchedText() {
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
        appendToWordFile("Fetched text: " + fetchedText);
    }
}
