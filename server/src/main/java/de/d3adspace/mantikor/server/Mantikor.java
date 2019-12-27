package de.d3adspace.mantikor.server;

/**
 * Basic interface for server actions.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public interface Mantikor {

  /**
   * Start the server.
   */
  void start();

  /**
   * Stop the server.
   */
  void stop();

  /**
   * Check if the server is running.
   *
   * @return If the server is running.
   */
  boolean isRunning();
}
