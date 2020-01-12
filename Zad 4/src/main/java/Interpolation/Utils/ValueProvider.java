package Interpolation.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValueProvider {
    public static String getGoogleApiData(String point1, String point2, int samples) throws Exception {
        String apiKey = System.getenv("GOOGLE_API_KEY");

        String googleApi = "https://maps.googleapis.com/maps/api/elevation/json";
        String path = "" + point1 + "|" + point2 + "&key=" + apiKey + "&samples=" + samples;

        System.out.println(googleApi + "?path=" + path);

        URL url = new URL(googleApi + "?path=" + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        Reader streamReader;
        if (con.getResponseCode() > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
                content.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
