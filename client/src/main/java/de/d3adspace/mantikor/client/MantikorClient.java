package de.d3adspace.mantikor.client;

import de.d3adspace.mantikor.server.commons.HTTPRequest;
import de.d3adspace.mantikor.server.commons.HTTPResponse;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public interface MantikorClient {

    /**
     * Execute an HTTP call with the given request.
     *
     * @param request The request.
     * @return The response.
     */
    HTTPResponse execute(HTTPRequest request);
}
