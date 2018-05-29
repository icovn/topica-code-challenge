package net.friend.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

  public static String getStackTrace(Exception e) {
    StringWriter sWriter = new StringWriter();
    PrintWriter pWriter = new PrintWriter(sWriter);
    e.printStackTrace(pWriter);
    String stackTrace = sWriter.toString();
    return stackTrace;
  }
}
