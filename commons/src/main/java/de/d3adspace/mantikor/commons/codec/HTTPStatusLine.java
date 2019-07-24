package de.d3adspace.mantikor.commons.codec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HTTPStatusLine {

  /**
   * The version.
   */
  private HTTPVersion version;

  /**
   * The status.
   */
  private HTTPStatus status;
}
