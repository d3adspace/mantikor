package de.d3adspace.mantikor.server.file;

import de.d3adspace.mantikor.MantikorServer;
import de.d3adspace.mantikor.config.MantikorConfig;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class MantikorFileServerBootstrap {

    public static void main(String[] args) {

        MantikorConfig mantikorConfig = MantikorConfig.builder()
                .serverHost("0.0.0.0")
                .serverPort(8080)
                .build();

        MantikorServer mantikorServer = new MantikorFileServer(mantikorConfig);
        mantikorServer.start();
    }
}
