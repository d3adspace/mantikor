package de.d3adspace.mantikor.commons;

import com.google.common.base.Preconditions;
import de.d3adspace.mantikor.commons.codec.*;

import java.util.Map;

public class HTTPResponse extends HTTPMessage {

  /**
   * The status line with version and message.
   */
  private final HTTPStatusLine statusLine;

  private HTTPResponse(HTTPStatusLine statusLine, HTTPHeaders headers,
    HTTPBody body) {
    super(headers, body);
    this.statusLine = statusLine;
  }

  public static HTTPResponse create(HTTPStatusLine statusLine, HTTPHeaders headers, HTTPBody body) {
    Preconditions.checkNotNull(statusLine);
    Preconditions.checkNotNull(headers);
    Preconditions.checkNotNull(body);

    return new HTTPResponse(statusLine, headers, body);
  }

  public static HTTPResponse ok() {
    HTTPStatusLine statusLine = HTTPStatusLine.ok();
    HTTPHeaders emptyHeaders = HTTPHeaders.empty();
    HTTPBody emptyBody = HTTPBody.empty();

    return create(statusLine, emptyHeaders, emptyBody);
  }


  public static HTTPResponse notFound() {
    HTTPStatusLine statusLine = HTTPStatusLine.notFound();
    HTTPHeaders emptyHeaders = HTTPHeaders.empty();
    HTTPBody emptyBody = HTTPBody.empty();

    return create(statusLine, emptyHeaders, emptyBody);
  }

  public static HTTPResponse forbidden() {
    HTTPStatusLine statusLine = HTTPStatusLine.forbidden();
    HTTPHeaders emptyHeaders = HTTPHeaders.empty();
    HTTPBody emptyBody = HTTPBody.empty();

    return create(statusLine, emptyHeaders, emptyBody);
  }

  public static HTTPResponse ok(char[] bodyContent) {
    HTTPStatusLine statusLine = HTTPStatusLine.ok();
    HTTPHeaders emptyHeaders = HTTPHeaders.empty();
    HTTPBody emptyBody = HTTPBody.withContent(bodyContent);

    return create(statusLine, emptyHeaders, emptyBody);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public HTTPVersion getVersion() {
    return statusLine.getVersion();
  }

  public HTTPStatus getStatus() {
    return statusLine.getStatus();
  }

  public static class Builder {

    private HTTPVersion version;
    private HTTPStatus status;
    private Map<String, String> headers;
    private char[] bodyContent;

    public HTTPResponse build() {
      HTTPStatusLine statusLine = HTTPStatusLine.create(version, status);
      HTTPHeaders httpHeaders = HTTPHeaders.fromMap(headers);
      HTTPBody httpBody = HTTPBody.withContent(bodyContent);

      return HTTPResponse.create(statusLine, httpHeaders, httpBody);
    }

    public Builder withStatus(HTTPStatus status) {
      this.status = status;
      return this;
    }

    public Builder withVersion(HTTPVersion version) {
      this.version = version;
      return this;
    }

    public Builder withHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder withBodyContent(char[] bodyContent) {
      this.bodyContent = bodyContent;
      return this;
    }
  }
}
