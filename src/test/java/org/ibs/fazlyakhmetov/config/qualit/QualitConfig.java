package org.ibs.fazlyakhmetov.config.qualit;

import org.aeonbits.owner.Config;

@Config.Sources({"file:C:\\Users\\Dinar\\IdeaProjects\\homeWorkIBS\\src\\test\\resources\\conf.properties"})
public interface QualitConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("http://localhost:8080/")
    String baseUrl();

    @Key("pathChromeDriver")
    @DefaultValue("src/test/resources/apps/chromedriver.exe")
    String chromeDriver();
}
