package com.jambit;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "at.java")
public interface AtJava {

  String name();
  String email();
  String address();
}
