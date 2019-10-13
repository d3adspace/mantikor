package de.d3adspace.mantikor.commons;

import com.google.common.base.Preconditions;
import de.d3adspace.mantikor.commons.codec.*;

import java.net.URI;

/**
 * A http request.
 */
public class HTTPRequest extends HTTPMessage {

  /**
   * The request line.
   */
  private final HTTPRequestLine requestLine;

  private HTTPRequest(HTTPRequestLine requestLine, HTTPHeaders headers,
                      HTTPBody body) {
    super(headers, body);
    this.requestLine = requestLine;
  }

  public static HTTPRequest create(HTTPRequestLine requestLine, HTTPHeaders headers, HTTPBody body) {
    Preconditions.checkNotNull(requestLine);
    Preconditions.checkNotNull(headers);
    Preconditions.checkNotNull(body);

    return new HTTPRequest(requestLine, headers, body);
  }

  public static HTTPRequest createGet(URI uri) {
    HTTPBody emptyBody = HTTPBody.empty();
    HTTPHeaders emptyHeaders = HTTPHeaders.empty();
    HTTPRequestLine requestLine = HTTPRequestLine.asGet(uri);

    return create(requestLine, emptyHeaders, emptyBody);
  }

  public HTTPMethod getMethod() {
    return requestLine.getMethod();
  }

  public URI getUri() {
    return requestLine.getUri();
  }

  public HTTPVersion getVersion() {
    return requestLine.getVersion();
  }
}
