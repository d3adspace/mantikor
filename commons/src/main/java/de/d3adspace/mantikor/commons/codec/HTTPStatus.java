package de.d3adspace.mantikor.commons.codec;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HTTPStatus {

    OK(200, "OK");

    /**
     * The status code.
     */
    private final int statusCode;

    /**
     * The status message.
     */
    private final String statusMessage;
}
