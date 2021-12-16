package ch.bbw.mw.controller;

import ch.bbw.mw.ConnectionsResponse;
import ch.bbw.mw.model.Connections;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Controller {


    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAll() {
        connect();
    }

    private Connections connect() {
        try {
            URL url = new URL("http://transport.opendata.ch/v1/connections?from=Lausanne&to=Genève");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());

            if(http.getResponseCode() == 200) {
                Connections connections;
                BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
                Gson gson = new Gson();
                connections = gson.fromJson(reader, Connections.class);
                http.disconnect();

                return connections;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
