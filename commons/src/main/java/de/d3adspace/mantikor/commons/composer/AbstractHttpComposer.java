package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HttpMessage;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;

import java.util.Map;

public abstract class AbstractHttpComposer<InputT extends HttpMessage,
  OutputT> {
  /**
   * Compose the given output out of the given input.
   *
   * @param input The input.
   * @return The output.
   */
  public abstract OutputT compose(InputT input);

  /**
   * Encode an http message into a string buffer.
   *
   * @param message The http message.
   * @return The string buffer.
   */
  StringBuffer encodeHeadersAndBody(HttpMessage message) {
    var stringBuffer = new StringBuffer();
    var headers = message.getHeaders();
    StringBuffer headersBuffer = encodeHeaders(headers);
    stringBuffer.append(headersBuffer);
    var body = message.getBody();
    StringBuffer bodyBuffer = encodeBody(body);
    stringBuffer.append(bodyBuffer);
    return stringBuffer;
  }

  /**
   * Encode the given http headers into a string buffer.
   *
   * @param httpHeaders The headers.
   * @return The string buffer.
   */
  private StringBuffer encodeHeaders(HttpHeaders httpHeaders) {
    var stringBuffer = new StringBuffer();
    var headers = httpHeaders.toMap();
    writeHeaders(stringBuffer, headers);
    return stringBuffer;
  }

  private void writeHeaders(
    StringBuffer stringBuffer,
    Map<String, String> headers
  ) {
    headers.forEach((key, value) -> {
      stringBuffer.append(key);
      stringBuffer.append(": ");
      stringBuffer.append(value);
      stringBuffer.append(MantikorCommons.LF);
    });
  }

  /**
   * Encode an http body into a string buffer.
   *
   * @param httpBody The http body.
   * @return The string buffer.
   */
  private StringBuffer encodeBody(HttpBody httpBody) {
    var stringBuffer = new StringBuffer();
    stringBuffer.append(MantikorCommons.CRLF);
    stringBuffer.append(httpBody.getContent());
    return stringBuffer;
  }
}
