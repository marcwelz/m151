package ch.bbw.mw.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "recently_played_songs" )

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Song {

    @Id
    @Column(name = "time")
    private String time;

    @Column(name = "album_name")
    private String album_name;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "artists")
    private String artists;

    @Column(name = "song_name")
    private String song_name;

    @Column(name = "popularity")
    private String popularity;
}
