package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.codec.HttpBody;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;
import de.d3adspace.mantikor.commons.codec.HttpStatus;
import de.d3adspace.mantikor.commons.codec.HttpStatusLine;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class HttpResponseFactory extends
  AbstractHttpMessageFactory<String, HttpResponse> {

  @Override
  public HttpResponse parse(String rawHttpResponse) {
    var reader = new BufferedReader(new StringReader(rawHttpResponse));
    String statusLine = tryReadStatusLine(reader);
    var httpStatusLine = parseStatusLine(statusLine);
    var httpHeaders = parseHeaders(reader);
    var httpBody = parseBody(httpHeaders, reader);
    return HttpResponse.create(httpStatusLine, httpHeaders, httpBody);
  }

  private String tryReadStatusLine(BufferedReader reader) {
    try {
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * Parse the status line out of the given string.
   *
   * @param statusLine The status line string.
   * @return The http status line.
   */
  private HttpStatusLine parseStatusLine(String statusLine) {
    var tokenizer = new StringTokenizer(statusLine);
    var nextToken = tokenizer.nextToken();
    var httpVersion = HttpVersion.fromString(nextToken);
    nextToken = tokenizer.nextToken();
    nextToken = " " + tokenizer.nextToken();
    var httpStatus = HttpStatus.fromCode(nextToken);
    return HttpStatusLine.create(httpVersion, httpStatus);
  }
}
