package org.ibs.fazlyakhmetov.config.qualit;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/conf.properties"})
public interface QualitConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("http://localhost:8080/")
    String baseUrl();

    @Key("pathChromeDriver")
    @DefaultValue("src/test/resources/apps/chromedriver.exe")
    String chromeDriver();

    @Key("selenoid.url")
    @DefaultValue("http://149.154.71.152:4444/wd/hub")
    String selenoidUrl();

    @Key("type.browser")
    @DefaultValue("chrome")
    String typeBrowser();

    @Key("remote.driver")
    @DefaultValue("remote")
    String remoteDriver();
}
