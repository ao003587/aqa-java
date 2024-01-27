package activities.api.test;

import activities.api.test.dto.Activity;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ActivityTest {
    private String baseUrl;
    OkHttpClient client;
    private ObjectMapper mapper;

    @BeforeClass
    public void setUp() {
        client = new OkHttpClient.Builder()
                .build();
        mapper = new ObjectMapper();
        baseUrl = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
    }

    @AfterClass
    public void shutDown() {
        client.connectionPool().evictAll();
    }

    @Test
    public void getAllActivitiesShouldReturn30Items() {
        try {
            var request = new Request.Builder()
                    .url(baseUrl)
                    .get()
                    .build();

            try (var response = client.newCall(request).execute()) {
                Assert.assertTrue(response.isSuccessful(),"Response is not successful, with code " + response.code() + " and message " + response.message());
                var bodyString = response.body().string();
                var activities = new JSONArray(bodyString);
                Assert.assertEquals(activities.length(), 30);
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void getActivityShouldReturnCorrectTitle() {
        try {
            var request = new Request.Builder()
                    .url(baseUrl + "/1")
                    .get()
                    .build();

            try (var response = client.newCall(request).execute()) {
                Assert.assertTrue(response.isSuccessful(),"Response is not successful, with code " + response.code() + " and message " + response.message());
                var bodyString = response.body().string();
                var activity = new JSONObject(bodyString);
                Assert.assertEquals(activity.get("title"), "Activity 1");
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void postActivityShouldAcceptData() {
        try {
            var newActivity = new Activity(100500, "Activity x", "2021-07-01T00:00:00+00:00", true);
            var requestBody = mapper.writeValueAsString(newActivity);

            var request = new Request.Builder()
                    .url(baseUrl)
                    .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                    .build();

            try (var response = client.newCall(request).execute()) {
                Assert.assertTrue(response.isSuccessful(),"Response is not successful, with code " + response.code() + " and message " + response.message());
                var bodyString = response.body().string();
                var createdActivity = mapper.readValue(bodyString, Activity.class);
                Assert.assertEquals(newActivity, createdActivity);
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void putActivityShouldAcceptData() {
        try {

            var newActivity = new Activity(100500, "Activity x", "2021-07-01T00:00:00+00:00", true);
            var requestBodyJson = mapper.writeValueAsString(newActivity);

            var postRequest = new Request.Builder()
                    .url(baseUrl)
                    .post(RequestBody.create(requestBodyJson, MediaType.parse("application/json")))
                    .build();

            try (var postResponse = client.newCall(postRequest).execute()) {
                Assert.assertTrue(postResponse.isSuccessful(),"Response is not successful, with code " + postResponse.code() + " and message " + postResponse.message());
            }

            var updateActivity = new Activity(100500, "Activity x", "2021-07-01T00:00:00+00:00", true);
            var requestBody = mapper.writeValueAsString(updateActivity);
            var request = new Request.Builder()
                    .url(baseUrl + "/100500")
                    .put(RequestBody.create(requestBody, MediaType.parse("application/json")))
                    .build();

            try (var response = client.newCall(request).execute()) {
                Assert.assertTrue(response.isSuccessful(),"Response is not successful, with code " + response.code() + " and message " + response.message());
                var bodyString = response.body().string();
                var updatedActivity = mapper.readValue(bodyString, Activity.class);
                Assert.assertEquals(updateActivity, updatedActivity);
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }


    @Test
    public void deleteActivityShouldBeSuccessfulData() {
        try {

            var newActivity = new Activity(1, "Activity x", "2021-07-01T00:00:00+00:00", true);
            var requestBodyJson = mapper.writeValueAsString(newActivity);

            var postRequest = new Request.Builder()
                    .url(baseUrl)
                    .post(RequestBody.create(requestBodyJson, MediaType.parse("application/json")))
                    .build();

            try (var postResponse = client.newCall(postRequest).execute()) {
                Assert.assertTrue(postResponse.isSuccessful(),"Response is not successful, with code " + postResponse.code() + " and message " + postResponse.message());
            }

            var request = new Request.Builder()
                    .url(baseUrl + "/1")
                    .delete()
                    .build();

            try (var response = client.newCall(request).execute()) {
                Assert.assertTrue(response.isSuccessful(),"Response is not successful, with code " + response.code() + " and message " + response.message());
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
