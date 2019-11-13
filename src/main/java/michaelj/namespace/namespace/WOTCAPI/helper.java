package michaelj.namespace.namespace.WOTCAPI;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class helper {

    public static void driver() throws IOException {
        String json = getJSON();
        Response response = responseFromJson(json);
        System.out.println(response);
    }

    public static String getJSON() throws IOException {
        String APIURL = "http://dnd5eapi.co/api/classes";
        URL url = new URL(APIURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            if (status == 200) {
                try (BufferedReader reader = fileReader(connection)) {
                    return jsonBuilder(reader);
                }
            } else {
                throw new IOException();
            }
        } finally {
            connection.disconnect();
        }
    }


    public static BufferedReader fileReader(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader fileReader = new BufferedReader(inputStreamReader);
        return fileReader;
    }

    public static String jsonBuilder(BufferedReader reader) throws IOException {
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            json.append(line);
            line = reader.readLine();
        }
        return json.toString();
    }

    public static Response responseFromJson(String JSON){
        Gson gson = new Gson();
        Response output = gson.fromJson(JSON, Response.class);
        return output;
    }
}
