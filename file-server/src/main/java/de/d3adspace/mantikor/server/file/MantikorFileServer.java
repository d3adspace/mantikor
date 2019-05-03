package de.d3adspace.mantikor.server.file;

import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.commons.HTTPRequest;
import de.d3adspace.mantikor.server.commons.HTTPResponse;
import de.d3adspace.mantikor.server.commons.HTTPResponseBuilder;
import de.d3adspace.mantikor.server.commons.codec.HTTPBody;
import de.d3adspace.mantikor.server.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.server.commons.codec.HTTPStatus;
import de.d3adspace.mantikor.server.commons.codec.HTTPStatusLine;
import de.d3adspace.mantikor.server.commons.codec.HTTPVersion;
import de.d3adspace.mantikor.server.file.config.MantikorFileConfig;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A really simple file server that will serve static files.
 *
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class MantikorFileServer extends MantikorServer {

    /**
     * The mantikor file server config containing some environment specifications.
     */
    private MantikorFileConfig config;

    /**
     * Create a new server based on a config.
     *
     * @param config The config.
     */
    MantikorFileServer(MantikorFileConfig config) {
        super(config);
        this.config = config;
    }

    @Override
    public HTTPResponse handleRequest(HTTPRequest request) {

        // Convert request uri to actual file system path
        URI uri = request.getRequestLine().getUri();
        Path path = Paths.get(config.getBasePath(), uri.toString());

        // Check if file exists and return 404 if not
        if (!Files.exists(path)) {
            HTTPStatusLine statusLine = new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.NOT_FOUND);

            return new HTTPResponseBuilder()
                    .setStatusLine(statusLine)
                    .createHTTPResponse();
        }

        // Read file content
        byte[] bytes = new byte[0];

        try {
             bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assemble response header
        HTTPStatusLine statusLine = new HTTPStatusLine(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.OK);
        HTTPHeaders headers = new HTTPHeaders();

        // Assemble response body
        HTTPBody body = new HTTPBody(new String(bytes).toCharArray());

        // Build the response
        return new HTTPResponseBuilder()
                .setStatusLine(statusLine)
                .setHeaders(headers)
                .setBody(body)
                .createHTTPResponse();
    }
}
