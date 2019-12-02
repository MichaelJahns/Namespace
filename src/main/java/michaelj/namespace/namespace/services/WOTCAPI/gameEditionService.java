package michaelj.namespace.namespace.services.WOTCAPI;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("gameEditionService")
public class gameEditionService {

    public static Response driver(){
        String json ="";
        try{
            json = getJSON("http://dnd5eapi.co/api/classes");
        }catch(IOException e){}
        Response response = responseFromJson(json);
        return response;
    }

    public static String getJSON(String APIURL) throws IOException {
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
