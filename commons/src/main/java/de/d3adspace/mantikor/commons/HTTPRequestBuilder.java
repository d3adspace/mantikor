package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.net.URI;

public class HTTPRequestBuilder {

    private HTTPRequestLine requestLine = new HTTPRequestLine(HTTPMethod.GET, URI.create("/"), HTTPVersion.HTTP_VERSION_1_1);
    private HTTPHeaders headers = new HTTPHeaders();
    private HTTPBody body = new HTTPBody();

    public HTTPRequestBuilder setRequestLine(HTTPRequestLine requestLine) {
        this.requestLine = requestLine;
        return this;
    }

    public HTTPRequestBuilder setHeaders(HTTPHeaders headers) {
        this.headers = headers;
        return this;
    }

    public HTTPRequestBuilder setBody(HTTPBody body) {
        this.body = body;
        return this;
    }

    public HTTPRequest createHTTPRequest() {
        return new HTTPRequest(requestLine, headers, body);
    }
}
