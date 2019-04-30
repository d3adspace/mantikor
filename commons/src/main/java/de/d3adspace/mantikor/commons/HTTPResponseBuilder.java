package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

public class HTTPResponseBuilder {

    private HTTPStatusLine statusLine = new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.OK);
    private HTTPHeaders headers = new HTTPHeaders();
    private HTTPBody body = new HTTPBody();

    public HTTPResponseBuilder setStatusLine(HTTPStatusLine statusLine) {
        this.statusLine = statusLine;
        return this;
    }

    public HTTPResponseBuilder setHeaders(HTTPHeaders headers) {
        this.headers = headers;
        return this;
    }

    public HTTPResponseBuilder setBody(HTTPBody body) {
        this.body = body;
        return this;
    }

    public HTTPResponse createHTTPResponse() {
        return new HTTPResponse(statusLine, headers, body);
    }
}
