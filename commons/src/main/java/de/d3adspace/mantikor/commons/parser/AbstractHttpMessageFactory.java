package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HttpMessage;
import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public abstract class AbstractHttpMessageFactory
  <InputT, OutputT extends HttpMessage> {

  /**
   * Parse the an output from the given input.
   *
   * @param input The input.
   * @return The parsed output.
   */
  public abstract OutputT parse(InputT input);

  /**
   * Parse the http headers from the given reader by lines.
   *
   * @param reader The reader.
   * @return The http headers.
   */
  HttpHeaders parseHeaders(BufferedReader reader) {
    try {
      HttpHeaders httpHeaders = HttpHeaders.empty();
      readHeaders(reader, httpHeaders);
      return httpHeaders;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void readHeaders(
    BufferedReader reader,
    HttpHeaders httpHeaders
  ) throws IOException {
    var currentLine = reader.readLine();
    while (currentLine != null && !currentLine.isEmpty()) {
      HeaderPair header = parseHeader(currentLine);
      httpHeaders.addHeader(header.getKey(), header.getValue());
      currentLine = reader.readLine();
    }
  }

  /**
   * Parse the http body from the given reader. Headers are needed to determine
   * content length.
   *
   * @param httpHeaders The http headers.
   * @param reader      The reader.
   * @return The http body.
   */
  HttpBody parseBody(HttpHeaders httpHeaders, BufferedReader reader) {
    if (!httpHeaders.hasHeader(HttpHeaders.KEY_CONTENT_LENGTH)) {
      return HttpBody.empty();
    }
    var contentLength = Integer
      .parseInt(httpHeaders.getHeader(HttpHeaders.KEY_CONTENT_LENGTH));
    var charBuffer = new char[contentLength];
    tryReadBuffer(charBuffer, reader);
    return HttpBody.withContent(charBuffer);
  }

  private void tryReadBuffer(char[] charBuffer, BufferedReader reader) {
    try {
      int charsRead = reader.read(charBuffer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Parse a header from the given line.
   *
   * @param line The line.
   * @return The header pair
   */
  private HeaderPair parseHeader(String line) {
    var stringTokenizer = new StringTokenizer(line, ":");
    return new HeaderPair(stringTokenizer.nextToken().trim(),
      stringTokenizer.nextToken().trim());
  }

  private class HeaderPair {
    private final String key;
    private final String value;

    private HeaderPair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }
  }
}
