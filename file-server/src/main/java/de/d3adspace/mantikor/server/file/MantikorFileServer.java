package de.d3adspace.mantikor.server.file;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.file.config.MantikorFileConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MantikorFileServer extends MantikorServer {
  /**
   * The mantikor file server config containing some environment
   * specifications.
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
  public HttpResponse handleRequest(HttpRequest request) {
    var path = Paths.get(config.getBasePath(), request.getUri().toString());
    if (!Files.exists(path)) {
      return HttpResponse.notFound();
    }
    if (Files.isDirectory(path)) {
      return HttpResponse.forbidden();
    }
    return readPath(path);
  }

  private HttpResponse readPath(Path path) {
    var bytes = tryReadBytes(path);
    var bodyContent = new String(bytes).toCharArray();
    return HttpResponse.ok(bodyContent);
  }

  private byte[] tryReadBytes(Path path) {
    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
      return new byte[0];
    }
  }
}
