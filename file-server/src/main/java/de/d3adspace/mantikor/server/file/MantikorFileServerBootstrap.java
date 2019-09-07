package de.d3adspace.mantikor.server.file;

import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.file.config.MantikorFileConfig;
import java.io.File;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class MantikorFileServerBootstrap {

  public static void main(String[] args) {

    MantikorFileConfig mantikorConfig = new MantikorFileConfig("0.0.0.0", 8080,
      new File("/var/www/html").getPath());

    MantikorServer mantikorServer = new MantikorFileServer(mantikorConfig);
    mantikorServer.start();
  }
}
