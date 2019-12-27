package de.d3adspace.mantikor.commons.codec;

import java.util.Arrays;

public enum HttpVersion {
  HTTP_VERSION_1_0("HTTP/1.0"),
  HTTP_VERSION_1_1("HTTP/1.1"),
  HTTP_VERSION_2_0("HTTP/2.0");

  private final String version;

  HttpVersion(String version) {
    this.version = version;
  }

  /**
   * Parse an http version from a string.
   *
   * @param version The version string.
   * @return http version.
   */
  public static HttpVersion fromString(final String version) {
    return Arrays.stream(values())
      .filter(httpVersion -> httpVersion.getVersion().equalsIgnoreCase(version))
      .findFirst().orElse(null);
  }

  public String getVersion() {
    return version;
  }
}
