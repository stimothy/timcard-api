package com.steventimothy.timcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>The Application class</h1>
 * <p>This is the main launching point of the Timcard Application.</p>
 */
@SpringBootApplication
public class Application {

  /**
   * This is the main method where Spring boot launches the application.
   *
   * @param args The commandline arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
