package de.d3adspace.mantikor.commons.codec;

public class HTTPBody {

    /**
     * The content of the body.
     */
    private char[] content;

    /**
     * Create a new body by its content.
     *
     * @param content The content.
     */
    public HTTPBody(char[] content) {

        this.content = content;
    }

    /**
     * Get the content of the body.
     *
     * @return The body.
     */
    public char[] getContent() {

        return content;
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
