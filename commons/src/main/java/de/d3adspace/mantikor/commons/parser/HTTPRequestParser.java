package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPRequestBuilder;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.StringTokenizer;

/**
 * The request parser that will create the HTTP Request from its raw string.
 */
public class HTTPRequestParser extends AbstractHTTPParser<String, HTTPRequest> {

    /**
     * Create a request from its raw form.
     *
     * @param rawHTTPRequest The raw data.
     *
     * @return The http request.
     */
    @Override
    public HTTPRequest parse(String rawHTTPRequest) {

        BufferedReader reader = new BufferedReader(new StringReader(rawHTTPRequest));

        // Parse request line
        HTTPRequestLine requestLine = null;

        try {
            String currentLine = reader.readLine();
            requestLine = parseRequestLine(currentLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse headers
        HTTPHeaders httpHeaders = parseHeaders(reader);

        // Parse body if present
        HTTPBody httpBody = parseBody(httpHeaders, reader);

        // Construct HTTP request
        return new HTTPRequestBuilder().setRequestLine(requestLine).setHeaders(httpHeaders).setBody(httpBody).createHTTPRequest();
    }

    /**
     * Parse the HTTP request line from the given text line.
     *
     * @param line The line.
     *
     * @return The http request line.
     */
    private HTTPRequestLine parseRequestLine(String line) {

        StringTokenizer tokenizer = new StringTokenizer(line);

        // Parse method
        String nextToken = tokenizer.nextToken();
        HTTPMethod method = parseRequestMethod(nextToken);

        // Parse URI
        nextToken = tokenizer.nextToken();
        URI uri = parseRequestURI(nextToken);

        // Parse version
        nextToken = tokenizer.nextToken();
        HTTPVersion version = parseRequestVersion(nextToken);

        return new HTTPRequestLine(method, uri, version);
    }

    /**
     * Parse the request version from the given token.
     *
     * @param nextToken The token.
     *
     * @return The requests HTTP version.
     */
    private HTTPVersion parseRequestVersion(String nextToken) {

        return HTTPVersion.fromString(nextToken);
    }

    /**
     * Parse an URI from the given token.
     *
     * @param nextToken The token.
     *
     * @return The URI.
     */
    private URI parseRequestURI(String nextToken) {

        return URI.create(nextToken);
    }

    /**
     * Parse a request method from the given token.
     *
     * @param methodToken The token.
     *
     * @return The HTTP method.
     */
    private HTTPMethod parseRequestMethod(String methodToken) {

        return HTTPMethod.valueOf(methodToken);
    }
}
