package dobby;

import java.util.List;

public interface IConfig {
    /**
     * Gets the value of the given key as an int
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as an int
     */
    int getInt(String key, int defaultValue);

    /**
     * Gets the value of the given key as an int
     *
     * @param key The key to get the value of
     * @return The value of the given key as an int
     */
    int getInt(String key);

    void setInt(String key, int value);

    /**
     * Gets the value of the given key as a string
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as a string
     */
    String getString(String key, String defaultValue);

    /**
     * Gets the value of the given key as a string
     *
     * @param key The key to get the value of
     * @return The value of the given key as a string
     */
    String getString(String key);

    void setString(String key, String value);

    /**
     * Gets the value of the given key as a boolean
     *
     * @param key          The key to get the value of
     * @param defaultValue The default value to return if the key is not found
     * @return The value of the given key as a boolean
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * Gets the value of the given key as a boolean
     *
     * @param key The key to get the value of
     * @return The value of the given key as a boolean
     */
    boolean getBoolean(String key);

    void setBoolean(String key, boolean value);

    double getFloat(String key, double defaultValue);

    double getFloat(String key);

    void setFloat(String key, double value);

    List<Object> getList(String key, List<Object> defaultValue);

    List<Object> getList(String key);

    void setList(String key, List<Object> value);
}
