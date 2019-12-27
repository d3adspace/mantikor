package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.codec.HttpMethod;
import de.d3adspace.mantikor.commons.codec.HttpRequestLine;
import de.d3adspace.mantikor.commons.codec.HttpVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.StringTokenizer;

public class HttpRequestMessageFactory
  extends AbstractHttpMessageFactory<String, HttpRequest> {
  /**
   * Create a request from its raw form.
   *
   * @param rawHttpRequest The raw data.
   * @return The http request.
   */
  @Override
  public HttpRequest parse(String rawHttpRequest) {
    var reader = new BufferedReader(new StringReader(rawHttpRequest));
    HttpRequestLine requestLine = tryReadStatusLine(reader);
    var httpHeaders = parseHeaders(reader);
    var httpBody = parseBody(httpHeaders, reader);
    return HttpRequest.create(requestLine, httpHeaders, httpBody);
  }

  private HttpRequestLine tryReadStatusLine(BufferedReader reader) {
    try {
      String currentLine = reader.readLine();
      return parseRequestLine(currentLine);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Parse the HTTP request line from the given text line.
   *
   * @param line The line.
   * @return The http request line.
   */
  private HttpRequestLine parseRequestLine(String line) {
    var tokenizer = new StringTokenizer(line);
    var nextToken = tokenizer.nextToken();
    var method = parseRequestMethod(nextToken);
    nextToken = tokenizer.nextToken();
    var uri = parseRequestUri(nextToken);
    nextToken = tokenizer.nextToken();
    var version = parseRequestVersion(nextToken);
    return HttpRequestLine.create(method, uri, version);
  }

  /**
   * Parse the request version from the given token.
   *
   * @param nextToken The token.
   * @return The requests HTTP version.
   */
  private HttpVersion parseRequestVersion(String nextToken) {
    return HttpVersion.fromString(nextToken);
  }

  /**
   * Parse an URI from the given token.
   *
   * @param nextToken The token.
   * @return The URI.
   */
  private URI parseRequestUri(String nextToken) {
    return URI.create(nextToken);
  }

  /**
   * Parse a request method from the given token.
   *
   * @param methodToken The token.
   * @return The HTTP method.
   */
  private HttpMethod parseRequestMethod(String methodToken) {
    return HttpMethod.valueOf(methodToken);
  }
}
