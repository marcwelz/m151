package ch.bbw.mw.service;

import ch.bbw.mw.model.*;
import ch.bbw.mw.model.device.Device;
import ch.bbw.mw.model.device.DeviceWrapper;
import ch.bbw.mw.model.user.User;
import ch.bbw.mw.repository.DeviceRepository;
import ch.bbw.mw.repository.SongRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final SongRepository songRepository;
    private final DeviceRepository deviceRepository;

    private HttpURLConnection connect(String url, String token, String requestMethod) {
        try {
            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setRequestMethod(requestMethod.toUpperCase());
            http.setRequestProperty("Authorization","Bearer " + token);
            http.setRequestProperty("Content-Type","application/json");
            http.setRequestProperty("Accept","application/json");

            return http;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserInformation(String userId, String token) throws IOException {
        HttpURLConnection http = connect("https://api.spotify.com/v1/users/" + userId, token, "GET");

        assert http != null;
        if(http.getResponseCode() == 200) {
            User connections;
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            Gson gson = new Gson();
            connections = gson.fromJson(reader, User.class);
            http.disconnect();

            return connections;
        } else {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        }
        return null;
    }

    public void pause(String token, String deviceId) throws IOException {
        HttpURLConnection http = connect("https://api.spotify.com/v1/me/player/pause?device_id=" + deviceId, token, "PUT");

        assert http != null;
        if(http.getResponseCode() == 200) {
            http.disconnect();
            System.out.println("successfull");
        } else {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage()); }
    }

    public List<Device> getDevices(String token) throws IOException {
        HttpURLConnection http = connect("https://api.spotify.com/v1/me/player/devices", token, "GET");

        assert http != null;
        if(http.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

            DeviceWrapper list = new Gson().fromJson(reader, DeviceWrapper.class);

            http.disconnect();
            for(Device devices : list.getDevices()) {
                devices.setTime(dtf.format(LocalDateTime.now()));
                deviceRepository.save(devices);
            }
            return list.getDevices();

        } else {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        }
        return null;
    }

    public Song getCurrentSong(String token) throws IOException {
        HttpURLConnection http = connect("https://api.spotify.com/v1/me/player/currently-playing?market=CH", token, "GET");

        assert http != null;
        if(http.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            ArrayList<String> arrayList = new ArrayList<>();

            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            JsonObject item = jsonObject.getAsJsonObject("item");

            JsonArray artists = item.getAsJsonArray("artists");
            artists.forEach(artist -> arrayList.add(artist.getAsJsonObject().get("name").getAsString()));

            Song song = new Song(
                    dtf.format(LocalDateTime.now()),
                    item.getAsJsonObject("album").get("name").getAsString(),
                    item.getAsJsonObject("album").get("release_date").getAsString(),
                    arrayList.toString(),
                    item.get("name").getAsString(),
                    item.get("popularity").getAsString()
            );

            http.disconnect();
            songRepository.save(song);
            return song;

        } else if (http.getResponseCode() == 204) {
            System.out.println("There is no song currently playing");
          return null;
        } else {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        }
        return null;
    }
}
