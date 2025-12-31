package dobby;

import common.inject.annotations.Inject;
import common.inject.annotations.RegisterFor;
import dobby.exceptions.MalformedJsonException;
import dobby.util.json.NewJson;
import common.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for loading the config file
 */
@RegisterFor(IConfig.class)
public class Config implements IConfig {
    private static final Logger LOGGER = new Logger(Config.class);
    private NewJson configJson = new NewJson();

    @Inject
    public Config() {
        try {
            loadConfig();
        } catch (MalformedJsonException e) {
            LOGGER.error("Failed to load config file");
            LOGGER.trace(e);
            System.exit(1);
        }
    }

    /**
     * Loads the config file
     */
    private void loadConfig() throws MalformedJsonException {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("application.json")) {
            if (stream == null) {
                throw new IllegalStateException("application.json not found at root of the JAR");
            }

            String rawConfig = loadFileContent(stream);
            configJson = NewJson.parse(rawConfig);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.json", e);
        }
    }


    private String loadFileContent(InputStream stream) {
        if (stream == null) {
            LOGGER.error("application.json not found");
            System.exit(1);
        }

        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    /**
     * Gets the value of the given key as an int
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as an int
     */
    public int getInt(String key, int defaultValue) {
        return getIntOrDefault(key, defaultValue);
    }

    /**
     * Gets the value of the given key as an int
     *
     * @param key The key to get the value of
     * @return The value of the given key as an int
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    public void setInt(String key, int value) {
        configJson.setInt(key, value);
    }

    /**
     * Gets the value of the given key as a string
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as a string
     */
    public String getString(String key, String defaultValue) {
        return getStringOrDefault(key, defaultValue);
    }

    /**
     * Gets the value of the given key as a string
     *
     * @param key The key to get the value of
     * @return The value of the given key as a string
     */
    public String getString(String key) {
        return getString(key, "");
    }

    public void setString(String key, String value) {
        configJson.setString(key, value);
    }

    /**
     * Gets the value of the given key as a boolean
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as a boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getBooleanOrDefault(key, defaultValue);
    }

    /**
     * Gets the value of the given key as a boolean
     *
     * @param key The key to get the value of
     * @return The value of the given key as a boolean
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value) {
        configJson.setBoolean(key, value);
    }

    public double getFloat(String key, double defaultValue) {
        final Double floatValue = configJson.getFloat(key);

        if (floatValue == null) {
            return defaultValue;
        }

        return floatValue;
    }

    public double getFloat(String key) {
        return getFloat(key, 0.0);
    }

    public void setFloat(String key, double value) {
        configJson.setFloat(key, value);
    }

    public List<Object> getList(String key, List<Object> defaultValue) {
        return getListOrDefault(key, defaultValue);
    }

    public List<Object> getList(String key) {
        return getList(key, List.of());
    }

    public void setList(String key, List<Object> value) {
        configJson.setList(key, value);
    }

    private List<Object> getListOrDefault(String key, List<Object> defaultValue) {
        final List<Object> listValue = configJson.getList(key);

        if (listValue == null) {
            return defaultValue;
        }

        return listValue;
    }

    private int getIntOrDefault(String key, int defaultValue) {
        final Integer intValue = configJson.getInt(key);

        if (intValue == null) {
            return defaultValue;
        }

        return intValue;
    }

    private String getStringOrDefault(String key, String defaultValue) {
        String stringValue = configJson.getString(key);

        if (stringValue == null) {
            return defaultValue;
        }

        return stringValue;
    }

    private boolean getBooleanOrDefault(String key, boolean defaultValue) {
        final Boolean boolValue = configJson.getBoolean(key);

        if (boolValue == null) {
            return defaultValue;
        }

        return boolValue;
    }
}
