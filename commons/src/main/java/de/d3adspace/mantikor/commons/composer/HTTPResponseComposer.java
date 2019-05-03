package de.d3adspace.mantikor.commons.composer;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.MantikorCommons;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;

public class HTTPResponseComposer extends AbstractHTTPComposer<HTTPResponse, String> {

    /**
     * Compose a HTTP message out of the given http response.
     *
     * @param response The response.
     * @return The http method.
     */
    @Override
    public String compose(HTTPResponse response) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode status line
        HTTPStatusLine statusLine = response.getStatusLine();

        HTTPVersion version = statusLine.getVersion();
        stringBuffer.append(version.getVersion());
        stringBuffer.append(" ");

        HTTPStatus status = statusLine.getStatus();
        stringBuffer.append(status.getStatusCode());
        stringBuffer.append(" ");
        stringBuffer.append(status.getStatusMessage());
        stringBuffer.append(MantikorCommons.CRLF);

        // Encode headers and body
        StringBuffer headerBodyBuffer = encodeHeadersAndBody(response);
        stringBuffer.append(headerBodyBuffer);

        return stringBuffer.toString();
    }

}
