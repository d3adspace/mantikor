package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
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
     * The payload of the request.
     */
    private HTTPBody httpBody;

    /**
     * Create a new http request.
     *
     * @param requestLine The request line.
     * @param httpHeaders The headers of the request.
     * @param httpBody The body.
     */
    public HTTPRequest(HTTPRequestLine requestLine, HTTPHeaders httpHeaders, HTTPBody httpBody) {

        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
        this.httpBody = httpBody;
    }

    @Override
    public String toString() {
        return "HTTPRequest{" +
                "requestLine=" + requestLine +
                ", httpHeaders=" + httpHeaders +
                ", httpBody=" + httpBody +
                '}';
    }
}
