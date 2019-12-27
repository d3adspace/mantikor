package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;

public class HttpMessage {
  private final HttpHeaders headers;
  private final HttpBody body;

  HttpMessage(HttpHeaders headers, HttpBody body) {
    this.headers = headers;
    this.body = body;
  }

  public HttpBody getBody() {
    return body;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

  public int getBodySize() {
    return body.getSize();
  }

  public int getHeaderCount() {
    return headers.getHeaderCount();
  }
}
