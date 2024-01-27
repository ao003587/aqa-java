package activities.api.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.function.Function;


@FunctionalInterface
interface Assertion {
    void Execute(Response response) throws IOException;
}

public class ActivitiesTest {

    private void ExecuteWithHttpClient(Function<Request.Builder, Request> setup, Assertion assertion) {
        var client = new OkHttpClient.Builder()
                .build();
        var requestBuilder = new Request.Builder()
                .url(baseUrl);
        var request = setup.apply(requestBuilder);
        try{
            try (var response = client.newCall(request).execute()) {
                assertion.Execute(response);
            }
        }
        catch (IOException ex){
            Assert.fail(ex.getMessage());
        }
    }

    private final String baseUrl = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

    @Test
    public void getAllActivitiesShouldReturn30Items() {

        ExecuteWithHttpClient(
                requestBuilder -> requestBuilder.get().build(),
                response -> {
                    var bodyString = response.body().string();
                    var activities = new JSONArray(bodyString);
                    Assert.assertEquals(activities.length(), 30);
                });
    }
}
