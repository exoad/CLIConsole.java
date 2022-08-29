package cli.java;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caller {
  private List<CallerInvokable> __loaded_invokables;
  private Map<String, Object> __caller_properties;
  private PrintStream stream;
  public static final String KEY_FAIL_INVOKE_MSG = "print_failed_invoke", KEY_DEFAULT_HELP_CMD = "def_help_cmd";

  public Caller(PrintStream mainStream, CallerInvokable... invokables) {
    __loaded_invokables = new ArrayList<>(Arrays.asList(invokables));
    this.stream = mainStream;
    __init_properties();
  }

  private void __init_properties() {
    __caller_properties = new HashMap<>();
    __caller_properties.put(KEY_FAIL_INVOKE_MSG, true);
    __caller_properties.put(KEY_DEFAULT_HELP_CMD,getDefaultHelpCmd());
  }

  private void __precheck_cmd(String command) {
    if(command.equals(__caller_properties.get(KEY_DEFAULT_HELP_CMD))) {

    }
  }

  public boolean setProperty(String key, Object property) {
    if (__caller_properties.get(key) != null) {
      __caller_properties.put(key, property);
      return true;
    }
    return false;
  }

  public String getDefaultHelpCmd() {
    return "helpcmd";
  }

  public Object getProperty(String key) {
    return __caller_properties.get(key);
  }

  public List<CallerInvokable> getLoadedInvokables() {
    return __loaded_invokables;
  }

  public void invoke(String invokableName, String command) {
    __loaded_invokables.forEach(x -> {
      if (x.getClass().getSimpleName().equals(invokableName)) {
        x.invoke(x.parseCommand(command));
        return;
      }
    });
    if (getProperty(KEY_FAIL_INVOKE_MSG).equals(true)) {
      stream.println("Failed to invoke: " + invokableName + " \nWith command: " + command);
    }
  }

  public String getMasterCommandList() {
    if (__loaded_invokables.size() > 0) {
      StringBuilder sb = new StringBuilder();
      __loaded_invokables.forEach(x -> sb.append("==Section: ").append(x.getSectionName()).append("==")
          .append(x.getAllCommands()).append("\n"));
      return sb.toString();
    }
    return "";
  }

  public String toString() {
    return "[@Caller" + this.hashCode() + ":LOADED:" + __loaded_invokables.size() + ']';
  }
}
