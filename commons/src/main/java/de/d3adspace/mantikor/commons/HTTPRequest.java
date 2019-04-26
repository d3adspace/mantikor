package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A http request.
 */
@Data
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
}
