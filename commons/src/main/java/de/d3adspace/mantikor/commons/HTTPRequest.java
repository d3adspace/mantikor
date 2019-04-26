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
}
