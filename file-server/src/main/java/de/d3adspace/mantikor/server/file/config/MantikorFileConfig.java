package de.d3adspace.mantikor.server.file.config;

import de.d3adspace.mantikor.server.config.MantikorConfig;
import lombok.Getter;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
@Getter
public class MantikorFileConfig extends MantikorConfig {

    private final String basePath;

    public MantikorFileConfig(String serverHost, int serverPort, String basePath) {
        super(serverHost, serverPort);
        this.basePath = basePath;
    }
}
