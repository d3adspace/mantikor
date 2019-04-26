package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.net.URI;

public class HTTPRequestComposer extends AbstractHTTPComposer<HTTPRequest, String> {

    /**
     * Compose a HTTP message out of the given http request.
     *
     * @param request The request.
     * @return The http message.
     */
    @Override
    public String compose(HTTPRequest request) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode request line
        HTTPRequestLine requestLine = request.getRequestLine();

        HTTPMethod method = requestLine.getMethod();
        stringBuffer.append(method.name());
        stringBuffer.append(" ");

        URI uri = requestLine.getUri();
        stringBuffer.append(uri.toString());
        stringBuffer.append(" ");

        HTTPVersion version = requestLine.getVersion();
        stringBuffer.append(version.getVersion());
        stringBuffer.append(MantikorCommons.CRLF);

        // Encode headers and body
        StringBuffer headerBuffer = encodeHeadersAndBody(request);
        stringBuffer.append(headerBuffer);

        return stringBuffer.toString();
    }
}
