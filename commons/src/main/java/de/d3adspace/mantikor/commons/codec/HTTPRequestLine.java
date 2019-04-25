package de.d3adspace.mantikor.commons.codec;

import java.net.URI;

/**
 * The request line containing the HTTP method, the URI and the version.
 */
public class HTTPRequestLine {

    /**
     * The HTTP method.
     */
    private final HTTPMethod method;

    /**
     * The HTTP URI.
     */
    private final URI uri;

    /**
     * The HTTP version.
     */
    private final HTTPVersion version;

    /**
     * Create a new http request line.
     *
     * @param method The request method.
     * @param uri The request uri.
     * @param version The request version.
     */
    public HTTPRequestLine(HTTPMethod method, URI uri, HTTPVersion version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public HTTPVersion getVersion() {
        return version;
    }

    public URI getURI() {
        return uri;
    }

    @Override
    public String toString() {
        return "HTTPRequestLine{" +
                "method=" + method +
                ", uri=" + uri +
                ", version=" + version +
                '}';
    }
}
