package ch.bbw.mw.model.user;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    public String display_name;
    public ExternalUrls external_urls;
    public Followers followers;
    public String href;
    public String id;
    public String type;
    public String uri;
}
