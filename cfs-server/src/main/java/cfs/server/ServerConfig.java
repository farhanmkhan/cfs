package cfs.server;

import com.google.common.collect.ImmutableMap;

import java.util.Optional;
import java.util.OptionalInt;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The wrapper of the server related configuration.
 * <p>
 * Created by farhankhan on 6/20/17.
 */
public class ServerConfig {
    private final ImmutableMap<String, String> properties;

    /**
     * Constructs the object with immutable map.
     *
     * @param properties the properties which will be wrapped. NPE will be thrown if null value is provided.
     */
    public ServerConfig(ImmutableMap<String, String> properties) {
        checkNotNull(properties, "The specified properties is null.");
        this.properties = properties;
    }

    /**
     * Gets the config value for the given key.
     *
     * @param key the configuration key which will be used to lookup the value. NPE will be thrown if null is
     *            specified.
     * @return the optional configuration value for this given key.
     */
    public Optional<String> getConfig(String key) {
        checkNotNull(key, "The specified key is null.");
        return Optional.of(properties.get(key));
    }

    /**
     * Gets the config value for the given key.
     *
     * @param key          the configuration key which will be used to lookup the value.
     * @param defaultValue if value not found for the given {@code key}
     * @return the optional configuration value for this given key.
     */
    public String getConfig(String key, String defaultValue) {
        checkNotNull(key, "The specified key is null.");
        return properties.getOrDefault(key, defaultValue);
    }

    /**
     * Gets the config value as integer for the given key.
     *
     * @param key the configuration key which will be used to lookup the value. NPE will be thrown if null is
     *            specified.
     * @return the optional configuration value for this given key.
     */
    public OptionalInt getConfigAsInt(String key) {
        Optional<String> value = getConfig(key);
        return value.isPresent() ? OptionalInt.of(Integer.parseInt(value.get())) : OptionalInt.empty();
    }

    /**
     * Gets the config value for the given key.
     *
     * @param key          the configuration key which will be used to lookup the value.
     * @param defaultValue if value not found for the given {@code key}
     * @return the optional configuration value for this given key.
     */
    public int getConfigAsInt(String key, int defaultValue) {
        OptionalInt configAsInt = getConfigAsInt(key);
        return configAsInt.isPresent() ? configAsInt.getAsInt() : defaultValue;
    }
}
