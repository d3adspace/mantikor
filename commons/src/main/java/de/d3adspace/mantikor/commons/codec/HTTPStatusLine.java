package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

public class HTTPStatusLine {

  /**
   * The version.
   */
  private HTTPVersion version;

  /**
   * The status.
   */
  private HTTPStatus status;

  private HTTPStatusLine(HTTPVersion version, HTTPStatus status) {
    this.version = version;
    this.status = status;
  }

  public static HTTPStatusLine create(HTTPVersion version, HTTPStatus status) {
    Preconditions.checkNotNull(version);
    Preconditions.checkNotNull(status);

    return new HTTPStatusLine(version, status);
  }

  public static HTTPStatusLine ok() {
    return create(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.OK);
  }

  public static HTTPStatusLine notFound() {
    return create(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.NOT_FOUND);
  }

  public static HTTPStatusLine forbidden() {
    return create(HTTPVersion.HTTP_VERSION_1_1, HTTPStatus.FORBIDDEN);
  }

  public HTTPVersion getVersion() {
    return version;
  }

  public HTTPStatus getStatus() {
    return status;
  }
}
