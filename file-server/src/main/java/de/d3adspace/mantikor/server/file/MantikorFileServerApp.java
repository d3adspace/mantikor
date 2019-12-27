package de.d3adspace.mantikor.server.file;

import com.google.common.net.HostAndPort;
import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.file.config.MantikorFileConfig;

import java.io.File;

public class MantikorFileServerApp {
  /**
   * Entry method.
   * @param args Command Line Arguments.
   */
  public static void main(String[] args) {
    var hostAndPort = HostAndPort.fromParts("0.0.0.0", 8080);
    var mantikorConfig = new MantikorFileConfig(hostAndPort,
      new File("/var/www/html").getPath());
    var mantikorServer = new MantikorFileServer(mantikorConfig);
    mantikorServer.start();
  }
}
