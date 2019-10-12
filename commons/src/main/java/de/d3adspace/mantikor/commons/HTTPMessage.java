package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

public class HTTPMessage {

  private final HTTPHeaders headers;
  private final HTTPBody body;

  HTTPMessage(HTTPHeaders headers, HTTPBody body) {
    this.headers = headers;
    this.body = body;
  }

  public HTTPBody getBody() {
    return body;
  }

  public HTTPHeaders getHeaders() {
    return headers;
  }

  public int getBodySize() {
    return body.getSize();
  }

  public int getHeaderCount() {
    return headers.getHeaderCount();
  }
}
