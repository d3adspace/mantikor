package de.d3adspace.mantikor.commons;

import de.d3adspace.mantikor.commons.codec.HTTPBody;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import lombok.Data;

@Data
public class HTTPMessage {

  private final HTTPHeaders headers;

  private final HTTPBody body;
}
