package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A http request.
 */
@Data
@Builder
@RequiredArgsConstructor
public class HTTPRequest {

    /**
     * The request line.
     */
    private final HTTPRequestLine requestLine;

    /**
     * The headers of the http request.
     */
    private final HTTPHeaders headers;

    /**
     * The payload of the request.
     */
    private final HTTPBody body;
}
