package ru.guess_the_song.server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import(ServerConfig.class)
public class AppConfig {
//    @Autowired
//    Environment environment;
//    private final ServerConfig serverConfig;
//    public AppConfig(ServerConfig serverConfig) {
//        this.serverConfig = serverConfig;
//    }
//
//    public ServerConfig getServerConfig() {
//        return serverConfig;
//    }
}
