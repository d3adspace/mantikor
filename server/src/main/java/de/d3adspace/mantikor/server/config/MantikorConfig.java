package de.d3adspace.mantikor.server.config;

import com.google.common.base.Preconditions;
import com.google.common.net.HostAndPort;

public class MantikorConfig {
  private final HostAndPort hostAndPort;

  protected MantikorConfig(HostAndPort hostAndPort) {
    this.hostAndPort = hostAndPort;
  }

  /**
   * Create a config with all relevant values.
   *
   * @param hostAndPort Host and port of the server.
   * @return Config instance.
   */
  public static MantikorConfig withHostAndPort(HostAndPort hostAndPort) {
    Preconditions.checkNotNull(hostAndPort);
    return new MantikorConfig(hostAndPort);
  }

  public String getServerHost() {
    return hostAndPort.getHost();
  }

  public int getServerPort() {
    return hostAndPort.getPort();
  }
}
