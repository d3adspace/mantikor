package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.HTTPResponseBuilder;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class HTTPResponseComposerTest {

    private static final HTTPResponse TEST_RESPONSE = new HTTPResponseBuilder().setStatusLine(new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.NOT_FOUND)).setHeaders(new HTTPHeaders(new LinkedHashMap<>() {{
        put("Date", "Sun, 18 Oct 2012 10:36:20 GMT");
        put("Server", "Apache/2.2.14 (Win32)");
        put("Content-Length", "230");
        put("Connection", "Closed");
        put("Content-Type", "text/html; charset=iso-8859-1");
    }})).setBody(new HTTPBody(("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
            "<html>\n" +
            "<head>\n" +
            "   <title>404 Not Found</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "   <h1>Not Found</h1>\n" +
            "   <p>The requested URL /t.html was not found on this server.</p>\n" +
            "</body>\n" +
            "</html>").toCharArray()
    )).createHTTPResponse();

    private static final String RESPONSE_STANDARD_OK = "HTTP/1.1 404 Not Found\r\n" +
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

    private HTTPResponseComposer responseComposer;

    @BeforeEach
    void setUp() {

        responseComposer = new HTTPResponseComposer();
    }

    @Test
    void testCompose() {

        String compose = responseComposer.compose(TEST_RESPONSE);
        assertArrayEquals(RESPONSE_STANDARD_OK.toCharArray(), compose.toCharArray());
    }
}
