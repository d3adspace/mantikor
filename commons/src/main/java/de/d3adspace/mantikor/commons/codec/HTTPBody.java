package de.d3adspace.mantikor.commons.codec;

import lombok.Data;

@Data
public class HTTPBody {

    /**
     * The content of the body.
     */
    private final char[] content;

    /**
     * Get the length of the body.
     *
     * @return The length.
     */
    public int getLength() {

        return content.length;
    }
}
