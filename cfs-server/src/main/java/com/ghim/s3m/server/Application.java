package com.ghim.s3m.server;

import com.google.common.base.Strings;

import java.net.URL;

/**
 *
 */
public class Application {
    public static void main(String[] args) throws Exception {
        String env = Strings.emptyToNull(System.getProperty("app.env"));
        String propertyResource = env == null ? "server.properties" : "server-" + env + ".properties";
        URL propertyResourceURL = ClassLoader.getSystemResource(propertyResource);

        Configuration configuration = Configuration.fromPropertiesFile(propertyResourceURL);

    }
}
