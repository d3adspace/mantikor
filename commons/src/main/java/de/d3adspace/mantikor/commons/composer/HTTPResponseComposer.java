package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

import java.util.Map;
import java.util.function.BiConsumer;

public class HTTPResponseComposer {

    /**
     * Compose a HTTP message out of the given http response.
     *
     * @param response The response.
     * @return The http method.
     */
    public String composeResponse(HTTPResponse response) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode status line
        HTTPStatusLine statusLine = response.getStatusLine();

        HTTPVersion version = statusLine.getVersion();
        stringBuffer.append(version.getVersion());

        HTTPStatus status = statusLine.getStatus();
        stringBuffer.append(status.getStatusCode());
        stringBuffer.append(" ");
        stringBuffer.append(status.getStatusMessage());
        stringBuffer.append(MantikorCommons.CRLF);

        // Encode headers
        HTTPHeaders httpHeaders = response.getHeaders();
        Map<String, String> headers = httpHeaders.getHeaders();

        headers.forEach((key, value) -> {
            stringBuffer.append(key);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            stringBuffer.append(MantikorCommons.CRLF);
        });

        // Encode body
        HTTPBody body = response.getBody();

        stringBuffer.append(MantikorCommons.CRLF);
        stringBuffer.append(body.getContent());

        return stringBuffer.toString();
    }

}
