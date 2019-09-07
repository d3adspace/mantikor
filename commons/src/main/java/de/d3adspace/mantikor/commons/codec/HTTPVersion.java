package de.d3adspace.mantikor.commons.codec;

import java.util.Arrays;

public enum HTTPVersion {

  HTTP_VERSION_1_0("HTTP/1.0"),
  HTTP_VERSION_1_1("HTTP/1.1"),
  HTTP_VERSION_2_0("HTTP/2.0");

  private final String version;

  HTTPVersion(String version) {
    this.version = version;
  }

  public static HTTPVersion fromString(final String version) {

    return Arrays.stream(values())
      .filter(httpVersion -> httpVersion.getVersion().equalsIgnoreCase(version))
      .findFirst().orElse(null);
  }

  public String getVersion() {
    return version;
  }
}
