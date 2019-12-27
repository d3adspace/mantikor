package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HttpMethod;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

import java.net.URI;

public class HttpRequestComposer extends
  AbstractHttpComposer<HttpRequest, String> {

  /**
   * Compose a HTTP message out of the given http request.
   *
   * @param request The request.
   * @return The http message.
   */
  @Override
  public String compose(HttpRequest request) {
    var requestBuilder = new StringBuilder();
    writeMethod(requestBuilder, request);
    writeUri(requestBuilder, request);
    writeVersion(requestBuilder, request);
    writeHeaderAndBody(requestBuilder, request);
    return requestBuilder.toString();
  }

  private void writeMethod(StringBuilder requestBuilder, HttpRequest request) {
    var method = request.getMethod();
    requestBuilder.append(method.name());
    requestBuilder.append(" ");
  }

  private void writeUri(StringBuilder requestBuilder, HttpRequest request) {
    var uri = request.getUri();
    requestBuilder.append(uri.toString());
    requestBuilder.append(" ");
  }

  private void writeVersion(StringBuilder requestBuilder, HttpRequest request) {
    var version = request.getVersion();
    requestBuilder.append(version.getVersion());
    requestBuilder.append(MantikorCommons.CRLF);
  }

  private void writeHeaderAndBody(
    StringBuilder requestBuilder,
    HttpRequest request
  ) {
    var headerBuffer = encodeHeadersAndBody(request);
    requestBuilder.append(headerBuffer);
  }
}
