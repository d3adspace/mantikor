package de.d3adspace.mantikor.server.file.config;

import com.google.common.net.HostAndPort;
import de.d3adspace.mantikor.server.config.MantikorConfig;
import lombok.Getter;

@Getter
public class MantikorFileConfig extends MantikorConfig {
  private final String basePath;

  public MantikorFileConfig(
    HostAndPort hostAndPort,
    String basePath
  ) {
    super(hostAndPort);
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }
}
