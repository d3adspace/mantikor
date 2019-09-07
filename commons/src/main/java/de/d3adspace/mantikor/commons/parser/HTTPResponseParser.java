package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.HTTPResponseBuilder;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class HTTPResponseParser extends
  AbstractHTTPParser<String, HTTPResponse> {

  @Override
  public HTTPResponse parse(String rawHTTPResponse) {

    BufferedReader reader = new BufferedReader(
      new StringReader(rawHTTPResponse));

    // Read status line
    String statusLine = null;

    try {
      statusLine = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    HTTPStatusLine httpStatusLine = parseStatusLine(statusLine);

    // Read headers
    HTTPHeaders httpHeaders = parseHeaders(reader);

    // Read body
    HTTPBody httpBody = parseBody(httpHeaders, reader);

    return new HTTPResponseBuilder().setStatusLine(httpStatusLine)
      .setHeaders(httpHeaders)
      .setBody(httpBody).createHTTPResponse();
  }

  /**
   * Parse the status line out of the given string.
   *
   * @param statusLine The status line string.
   * @return The http status line.
   */
  private HTTPStatusLine parseStatusLine(String statusLine) {

    StringTokenizer tokenizer = new StringTokenizer(statusLine);

    // Parse status
    String nextToken = tokenizer.nextToken();
    HTTPVersion httpVersion = HTTPVersion.fromString(nextToken);

    // Parse status
    nextToken = tokenizer.nextToken();
    nextToken = " " + tokenizer.nextToken();

    HTTPStatus httpStatus = HTTPStatus.fromCode(nextToken);

    return new HTTPStatusLine(httpVersion, httpStatus);
  }
}
