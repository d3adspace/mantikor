package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

import java.net.URI;

public final class HttpRequestLine {
  private HttpMethod method;
  private URI uri;
  private HttpVersion version;

  private HttpRequestLine(HttpMethod method, URI uri, HttpVersion version) {
    this.method = method;
    this.uri = uri;
    this.version = version;
  }

  /**
   * Create a request line with a specific method, uri and version.
   *
   * @param method Http method.
   * @param uri Uri.
   * @param version Http version.
   * @return The request line.
   */
  public static HttpRequestLine create(
    HttpMethod method,
    URI uri,
    HttpVersion version
  ) {
    Preconditions.checkNotNull(method);
    Preconditions.checkNotNull(uri);
    Preconditions.checkNotNull(version);
    return new HttpRequestLine(method, uri, version);
  }

  /**
   * Create a status line with a specific method anf uri.
   *
   * @param method Http method.
   * @param uri Uri.
   * @return Request line.
   */
  public static HttpRequestLine withMethod(HttpMethod method, URI uri) {
    Preconditions.checkNotNull(method);
    Preconditions.checkNotNull(uri);
    return create(method, uri, HttpVersion.HTTP_VERSION_1_1);
  }

  /**
   * Will return a new request line with get method.
   *
   * @param uri Uri.
   * @return Request line.
   */
  public static HttpRequestLine asGet(URI uri) {
    Preconditions.checkNotNull(uri);
    return withMethod(HttpMethod.GET, uri);
  }

  public HttpMethod getMethod() {
    return method;
  }

  public HttpVersion getVersion() {
    return version;
  }

  public URI getUri() {
    return uri;
  }
}
