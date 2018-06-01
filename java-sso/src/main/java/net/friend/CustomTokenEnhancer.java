package net.friend;

import java.util.HashMap;
import java.util.Map;
import net.friend.model.User;
import net.friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

  @Autowired
  private UserService userService;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    final Map<String, Object> additionalInfo = new HashMap<>();

    User user = userService.findByUsername(authentication.getName());
    if (user != null) {
      additionalInfo.put("id", user.getId());
      additionalInfo.put("email", user.getUsername());
    }

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }

}
