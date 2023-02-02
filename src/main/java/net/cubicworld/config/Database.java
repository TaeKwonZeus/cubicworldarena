package net.cubicworld.config;

import lombok.Value;

@Value
public class Database {
    String url;
    String username;
    String password;
}
