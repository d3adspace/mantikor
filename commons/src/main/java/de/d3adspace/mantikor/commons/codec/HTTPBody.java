package de.d3adspace.mantikor.commons.codec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HTTPBody {

  /**
   * The content of the body.
   */
  private char[] content;

  public HTTPBody() {
    this(new char[0]);
  }

  /**
   * Get the length of the body.
   *
   * @return The length.
   */
  public int getLength() {

    return content.length;
  }
}
