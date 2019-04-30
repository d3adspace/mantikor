package de.d3adspace.mantikor.server.commons.codec;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class HTTPStatusLine {

    /**
     * The version.
     */
    private final HTTPVersion version;

    /**
     * The status.
     */
    private final HTTPStatus status;
}
