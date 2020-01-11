package Interpolation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValueProvider {
    public static String getGoogleApiData() throws Exception {
        URL url = new URL("https://maps.googleapis.com/maps/api/elevation/json?path={point1}|{point2}&key={key}&samples={samples}");
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
