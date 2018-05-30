package net.friend.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtil {

  public static String[] toArray(Set<String> sourceSet){
    return sourceSet.toArray(new String[sourceSet.size()]);
  }

  public static Set<String> toSet(String[] sourceArray){
    return new HashSet<>(Arrays.asList(sourceArray));
  }
}
