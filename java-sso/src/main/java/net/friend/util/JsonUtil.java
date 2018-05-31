package net.friend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class JsonUtil {

  public static Map<String, Object> convert(String json) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(json, new TypeReference<Map<String, String>>() {
      });
    } catch (IOException ex) {
      return null;
    }
  }

  public static String convertToJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(object);
    } catch (IOException ex) {
      return null;
    }
  }
}
