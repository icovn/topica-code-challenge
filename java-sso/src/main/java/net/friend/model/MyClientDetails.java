package net.friend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.friend.util.JsonUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Data
@Entity
@NoArgsConstructor
@Table(name = "oauth_clients")
public class MyClientDetails implements ClientDetails {

  @Id
  private String clientId;

  private String clientSecret;

  private String resourceIds;

  private boolean isSecretRequired;

  private boolean isScoped;

  private String scope;

  private String authorizedGrantTypes;

  private String registeredRedirectUri;

  private String authorities;

  private Integer accessTokenValiditySeconds;

  private Integer refreshTokenValiditySeconds;

  private boolean isAutoApprove;

  private String additionalInformation;

  @Override
  public String getClientId() {
    return clientId;
  }

  @Override
  public Set<String> getResourceIds() {
    return JsonUtil.toSet(resourceIds);
  }

  @Override
  public boolean isSecretRequired() {
    return isSecretRequired;
  }

  @Override
  public String getClientSecret() {
    return clientSecret;
  }

  @Override
  public boolean isScoped() {
    return isScoped;
  }

  @Override
  public Set<String> getScope() {
    return JsonUtil.toSet(scope);
  }

  @Override
  public Set<String> getAuthorizedGrantTypes() {
    return JsonUtil.toSet(authorizedGrantTypes);
  }

  @Override
  public Set<String> getRegisteredRedirectUri() {
    return JsonUtil.toSet(registeredRedirectUri);
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    Set<String> setOfAuthorities = JsonUtil.toSet(authorities);
    for(String authority: setOfAuthorities){
      grantedAuthorities.add(new MyGrantedAuthority(authority));
    }

    return grantedAuthorities;
  }

  @Override
  public Integer getAccessTokenValiditySeconds() {
    return accessTokenValiditySeconds;
  }

  @Override
  public Integer getRefreshTokenValiditySeconds() {
    return refreshTokenValiditySeconds;
  }

  @Override
  public boolean isAutoApprove(String scope) {
    return isAutoApprove;
  }

  @Override
  public Map<String, Object> getAdditionalInformation() {
    return JsonUtil.convert(additionalInformation);
  }
}
