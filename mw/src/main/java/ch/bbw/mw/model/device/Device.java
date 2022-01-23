package ch.bbw.mw.model.device;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices" )

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Device {

    @Id
    @Column(name = "time")
    private String time;

    @Column(name = "device_id")
    private String id;

    @Column(name = "is_active")
    private boolean is_active;

    @Column(name = "is_private_session")
    private boolean is_private_session;

    @Column(name = "is_restricted")
    private boolean is_restricted;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "volume_percent")
    private int volume_percent;

}
