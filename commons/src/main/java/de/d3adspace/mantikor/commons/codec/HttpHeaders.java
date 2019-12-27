package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper around HTTP headers.
 */
public final class HttpHeaders {

  /**
   * The key for the header that contains the content length.
   */
  public static final String KEY_CONTENT_LENGTH = "Content-Length";

  /**
   * The key for the header that contains the content type.
   */
  public static final String KEY_CONTENT_TYPE = "Content-Type";

  /**
   * The key for the header that contains the connection state.
   */
  public static final String KEY_CONNECTION = "Connection";

  /**
   * The key for the header that contains the server name.
   */
  public static final String KEY_SERVER = "Server";

  /**
   * The date of the server.
   */
  public static final String KEY_DATE = "Date";

  private Map<String, String> headers;

  private HttpHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  /**
   * Create a builder from a set of prototype headers.
   *
   * @param prototype Headers prototype.
   * @return Headers builder.
   */
  public static Builder newBuilder(HttpHeaders prototype) {
    Preconditions.checkNotNull(prototype);
    var headers = prototype.headers;
    return new Builder(new HashMap<>(headers));
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static HttpHeaders fromMap(Map<String, String> headers) {
    Preconditions.checkNotNull(headers);
    return new HttpHeaders(Maps.newHashMap(headers));
  }

  public static HttpHeaders empty() {
    return new HttpHeaders(Maps.newHashMap());
  }

  /**
   * Add a header by its key and value.
   *
   * @param key   The key.
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

  public Map<String, String> toMap() {
    return Maps.newHashMap(headers);
  }

  public static class Builder {
    private final Map<String, String> headers;

    private Builder(Map<String, String> headers) {
      this.headers = headers;
    }

    private Builder() {
      headers = Maps.newHashMap();
    }

    /**
     * Append a header represented by a key value pair.
     *
     * @param key Key for the header.
     * @param value Value for the header.
     * @return Builder instance.
     */
    public Builder withHeader(String key, String value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      headers.put(key, value);
      return this;
    }

    public HttpHeaders build() {
      return HttpHeaders.fromMap(headers);
    }
  }
}
