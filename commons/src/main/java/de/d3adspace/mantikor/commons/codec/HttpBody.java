package de.d3adspace.mantikor.commons.codec;

import com.google.common.base.Preconditions;

public final class HttpBody {
  private char[] content;

  private HttpBody(char[] content) {
    this.content = content;
  }

  public static HttpBody empty() {
    return withContent(new char[0]);
  }

  /**
   * Create an http body from a simple string.
   *
   * @param content Body content.
   * @return Http body.
   */
  public static HttpBody fromString(String content) {
    Preconditions.checkNotNull(content);
    var bytes = content.toCharArray();
    return withContent(bytes);
  }

  public static HttpBody withContent(char[] content) {
    Preconditions.checkNotNull(content);
    return new HttpBody(content);
  }

  public char[] getContent() {
    return content.clone();
  }

  public int getSize() {
    return content.length;
  }
}
