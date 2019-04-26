package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPMethod;
import de.d3adspace.mantikor.commons.codec.HTTPRequestLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.net.URI;
import java.util.Map;

public class HTTPRequestComposer {

    /**
     * Compose a HTTP message out of the given http request.
     *
     * @param request The request.
     * @return The http message.
     */
    public String composeRequest(HTTPRequest request) {

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

        // Encode headers
        HTTPHeaders httpHeaders = request.getHeaders();
        Map<String, String> headers = httpHeaders.getHeaders();

        headers.forEach((key, value) -> {
            stringBuffer.append(key);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            stringBuffer.append(MantikorCommons.CRLF);
        });

        // Encode body
        HTTPBody body = request.getBody();

        stringBuffer.append(MantikorCommons.CRLF);
        stringBuffer.append(body.getContent());

        return stringBuffer.toString();
    }
}
