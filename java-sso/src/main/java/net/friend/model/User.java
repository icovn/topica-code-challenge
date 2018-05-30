package net.friend.model;

import java.io.Serializable;
import java.util.Set;

public interface User extends Serializable {

  String getId();

  String getUsername();

  String getPassword();

  boolean isActive();

  Set<String> getRoles();
}
