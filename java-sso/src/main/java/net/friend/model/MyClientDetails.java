package net.friend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.friend.util.CollectionUtil;
import net.friend.util.JsonUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Entity
@NoArgsConstructor
@Setter
@Table(name = "oauth_clients")
@ToString
public class MyClientDetails implements ClientDetails {

  @Id
  private String clientId;

  private String clientSecret;

  private String resourceIds;

  private Boolean isSecretRequired;

  private Boolean isScoped;

  private String scope;

  private String authorizedGrantTypes;

  private String registeredRedirectUri;

  private String authorities;

  private Integer accessTokenValiditySeconds;

  private Integer refreshTokenValiditySeconds;

  private Boolean isAutoApprove;

  private String additionalInformation;

  @Override
  public String getClientId() {
    return clientId;
  }

  @Override
  public Set<String> getResourceIds() {
    if(resourceIds == null){
      return new HashSet<>();
    }

    return CollectionUtil.toSet(resourceIds.split(","));
  }

  @Override
  public boolean isSecretRequired() {
    if(isSecretRequired == null){
      return false;
    }

    return isSecretRequired;
  }

  @Override
  public String getClientSecret() {
    return clientSecret;
  }

  @Override
  public boolean isScoped() {
    if(isScoped == null){
      return false;
    }

    return isScoped;
  }

  @Override
  public Set<String> getScope() {
    if(scope == null){
      return new HashSet<>();
    }

    return CollectionUtil.toSet(scope.split(","));
  }

  @Override
  public Set<String> getAuthorizedGrantTypes() {
    if(authorizedGrantTypes == null){
      return new HashSet<>();
    }

    return CollectionUtil.toSet(authorizedGrantTypes.split(","));
  }

  @Override
  public Set<String> getRegisteredRedirectUri() {
    if(registeredRedirectUri == null){
      return new HashSet<>();
    }

    return CollectionUtil.toSet(registeredRedirectUri.split(","));
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    if(authorities == null){
      return grantedAuthorities;
    }

    Set<String> setOfAuthorities = CollectionUtil.toSet(authorities.split(","));
    for(String authority: setOfAuthorities){
      grantedAuthorities.add(new MyGrantedAuthority(authority));
    }

    return grantedAuthorities;
  }

  @Override
  public Integer getAccessTokenValiditySeconds() {
    if(accessTokenValiditySeconds == null){
      return 86400; //1 day
    }

    return accessTokenValiditySeconds;
  }

  @Override
  public Integer getRefreshTokenValiditySeconds() {
    if(refreshTokenValiditySeconds == null){
      return 604800; //1 week
    }

    return refreshTokenValiditySeconds;
  }

  @Override
  public boolean isAutoApprove(String scope) {
    if(isAutoApprove == null){
      return true;
    }

    return isAutoApprove;
  }

  @Override
  public Map<String, Object> getAdditionalInformation() {
    if(additionalInformation == null){
      return new HashMap<>();
    }

    return JsonUtil.convert(additionalInformation);
  }
}
