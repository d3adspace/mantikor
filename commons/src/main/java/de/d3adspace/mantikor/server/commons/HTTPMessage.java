package de.d3adspace.mantikor.server.commons;

import de.d3adspace.mantikor.server.commons.codec.HTTPBody;
import de.d3adspace.mantikor.server.commons.codec.HTTPHeaders;
import lombok.Data;

@Data
public class HTTPMessage {

    private final HTTPHeaders headers;

    private final HTTPBody body;
}
