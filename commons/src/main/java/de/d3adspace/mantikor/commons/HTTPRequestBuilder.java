package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import java.net.URI;

public class HTTPRequestBuilder {

  private HTTPRequestLine requestLine = new HTTPRequestLine(HTTPMethod.GET,
    URI.create("/"),
    HTTPVersion.HTTP_VERSION_1_1);
  private HTTPHeaders headers = new HTTPHeaders();
  private HTTPBody body = new HTTPBody();

  public HTTPRequestBuilder setMethod(HTTPMethod method) {
    requestLine.setMethod(method);
    return this;
  }

  public HTTPRequestBuilder setLocation(String location) {
    requestLine.setUri(URI.create(location));
    return this;
  }

  public HTTPRequestBuilder setVersion(HTTPVersion version) {
    requestLine.setVersion(version);
    return this;
  }

  public HTTPRequestBuilder setRequestLine(HTTPRequestLine requestLine) {
    this.requestLine = requestLine;
    return this;
  }

  public HTTPRequestBuilder setHeader(String key, String value) {
    headers.addHeader(key, value);
    return this;
  }

  public HTTPRequestBuilder setHeaders(HTTPHeaders headers) {
    this.headers = headers;
    return this;
  }

  public HTTPRequestBuilder setBody(byte[] bytes) {
    return setBody(new String(bytes));
  }

  public HTTPRequestBuilder setBody(String body) {
    return setBody(body.toCharArray());
  }

  public HTTPRequestBuilder setBody(char[] body) {
    this.body.setContent(body);
    return this;
  }

  public HTTPRequestBuilder setBody(HTTPBody body) {
    this.body = body;
    return this;
  }

  public HTTPRequest createHTTPRequest() {
    return new HTTPRequest(requestLine, headers, body);
  }
}
