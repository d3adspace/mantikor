package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;

/**
 * A http request.
 */
public class HTTPRequest {

    /**
     * The request line.
     */
    private final HTTPRequestLine requestLine;

    /**
     * The headers of the http request.
     */
    private final HTTPHeaders httpHeaders;

    /**
     * Create a new http request.
     *
     * @param requestLine The request line.
     * @param httpHeaders The headers of the request.
     */
    public HTTPRequest(HTTPRequestLine requestLine, HTTPHeaders httpHeaders) {

        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
    }
}
