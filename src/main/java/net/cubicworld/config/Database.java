package net.cubicworld.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Database {
    String url;
    String username;
    String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Database(@JsonProperty("url") String url, @JsonProperty("username") String username,
                    @JsonProperty("password") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
