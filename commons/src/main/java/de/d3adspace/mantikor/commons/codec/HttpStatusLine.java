package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

public final class HttpStatusLine {
  private HttpVersion version;
  private HttpStatus status;

  private HttpStatusLine(HttpVersion version, HttpStatus status) {
    this.version = version;
    this.status = status;
  }

  /**
   * Create a status line from version and status.
   *
   * @param version Http protocol version.
   * @param status Http message status.
   * @return Http status line.
   */
  public static HttpStatusLine create(HttpVersion version, HttpStatus status) {
    Preconditions.checkNotNull(version);
    Preconditions.checkNotNull(status);
    return new HttpStatusLine(version, status);
  }

  public static HttpStatusLine ok() {
    return create(HttpVersion.HTTP_VERSION_1_1, HttpStatus.OK);
  }

  public static HttpStatusLine notFound() {
    return create(HttpVersion.HTTP_VERSION_1_1, HttpStatus.NOT_FOUND);
  }

  public static HttpStatusLine forbidden() {
    return create(HttpVersion.HTTP_VERSION_1_1, HttpStatus.FORBIDDEN);
  }

  public HttpVersion getVersion() {
    return version;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
