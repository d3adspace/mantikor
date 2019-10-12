package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HTTPRequestParserTest {

  private static final HTTPMethod REQUEST_STANDARD_GET_METHOD = HTTPMethod.GET;
  private static final URI REQUEST_STANDARD_GET_URI = URI.create("/index");
  private static final HTTPVersion REQUEST_STANDARD_GET_VERSION = HTTPVersion.HTTP_VERSION_1_1;
  private static final int REQUEST_STANDARD_GET_HEADER_COUNT = 0;

  private static final String REQUEST_STANDARD_GET = "GET /index HTTP/1.1\r\n";

  private static final HTTPMethod REQUEST_EXTENDED_POST_METHOD = HTTPMethod.POST;
  private static final URI REQUEST_EXTENDED_POST_URI = URI
    .create("/index/data");
  private static final HTTPVersion REQUEST_EXTENDED_POST_VERSION = HTTPVersion.HTTP_VERSION_1_1;
  private static final int REQUEST_EXTENDED_POST_HEADER_COUNT = 2;
  private static final char[] REQUEST_EXTENDED_POST_BODY = "<h1>test</h1>"
    .toCharArray();

  private static final String REQUEST_EXTENDED_POST =
    "POST /index/data HTTP/1.1\r\n" +
      "Content-Length: 13\n" +
      "Content-Type: text/html\n" +
      "\r\n" +
      "<h1>test</h1>";

  private HTTPRequestMessageFactory requestParser;

  @BeforeEach
  void setUp() {

    requestParser = new HTTPRequestMessageFactory();
  }

  @Test
  void testParseRequest() {

    HTTPRequest httpRequest = requestParser.parse(REQUEST_STANDARD_GET);

    assertEquals(REQUEST_STANDARD_GET_METHOD,
      httpRequest.getMethod());
    assertEquals(REQUEST_STANDARD_GET_URI,
      httpRequest.getUri());
    assertEquals(REQUEST_STANDARD_GET_VERSION,
      httpRequest.getVersion());
    assertEquals(REQUEST_STANDARD_GET_HEADER_COUNT,
      httpRequest.getHeaderCount());
  }

  @Test
  void testParseRequestExtendedPost() {

    HTTPRequest httpRequest = requestParser.parse(REQUEST_EXTENDED_POST);

    assertEquals(REQUEST_EXTENDED_POST_METHOD,
      httpRequest.getMethod());
    assertEquals(REQUEST_EXTENDED_POST_URI,
      httpRequest.getUri());
    assertEquals(REQUEST_EXTENDED_POST_VERSION,
      httpRequest.getVersion());
    assertEquals(REQUEST_EXTENDED_POST_HEADER_COUNT,
      httpRequest.getHeaders().getHeaderCount());
    assertArrayEquals(REQUEST_EXTENDED_POST_BODY,
      httpRequest.getBody().getContent());
  }
}
