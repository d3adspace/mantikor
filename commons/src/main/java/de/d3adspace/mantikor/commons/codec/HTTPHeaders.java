package de.d3adspace.mantikor.commons.codec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper around HTTP headers.
 */
@Data
@NoArgsConstructor
public class HTTPHeaders {

    /**
     * The key for the header that contains the content length.
     */
    public static final String KEY_CONTENT_LENGTH = "Content-Length";

    /**
     * The key for the header that contains the content type.
     */
    public static final String KEY_CONTENT_TYPE = "Content-Type";

    /**
     * The map containing the headers.
     */
    private Map<String, String> headers = new HashMap<>();

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

    /**
     * Get the amount of headers.
     *
     * @return The header count.
     */
    public int getHeaderCount() {

        return headers.size();
    }
}
