package de.d3adspace.mantikor.server.file;

import de.d3adspace.mantikor.MantikorServer;
import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.commons.codec.HTTPVersion;
import de.d3adspace.mantikor.config.MantikorConfig;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class MantikorFileServer extends MantikorServer {

    /**
     * Create a new server based on a config.
     *
     * @param config The config.
     */
    MantikorFileServer(MantikorConfig config) {
        super(config);
    }

    @Override
    public HTTPResponse handleRequest(HTTPRequest request) {

        URI uri = request.getRequestLine().getUri();
        Path path = Paths.get(uri.toString());

        if (!Files.exists(path)) {
            HTTPStatusLine statusLine = new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.NOT_FOUND);
            HTTPHeaders httpHeaders = new HTTPHeaders();

            return new HTTPResponse(statusLine, httpHeaders, null);
        }

        byte[] bytes = new byte[0];

        try {
             bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HTTPStatusLine statusLine = new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.OK);
        HTTPHeaders headers = new HTTPHeaders();

        headers.addHeader(HTTPHeaders.KEY_CONTENT_LENGTH, String.valueOf(bytes.length));

        HTTPBody body = new HTTPBody(new String(bytes).toCharArray());

        return new HTTPResponse(statusLine, headers, body);
    }
}
