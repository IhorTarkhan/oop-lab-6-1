package com.ihortarkhan.ooplab61.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;

@Data
public class PropertiesConfiguration {
  private String keycloakFile;

  private static PropertiesConfiguration instance = null;

  @SneakyThrows
  public static PropertiesConfiguration getProperties() {
    if (instance == null) {
      instance =
          new ObjectMapper().readValue(new File(System.getenv("CONFIG_FILE")), PropertiesConfiguration.class);
    }
    return instance;
  }
}
