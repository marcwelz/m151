package ch.bbw.mw.model.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeviceWrapper {
    private ArrayList<Device> devices;
}
