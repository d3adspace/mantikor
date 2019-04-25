package de.d3adspace.mantikor.commons.codec;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper around HTTP headers.
 */
public class HTTPHeaders {

    public static final String KEY_CONTENT_LENGTH = "Content-Length";
    /**
     * The map containing the headers.
     */
    private final Map<String, String> headers;

    /**
     * Create a new http header container.
     *
     * @param headers The headers.
     */
    public HTTPHeaders(Map<String, String> headers) {

        this.headers = headers;
    }

    /**
     * Create a new empty http header container.
     */
    public HTTPHeaders() {

        this(new HashMap<String, String>());
    }

    /**
     * Add a header by its key and value.
     *
     * @param key The key.
     * @param value The value.
     */
    public void addHeader(String key, String value) {

        headers.put(key, value);
    }

    /**
     * Remove the header with the given key.
     *
     * @param key The key.
     */
    public void removeHeader(String key) {

        headers.remove(key);
    }

    /**
     * Get a header value by its key.
     *
     * @param key The key of the header.
     * @return The value.
     */
    public String getHeader(String key) {

        return headers.get(key);
    }

    /**
     * Check if there is a header with the given key.
     *
     * @param key The key.
     * @return If there is a header with the given key.
     */
    public boolean hasHeader(String key) {

        return headers.containsKey(key);
    }

    @Override
    public String toString() {
        return "HTTPHeaders{" +
                "headers=" + headers +
                '}';
    }
}
