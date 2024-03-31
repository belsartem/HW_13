package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:config/driver.properties"})

public interface DriverConfig extends Config {

    @Key("browser.name")
    String browserName();

    @Key("browser.version")
    String browserVersion();

    @Key("browser.size")
    String browserSize();

    @Key("remote.url")
    String remoteUrl();
}
