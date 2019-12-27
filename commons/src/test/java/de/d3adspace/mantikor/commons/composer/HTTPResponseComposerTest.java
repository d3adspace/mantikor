package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.codec.HttpStatus;
import de.d3adspace.mantikor.commons.codec.HttpVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HTTPResponseComposerTest {

  private static final HttpResponse TEST_RESPONSE = HttpResponse.newBuilder()
    .withStatus(HttpStatus.NOT_FOUND)
    .withVersion(HttpVersion.HTTP_VERSION_1_1)
    .withHeaders(new LinkedHashMap<>() {{
      put("Date", "Sun, 18 Oct 2012 10:36:20 GMT");
      put("Server", "Apache/2.2.14 (Win32)");
      put("Content-Length", "230");
      put("Connection", "Closed");
      put("Content-Type", "text/html; charset=iso-8859-1");
    }}).withBodyContent(("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
      "<html>\n" +
      "<head>\n" +
      "   <title>404 Not Found</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "   <h1>Not Found</h1>\n" +
      "   <p>The requested URL /t.html was not found on this server.</p>\n" +
      "</body>\n" +
      "</html>").toCharArray()
    ).build();

  private static final String RESPONSE_STANDARD_OK =
    "HTTP/1.1 404 Not Found\r\n" +
      "Date: Sun, 18 Oct 2012 10:36:20 GMT\n" +
      "Server: Apache/2.2.14 (Win32)\n" +
      "Content-Length: 230\n" +
      "Connection: Closed\n" +
      "Content-Type: text/html; charset=iso-8859-1\n" +
      "\r\n" +
      "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
      "<html>\n" +
      "<head>\n" +
      "   <title>404 Not Found</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "   <h1>Not Found</h1>\n" +
      "   <p>The requested URL /t.html was not found on this server.</p>\n" +
      "</body>\n" +
      "</html>";

  private HttpResponseComposer responseComposer;

  @BeforeEach
  void setUp() {

    responseComposer = new HttpResponseComposer();
  }

  @Test
  void testCompose() {

    String compose = responseComposer.compose(TEST_RESPONSE);
    assertArrayEquals(RESPONSE_STANDARD_OK.toCharArray(),
      compose.toCharArray());
  }
}
