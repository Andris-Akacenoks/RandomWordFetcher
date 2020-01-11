package helpers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RestHelper {

    private OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException {
        Response response;
        Request request = new Request.Builder()
                .url(url)
                .build();
        response = client.newCall(request).execute();
        if (response.body() != null) {
            return response.body().string();
        }
        return null;
    }
}
