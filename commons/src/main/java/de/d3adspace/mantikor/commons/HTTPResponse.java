package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class HTTPResponse {

    /**
     * The status line with version and message.
     */
    private final HTTPStatusLine statusLine;

    /**
     * The headers.
     */
    private final HTTPHeaders headers;

    /**
     * The body payload.
     */
    private final HTTPBody body;
}
