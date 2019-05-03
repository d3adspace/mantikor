package de.d3adspace.mantikor.server.commons.codec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class HTTPStatusLine {

    /**
     * The version.
     */
    private HTTPVersion version;

    /**
     * The status.
     */
    private HTTPStatus status;
}
