package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class HTTPRequestParserTest {

    private static final HTTPMethod REQUEST_STANDARD_GET_METHOD = HTTPMethod.GET;
    private static final URI REQUEST_STANDARD_GET_URI = URI.create("/index");
    private static final HTTPVersion REQUEST_STANDARD_GET_VERSION = HTTPVersion.HTTP_VERSION_1_1;
    private static final int REQUEST_STANDARD_GET_HEADER_COUNT = 0;

    private static final String REQUEST_STANDARD_GET = "GET /index HTTP/1.1\r\n";

    private HTTPRequestParser requestParser;

    @BeforeEach
    void setUp() {

        requestParser = new HTTPRequestParser();
    }

    @Test
    void testParseRequest() {

        HTTPRequest httpRequest = requestParser.parseRequest(REQUEST_STANDARD_GET);

        assertEquals(REQUEST_STANDARD_GET_METHOD, httpRequest.getRequestLine().getMethod());
        assertEquals(REQUEST_STANDARD_GET_URI, httpRequest.getRequestLine().getURI());
        assertEquals(REQUEST_STANDARD_GET_VERSION, httpRequest.getRequestLine().getVersion());

        assertEquals(REQUEST_STANDARD_GET_HEADER_COUNT, httpRequest.getHttpHeaders().getHeaderCount());
    }
}
