package com.ghim.s3m.server;

import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 *
 */
public class Configuration {
    private final int port;
    private final URI baseURI;
    private final ImmutableMap<ConfigKey, String> configValues;

    public static Configuration fromPropertiesFile(URL propertiesResource) throws IOException, URISyntaxException {
        try (InputStream inputStream = propertiesResource.openStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);
            int port = Integer.valueOf(properties.getProperty(ConfigKey.SERVER_PORT.getKey(), ConfigKey.SERVER_PORT.getDefaultValue()));
            URI baseURI = new URI(properties.getProperty(ConfigKey.SERVER_BASE_URI.getKey(), ConfigKey.SERVER_BASE_URI.getDefaultValue()));
            ImmutableMap.Builder<ConfigKey, String> builder = ImmutableMap.builder();

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Optional<ConfigKey> optConfigKey = ConfigKey.lookup((String) entry.getKey());

                if (optConfigKey.isPresent()) {
                    String thisValue = (String) entry.getValue();
                    builder.put(optConfigKey.get(), thisValue);
                }
            }

            return new Configuration(port, baseURI, builder.build());
        }
    }

    public Configuration(int port, URI baseURI, ImmutableMap<ConfigKey, String> configValues) {
        this.port = port;
        this.baseURI = baseURI;
        this.configValues = configValues;
    }

    public enum ConfigKey {
        SERVER_PORT("server.port", "8080"), SERVER_BASE_URI("server.baseURI", "");

        private final String key;
        private final String defaultValue;

        public static Optional<ConfigKey> lookup(String key) {
            for (ConfigKey configKey : values()) {
                if (configKey.getKey().equalsIgnoreCase(key)) {
                    return Optional.of(configKey);
                }
            }

            return Optional.empty();
        }

        ConfigKey(String key, String defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }

        public String getKey() {
            return key;
        }

        public String getDefaultValue() {
            return defaultValue;
        }
    }
}
