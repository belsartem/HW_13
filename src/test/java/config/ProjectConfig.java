package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${environment}.properties")

public interface ProjectConfig extends Config {

    @Key("first.name")
    String firstName();

    @Key("last.name")
    String lastName();

    @Key("user.email")
    String userEmail();
    @Key("image.name")
    String imageName();
}
