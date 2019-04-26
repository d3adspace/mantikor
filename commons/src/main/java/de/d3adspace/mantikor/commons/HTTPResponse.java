package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HTTPResponse extends HTTPMessage {

    /**
     * The status line with version and message.
     */
    private final HTTPStatusLine statusLine;

    public HTTPResponse(HTTPStatusLine statusLine, HTTPHeaders headers, HTTPBody body) {
        super(headers, body);
        this.statusLine = statusLine;
    }
}
