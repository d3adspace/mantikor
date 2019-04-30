package de.d3adspace.mantikor.server.commons;

import de.d3adspace.mantikor.server.commons.codec.HTTPBody;
import de.d3adspace.mantikor.server.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.server.commons.codec.HTTPRequestLine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A http request.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HTTPRequest extends HTTPMessage {

    /**
     * The request line.
     */
    private final HTTPRequestLine requestLine;

    public HTTPRequest(HTTPRequestLine requestLine, HTTPHeaders headers, HTTPBody body) {
        super(headers, body);
        this.requestLine = requestLine;
    }

    public HTTPRequest(HTTPRequestLine requestLine, HTTPHeaders headers) {
        this(requestLine, headers, new HTTPBody());
    }

    public HTTPRequest(HTTPRequestLine requestLine) {
        this(requestLine, new HTTPHeaders());
    }
}
