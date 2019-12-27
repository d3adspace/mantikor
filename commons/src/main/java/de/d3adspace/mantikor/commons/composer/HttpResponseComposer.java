package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HttpStatus;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

public class HttpResponseComposer extends
  AbstractHttpComposer<HttpResponse, String> {

  /**
   * Compose a HTTP message out of the given http response.
   *
   * @param response The response.
   * @return The http method.
   */
  @Override
  public String compose(HttpResponse response) {
    var responseBuilder = new StringBuilder();
    writeVersion(responseBuilder, response);
    writeStatus(responseBuilder, response);
    writeHeaderAndBody(responseBuilder, response);
    return responseBuilder.toString();
  }

  private void writeVersion(
    StringBuilder responseBuilder,
    HttpResponse response
  ) {
    HttpVersion version = response.getVersion();
    responseBuilder.append(version.getVersion());
    responseBuilder.append(" ");
  }

  private void writeStatus(
    StringBuilder responseBuilder,
    HttpResponse response
  ) {
    HttpStatus status = response.getStatus();
    responseBuilder.append(status.getStatusCode());
    responseBuilder.append(" ");
    responseBuilder.append(status.getStatusMessage());
    responseBuilder.append(MantikorCommons.CRLF);
  }

  private void writeHeaderAndBody(
    StringBuilder responseBuilder,
    HttpResponse response
  ) {
    StringBuffer headerBodyBuffer = encodeHeadersAndBody(response);
    responseBuilder.append(headerBodyBuffer);
  }
}
