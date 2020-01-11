package fetcher;

import helpers.RestHelper;

import java.io.IOException;

public class RandomWordFetcher {

    private String baseUrl = "https://random-word-api.herokuapp.com";
    private String apiKey;

    public RandomWordFetcher(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String fetch(int numberOfWords, int maxNumberOfAttempts) throws RandomWordFetcherException {
        if (numberOfWords < 10) {
            throw new RandomWordFetcherException("Word count of "+numberOfWords+" is too small. We need at least 10.");
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
                System.out.println("Retrying... Attempt nr."+attempts);
                if(attempts >= maxNumberOfAttempts) {
                    throw new RandomWordFetcherException("Failed to fetch words: ", e);
                }
            }
        }
        return words;
    }

}
