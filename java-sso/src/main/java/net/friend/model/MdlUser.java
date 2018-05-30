package net.friend.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.friend.util.CollectionUtil;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Table(name="mdl_user")
@ToString(exclude = "password")
public class MdlUser implements User {

  @Id
  private Long id;

  private String username;

  private String password;

  @Column(name = "is_active")
  private boolean isActive;

  private String roles;

  public MdlUser(User user){
    this.id = Long.parseLong(user.getId());
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.isActive = user.isActive();
    this.roles = user.getRoles().toString();
  }

  @Override
  public String getId() {
    return Long.toString(id);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isActive() {
    return isActive;
  }

  @Override
  public Set<String> getRoles() {
    return CollectionUtil.toSet(roles.split(","));
  }
}
