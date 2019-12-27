package de.d3adspace.mantikor.commons.codec;

import de.d3adspace.mantikor.commons.MantikorCommons;

import java.util.Arrays;
import java.util.StringTokenizer;

public enum HttpStatus {
  OK(200, "OK"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error");

  /**
   * The status code.
   */
  private final int statusCode;

  /**
   * The status message.
   */
  private final String statusMessage;

  HttpStatus(int statusCode, String statusMessage) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
  }

  /**
   * Create a status from its full token.
   *
   * @param token Full token.
   * @return Http status.
   */
  public static HttpStatus fromCode(String token) {
    var tokenizer = new StringTokenizer(token);
    var statusCode = Integer.parseInt(tokenizer.nextToken());
    var statusMessage = tokenizer.nextToken(MantikorCommons.CRLF);
    return Arrays.stream(values())
      .filter(
        httpStatus -> httpStatus.statusCode == statusCode && statusMessage
          .equalsIgnoreCase(httpStatus.statusMessage))
      .findFirst().orElse(INTERNAL_SERVER_ERROR);
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }
}
