package fetcher;

import helpers.RestHelper;

public class RandomWordBase {
    private String apiKey;
    private String baseUrl = "https://random-word-api.herokuapp.com";
    private String words;
    private String fileLocation = null;
    RestHelper rest = new RestHelper();

    public RandomWordBase() {}

    public RandomWordBase(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String[] getWordsArray() {
        String commaSeparatedWords = getWords().replaceAll("[^a-zA-Z, ]", "");
        return commaSeparatedWords.split(", ");
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setRestHelper(RestHelper rest) {
        this.rest = rest;
    }
}
