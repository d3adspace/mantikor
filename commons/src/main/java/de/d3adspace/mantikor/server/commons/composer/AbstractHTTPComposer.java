package de.d3adspace.mantikor.server.commons.composer;

import de.d3adspace.mantikor.server.commons.HTTPMessage;
import de.d3adspace.mantikor.server.commons.MantikorCommons;
import de.d3adspace.mantikor.server.commons.codec.HTTPBody;
import de.d3adspace.mantikor.server.commons.codec.HTTPHeaders;

import java.util.Map;

public abstract class AbstractHTTPComposer<InputType extends HTTPMessage, OutputType> {

    /**
     * Compose the given output out of the given input.
     *
     * @param input The input.
     * @return The output.
     */
    public abstract OutputType compose(InputType input);

    /**
     * Encode an http message into a string buffer.
     *
     * @param message The http message.
     * @return The string buffer.
     */
    StringBuffer encodeHeadersAndBody(HTTPMessage message) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode headers
        HTTPHeaders headers = message.getHeaders();
        StringBuffer headersBuffer = encodeHeaders(headers);
        stringBuffer.append(headersBuffer);

        // Encode body
        HTTPBody body = message.getBody();
        StringBuffer bodyBuffer = encodeBody(body);
        stringBuffer.append(bodyBuffer);

        return stringBuffer;
    }

    /**
     * Encode the given http headers into a string buffer.
     *
     * @param httpHeaders The headers.
     * @return The string buffer.
     */
    private StringBuffer encodeHeaders(HTTPHeaders httpHeaders) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode headers
        Map<String, String> headers = httpHeaders.getHeaders();

        headers.forEach((key, value) -> {
            stringBuffer.append(key);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            stringBuffer.append(MantikorCommons.LF);
        });

        return stringBuffer;
    }

    /**
     * Encode an http body into a string buffer.
     *
     * @param httpBody The http body.
     * @return The string buffer.
     */
    private StringBuffer encodeBody(HTTPBody httpBody) {

        StringBuffer stringBuffer = new StringBuffer();

        // Encode body
        stringBuffer.append(MantikorCommons.CRLF);
        stringBuffer.append(httpBody.getContent());

        return stringBuffer;
    }
}
