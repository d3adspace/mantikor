package de.d3adspace.mantikor.commons;

import com.google.common.base.Preconditions;
import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;
import de.d3adspace.mantikor.commons.codec.HttpStatus;
import de.d3adspace.mantikor.commons.codec.HttpStatusLine;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

import java.util.Map;

public final class HttpResponse extends HttpMessage {
  private final HttpStatusLine statusLine;

  private HttpResponse(
    HttpStatusLine statusLine,
    HttpHeaders headers,
    HttpBody body
  ) {
    super(headers, body);
    this.statusLine = statusLine;
  }

  /**
   * Create a response with a status, all headers and full body.
   *
   * @param statusLine Http status line.
   * @param headers Http headers.
   * @param body Http body.
   * @return Http response.
   */
  public static HttpResponse create(
    HttpStatusLine statusLine,
    HttpHeaders headers,
    HttpBody body
  ) {
    Preconditions.checkNotNull(statusLine);
    Preconditions.checkNotNull(headers);
    Preconditions.checkNotNull(body);
    return new HttpResponse(statusLine, headers, body);
  }

  /**
   * Create a response with the 200 Ok Status.
   *
   * @return Http ok response.
   */
  public static HttpResponse ok() {
    HttpStatusLine statusLine = HttpStatusLine.ok();
    HttpHeaders emptyHeaders = HttpHeaders.empty();
    HttpBody emptyBody = HttpBody.empty();
    return create(statusLine, emptyHeaders, emptyBody);
  }

  /**
   * Create a response with the 200 Ok Status and a body.
   *
   * @param bodyContent Body content.
   * @return Http response.
   */
  public static HttpResponse ok(char[] bodyContent) {
    var statusLine = HttpStatusLine.ok();
    var emptyHeaders = HttpHeaders.empty();
    var emptyBody = HttpBody.withContent(bodyContent);
    return create(statusLine, emptyHeaders, emptyBody);
  }

  /**
   * Create a response with the 404 not found Status.
   *
   * @return Http response.
   */
  public static HttpResponse notFound() {
    var statusLine = HttpStatusLine.notFound();
    var emptyHeaders = HttpHeaders.empty();
    var emptyBody = HttpBody.empty();
    return create(statusLine, emptyHeaders, emptyBody);
  }

  /**
   * Create a response with the 403 forbidden status.
   *
   * @return Http response.
   */
  public static HttpResponse forbidden() {
    HttpStatusLine statusLine = HttpStatusLine.forbidden();
    HttpHeaders emptyHeaders = HttpHeaders.empty();
    HttpBody emptyBody = HttpBody.empty();
    return create(statusLine, emptyHeaders, emptyBody);
  }

  public HttpVersion getVersion() {
    return statusLine.getVersion();
  }

  public HttpStatus getStatus() {
    return statusLine.getStatus();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private HttpVersion version;
    private HttpStatus status;
    private Map<String, String> headers;
    private char[] bodyContent;

    /**
     * Build a full featured http response.
     *
     * @return Http response.
     */
    public HttpResponse build() {
      var statusLine = HttpStatusLine.create(version, status);
      var httpHeaders = HttpHeaders.fromMap(headers);
      var httpBody = HttpBody.withContent(bodyContent);
      return HttpResponse.create(statusLine, httpHeaders, httpBody);
    }

    public Builder withStatus(HttpStatus status) {
      this.status = status;
      return this;
    }

    public Builder withVersion(HttpVersion version) {
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
