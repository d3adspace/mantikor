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

import de.d3adspace.mantikor.MantikorServer;
import de.d3adspace.mantikor.config.MantikorConfig;
import de.d3adspace.mantikor.http.HTTPHeaders;
import de.d3adspace.mantikor.http.HTTPRequest;
import de.d3adspace.mantikor.http.HTTPResponse;
import de.d3adspace.mantikor.http.HTTPStatus;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MantikorServerTest {
	
	public static void main(String[] args) {
		MantikorConfig mantikorConfig = MantikorConfig.newBuilder()
			.setServerHost("localhost")
			.setServerPort(8080)
			.createMantikorConfig();
		
		MantikorServer mantikorServer = new MantikorServer(mantikorConfig) {
			@Override
			public HTTPResponse handleRequest(HTTPRequest request) {
				return HTTPResponse.newBuilder()
					.setStatus(HTTPStatus.NOT_FOUND)
					.setHeaders(new HTTPHeaders())
					.createHTTPResponse();
			}
		};
		
		mantikorServer.start();
	}
}
