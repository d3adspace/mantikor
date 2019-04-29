package de.d3adspace.mantikor.server.file.config;

import de.d3adspace.mantikor.config.MantikorConfig;
import lombok.Getter;

@Getter
public class MantikorFileConfig extends MantikorConfig {

    private final String basePath;

    public MantikorFileConfig(String serverHost, int serverPort, String basePath) {
        super(serverHost, serverPort);
        this.basePath = basePath;
    }
}
