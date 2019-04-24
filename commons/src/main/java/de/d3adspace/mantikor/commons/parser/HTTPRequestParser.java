package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.net.URI;
import java.util.StringTokenizer;

/**
 * The request parser that will create the HTTP Request from its raw string.
 */
public class HTTPRequestParser {

    /**
     * Create a request from its raw form.
     *
     * @param rawHTTPRequest The raw data.
     * @return The http request.
     */
    public HTTPRequest parseRequest(String rawHTTPRequest) {

        // Create a tokenizer of the string.
        StringTokenizer tokenizer = new StringTokenizer(rawHTTPRequest);

        // Parse the http token
        String nextToken = tokenizer.nextToken();
        HTTPMethod method = parseRequestMethod(nextToken);

        // Parse the URI
        nextToken = tokenizer.nextToken();
        URI uri = parseRequestURI(nextToken);

        // Parse the HTTP version
        nextToken = tokenizer.nextToken();
        HTTPVersion version = parseRequestVersion(nextToken);

        // Construct HTTPRequestLine
        HTTPRequestLine requestLine = new HTTPRequestLine(method, uri, version);

        // Construct HTTP request
        return new HTTPRequest(requestLine, null);
    }

    /**
     * Parse the request version from the given token.
     *
     * @param nextToken The token.
     * @return The requests HTTP version.
     */
    private HTTPVersion parseRequestVersion(String nextToken) {

        return HTTPVersion.fromString(nextToken);
    }

    /**
     * Parse an URI from the given token.
     *
     * @param nextToken The token.
     * @return The URI.
     */
    private URI parseRequestURI(String nextToken) {

        return URI.create(nextToken);
    }

    /**
     * Parse a request method from the given token.
     *
     * @param methodToken The token.
     * @return The HTTP method.
     */
    private HTTPMethod parseRequestMethod(String methodToken) {

        return HTTPMethod.valueOf(methodToken);
    }
}
