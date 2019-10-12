package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

import java.net.URI;

/**
 * The request line containing the HTTP method, the URI and the version.
 */
public class HTTPRequestLine {

  /**
   * The HTTP method.
   */
  private HTTPMethod method;

  /**
   * The HTTP URI.
   */
  private URI uri;

  /**
   * The HTTP version.
   */
  private HTTPVersion version;

  private HTTPRequestLine(HTTPMethod method, URI uri, HTTPVersion version) {
    this.method = method;
    this.uri = uri;
    this.version = version;
  }

  public HTTPMethod getMethod() {
    return method;
  }

  public HTTPVersion getVersion() {
    return version;
  }

  public URI getUri() {
    return uri;
  }

  public static HTTPRequestLine create(HTTPMethod method, URI uri, HTTPVersion version) {
    Preconditions.checkNotNull(method);
    Preconditions.checkNotNull(uri);
    Preconditions.checkNotNull(version);

    return new HTTPRequestLine(method, uri, version);
  }

  public static HTTPRequestLine withMethod(HTTPMethod method, URI uri) {
    Preconditions.checkNotNull(method);
    Preconditions.checkNotNull(uri);

    return create(method, uri, HTTPVersion.HTTP_VERSION_1_1);
  }

  public static HTTPRequestLine asGet(URI uri) {
    Preconditions.checkNotNull(uri);

    return withMethod(HTTPMethod.GET, uri);
  }
}
