package ch.bbw.mw.controller;

import ch.bbw.mw.model.device.Device;
import ch.bbw.mw.model.Song;
import ch.bbw.mw.model.user.User;
import ch.bbw.mw.service.Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@Transactional
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/getUserInformation/{userId}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserInformation(@PathVariable String userId, @PathVariable String token) throws IOException {
        return service.getUserInformation(userId, token);
    }

    @PutMapping(value = "/play/pause/{token}/{deviceId}")
    public void pauseSong(@PathVariable String token, @PathVariable String deviceId) throws IOException {
        service.pause(token, deviceId);
    }

    @GetMapping(value = "/info/getdevice={token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getDevices(@PathVariable String token) throws IOException {
        return service.getDevices(token);
    }

    @GetMapping(value = "/play/currentsong={token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Song getCurrentSong(@PathVariable String token) throws IOException {
        return service.getCurrentSong(token);
    }

}
