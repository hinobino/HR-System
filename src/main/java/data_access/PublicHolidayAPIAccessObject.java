package data_access;

import java.io.IOException;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PublicHolidayAPIAccessObject {

    private final String region;

    private final static String API_BASE_URL = "https://holidays.abstractapi.com/v1/";

    public static String getAPIToken() {
        return System.getenv("key");
    }

    public PublicHolidayAPIAccessObject(String region) {
        this.region = region;
    }

    public boolean holidayOn(LocalDate date) {
        // Get components of date
        final int year = date.getYear();
        final int month = date.getMonthValue();
        final int day = date.getDayOfMonth();

        // Build request
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(
                        String.format("%s?api_key=%s&country=%s&year=%d&month=%d&day=%d", API_BASE_URL,
                                getAPIToken(), region, year, month, day)
                )
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (responseBody.startsWith("{")) {
                final JSONObject responseObj = new JSONObject(responseBody);
                throw new RuntimeException(responseObj.getJSONObject("error").getString("message"));
            }
            else {
                final JSONArray holidays = new JSONArray(responseBody);
                return !holidays.isEmpty();
            }
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

}