package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPMessage;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import lombok.Data;

public abstract class AbstractHTTPParser<InputType, OutputType extends HTTPMessage> {

  /**
   * Parse the an output from the given input.
   *
   * @param input The input.
   * @return The parsed output.
   */
  public abstract OutputType parse(InputType input);

  /**
   * Parse the http headers from the given reader by lines.
   *
   * @param reader The reader.
   * @return The http headers.
   */
  HTTPHeaders parseHeaders(BufferedReader reader) {

    HTTPHeaders httpHeaders = null;

    try {
      httpHeaders = new HTTPHeaders();
      String currentLine = reader.readLine();

      while (currentLine != null && !currentLine.isEmpty()) {

        HeaderPair header = parseHeader(currentLine);
        httpHeaders.addHeader(header.getKey(), header.getValue());

        currentLine = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return httpHeaders;
  }

  /**
   * Parse the http body from the given reader. Headers are needed to determine
   * content length.
   *
   * @param httpHeaders The http headers.
   * @param reader The reader.
   * @return The http body.
   */
  HTTPBody parseBody(HTTPHeaders httpHeaders, BufferedReader reader) {

    if (!httpHeaders.hasHeader(HTTPHeaders.KEY_CONTENT_LENGTH)) {
      return new HTTPBody(new char[0]);
    }

    int contentLength = Integer
      .parseInt(httpHeaders.getHeader(HTTPHeaders.KEY_CONTENT_LENGTH));
    char[] charBuffer = new char[contentLength];

    try {
      int charsRead = reader.read(charBuffer);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new HTTPBody(charBuffer);
  }

  /**
   * Parse a header from the given line.
   *
   * @param line The line.
   * @return The header pair
   */
  private HeaderPair parseHeader(String line) {

    StringTokenizer stringTokenizer = new StringTokenizer(line, ":");

    return new HeaderPair(stringTokenizer.nextToken().trim(),
      stringTokenizer.nextToken().trim());
  }


  @Data
  private class HeaderPair {

    private final String key;
    private final String value;
  }
}
