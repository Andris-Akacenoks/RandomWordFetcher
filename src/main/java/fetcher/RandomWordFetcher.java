import helpers.RandomWordFetcherException;
import helpers.RestHelper;

import java.io.IOException;

public class RandomWordFetcher {

    private String baseUrl = "https://random-word-api.herokasdsduapp.com";
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
                if(attempts >= maxNumberOfAttempts) {
                    throw new RandomWordFetcherException("Failed to ");
                }
            }
        }
        return words;
    }


    //    public static void main(String[] args) throws IOException {
//        String API_KEY = "RPGS9YWT";
//        int numberOfWords = 9;
//        String baseUrl = "https://random-word-api.herokasdsduapp.com";
//        String requestUrl = baseUrl+"/word?key="+API_KEY+"&number="+numberOfWords;
//
//        RestHelper rest = new RestHelper();
//        String words = rest.get(requestUrl, 3);
//        System.out.println(words);
//
//    }

}
