package cfs.server;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by farhankhan on 6/20/17.
 */
public class ServerConfigTest {
    private ServerConfig serverConfig;

    @Before
    public void setup() {
        ImmutableMap.Builder<String, String> configMapBuilder = new ImmutableMap.Builder<>();
        configMapBuilder.put("server.port", "8080");
        configMapBuilder.put("server.baseURI", "http://localhost:8080/api");
        configMapBuilder.put("server.thread.pool", "80");
        configMapBuilder.put("server.thread.active", "10");
        this.serverConfig = new ServerConfig(configMapBuilder.build());
    }

    @Test
    public void testGetConfig() {
        Optional<String> portOpt = serverConfig.getConfig("server.port");
        assertTrue(portOpt.isPresent());
        assertEquals("8080", portOpt.get());

        Optional<String> baseURIOpt = serverConfig.getConfig("server.baseURI");
        assertTrue(baseURIOpt.isPresent());
        assertEquals("http://localhost:8080/api", baseURIOpt.get());

        String baseURI = serverConfig.getConfig("server.baseURI", "localhost");
        assertEquals("http://localhost:8080/api", baseURI);

        String contextValue = serverConfig.getConfig("server.context", "api");
        assertEquals("api", contextValue);

    }

    @Test
    public void testGetConfigAsInt() {
        OptionalInt portOpt = serverConfig.getConfigAsInt("server.port");
        assertTrue(portOpt.isPresent());
        assertEquals(8080, portOpt.getAsInt());

        int threadPoolOpt = serverConfig.getConfigAsInt("server.thread.pool", 10);
        assertEquals(80, threadPoolOpt);
    }
}
