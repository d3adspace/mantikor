package de.d3adspace.mantikor.commons;

import com.google.common.base.Preconditions;
import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;
import de.d3adspace.mantikor.commons.codec.HttpMethod;
import de.d3adspace.mantikor.commons.codec.HttpRequestLine;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

import java.net.URI;

public final class HttpRequest extends HttpMessage {
  private final HttpRequestLine requestLine;

  private HttpRequest(
    HttpRequestLine requestLine,
    HttpHeaders headers,
    HttpBody body
  ) {
    super(headers, body);
    this.requestLine = requestLine;
  }

  /**
   * Create a request from a request line, all headers and the full body.
   *
   * @param requestLine Http request line.
   * @param headers Http headers.
   * @param body Http body.
   * @return Http request.
   */
  public static HttpRequest create(
    HttpRequestLine requestLine,
    HttpHeaders headers,
    HttpBody body
  ) {
    Preconditions.checkNotNull(requestLine);
    Preconditions.checkNotNull(headers);
    Preconditions.checkNotNull(body);
    return new HttpRequest(requestLine, headers, body);
  }

  /**
   * Create a get request .
   *
   * @param uri Uri.
   * @return Http get request.
   */
  public static HttpRequest createGet(URI uri) {
    HttpBody emptyBody = HttpBody.empty();
    HttpHeaders emptyHeaders = HttpHeaders.empty();
    HttpRequestLine requestLine = HttpRequestLine.asGet(uri);
    return create(requestLine, emptyHeaders, emptyBody);
  }

  public HttpMethod getMethod() {
    return requestLine.getMethod();
  }

  public URI getUri() {
    return requestLine.getUri();
  }

  public HttpVersion getVersion() {
    return requestLine.getVersion();
  }
}
