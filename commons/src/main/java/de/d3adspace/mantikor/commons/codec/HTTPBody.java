package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

public final class HTTPBody {

  /**
   * The content of the body.
   */
  private char[] content;

  private HTTPBody(char[] content) {
    this.content = content;
  }

  public static HTTPBody empty() {
    return withContent(new char[0]);
  }

  public static HTTPBody fromString(String content) {
    Preconditions.checkNotNull(content);

    char[] bytes = content.toCharArray();
    return withContent(bytes);
  }

  public static HTTPBody withContent(char[] content) {
    Preconditions.checkNotNull(content);

    return new HTTPBody(content);
  }

  public char[] getContent() {
    return content.clone();
  }

  public int getSize() {
    return content.length;
  }
}
