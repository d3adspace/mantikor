package de.d3adspace.mantikor.server.server.file;

import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.server.file.config.MantikorFileConfig;

import java.io.File;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class MantikorFileServerBootstrap {

    public static void main(String[] args) {

        MantikorFileConfig mantikorConfig = new MantikorFileConfig("0.0.0.0", 9090, new File(".").getPath());

        MantikorServer mantikorServer = new MantikorFileServer(mantikorConfig);
        mantikorServer.start();
    }
}
