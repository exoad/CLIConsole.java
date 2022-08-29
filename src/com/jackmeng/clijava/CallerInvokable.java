package com.jackmeng.clijava;

public interface CallerInvokable {
  String invoke(String... args);

  String getSectionName();

  String getAllCommands();

  default String[] parseCommand(String command) {
    return command.split(" ");
  }
}
