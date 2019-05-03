package de.d3adspace.mantikor.server.commons.codec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.net.URI;

/**
 * The request line containing the HTTP method, the URI and the version.
 */
@Data
@Builder
@AllArgsConstructor
public class HTTPRequestLine {

    /**
     * The HTTP method.
     */
    private HTTPMethod method;

    /**
     * The HTTP URI.
     */
    private URI uri;

    /**
     * The HTTP version.
     */
    private HTTPVersion version;
}
