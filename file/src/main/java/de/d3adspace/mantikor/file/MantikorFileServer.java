/*
 * Copyright (c) 2017 D3adspace
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.d3adspace.mantikor.file;

import de.d3adspace.mantikor.MantikorServer;
import de.d3adspace.mantikor.config.MantikorConfig;
import de.d3adspace.mantikor.http.HTTPBody;
import de.d3adspace.mantikor.http.HTTPRequest;
import de.d3adspace.mantikor.http.HTTPResponse;
import de.d3adspace.mantikor.http.HTTPStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MantikorFileServer extends MantikorServer {

    /**
     * Create a new server based on a config.
     *
     * @param config The config.
     */
    public MantikorFileServer(MantikorConfig config) {
        super(config);
    }

    @Override
    public HTTPResponse handleRequest(HTTPRequest request) {

        String location = request.getLocation();
        location = location.equalsIgnoreCase("") ? "/index.html" : location;
        location = !location.contains(".") && !location.endsWith("/") ? location + "/index.html" : location;

        System.out.println("Loc: " + location);

        InputStream stream = this.getClass().getResourceAsStream(location);

        if (stream == null) {
            return HTTPResponse.newBuilder()
                    .setStatus(HTTPStatus.NOT_FOUND)
                    .createHTTPResponse();
        }

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            return HTTPResponse.newBuilder()
                    .setStatus(HTTPStatus.OK)
                    .setBody(new HTTPBody(buffer.toByteArray()))
                    .createHTTPResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
