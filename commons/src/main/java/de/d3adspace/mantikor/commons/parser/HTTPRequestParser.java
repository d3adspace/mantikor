package de.d3adspace.mantikor.commons.parser;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
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
     *
     * @return The http request.
     */
    public HTTPRequest parseRequest(String rawHTTPRequest) {

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
        HTTPHeaders httpHeaders = null;

        try {
            httpHeaders = new HTTPHeaders();
            String currentLine = reader.readLine();

            while (currentLine != null && !currentLine.isEmpty()) {

                Pair<String, String> header = parseHeader(currentLine);
                httpHeaders.addHeader(header.getKey(), header.getValue());

                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse body if present
        HTTPBody httpBody = null;
        if (httpHeaders.hasHeader(HTTPHeaders.KEY_CONTENT_LENGTH)) {

            int contentLength = Integer.parseInt(httpHeaders.getHeader(HTTPHeaders.KEY_CONTENT_LENGTH));
            char[] bodyContent = new char[contentLength];

            try {
                int read = reader.read(bodyContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

            httpBody = new HTTPBody(bodyContent);
        }

        // Construct HTTP request
        return new HTTPRequest(requestLine, httpHeaders, httpBody);
    }

    /**
     * Parse a header from the given line.
     *
     * @param line The line.
     *
     * @return The header pair
     */
    private Pair<String, String> parseHeader(String line) {

        StringTokenizer stringTokenizer = new StringTokenizer(line, ":");

        return new Pair<>(stringTokenizer.nextToken().trim(), stringTokenizer.nextToken().trim());
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
