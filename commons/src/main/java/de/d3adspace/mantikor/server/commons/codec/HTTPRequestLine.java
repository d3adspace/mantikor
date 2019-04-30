package de.d3adspace.mantikor.server.commons.codec;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.net.URI;

/**
 * The request line containing the HTTP method, the URI and the version.
 */
@Data
@Builder
@RequiredArgsConstructor
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
}
