<?php
/**
 *
 * User: icovn
 * Date: 5/31/2018
 * Time: 6:30 PM
 */

namespace App\Providers\SocialiteProviders;

use Laravel\Socialite\Two\ProviderInterface;
use SocialiteProviders\Manager\OAuth2\AbstractProvider;
use SocialiteProviders\Manager\OAuth2\User;

class Provider extends AbstractProvider implements ProviderInterface
{
    /**
     * Unique Provider Identifier.
     */
    const IDENTIFIER = 'ICOVN';

    /**
     * The separating character for the requested scopes.
     *
     * @var string
     */
    protected $scopeSeparator = ' ';

    /**
     * The scopes being requested.
     *
     * @var array
     */
    protected $scopes = [
        'read',
        'write',
    ];

    /**
     * Get the authentication URL for the provider.
     *
     * @param string $state
     *
     * @return string
     */
    protected function getAuthUrl($state)
    {
        return $this->buildAuthUrlFromBase(
            $this->getConfig('user_authorization_url', 'http://sso.icovn.me/oauth/authorize'), $state
        );
    }

    /**
     * Get the token URL for the provider.
     *
     * @return string
     */
    protected function getTokenUrl()
    {
        return $this->getConfig('access_token_url', 'http://sso.icovn.me/oauth/token');
    }

    /**
     * Get the raw user for the given access token.
     *
     * @param string $token
     *
     * @return array
     */
    protected function getUserByToken($token)
    {
        $response = $this->getHttpClient()->get(
            $this->getConfig('user_info_url', 'http://sso.icovn.me/user/me'), [
            'headers' => [
                'Authorization' => 'Bearer '.$token,
            ],
        ]);
        return json_decode($response->getBody()->getContents(), true);
    }

    /**
     * Map the raw user array to a Socialite User instance.
     *
     * @param array $user
     *
     * @return \Laravel\Socialite\User
     */
    protected function mapUserToObject(array $user)
    {
        return (new User())->setRaw($user)->map([
            'email'    => $user['name'] ?? null,
        ]);
    }

    /**
     * Get the POST fields for the token request.
     *
     * @param string $code
     *
     * @return array
     */
    protected function getTokenFields($code)
    {
        return array_merge(parent::getTokenFields($code), [
            'grant_type' => 'authorization_code',
        ]);
    }

    /**
     * @return \SocialiteProviders\Manager\OAuth2\User
     */
    public function user()
    {
        if ($this->hasInvalidState()) {
            throw new InvalidStateException();
        }
        $response = $this->getAccessTokenResponse($this->getCode());
        if ($jwt = $response['id_token'] ?? null) {
            list($headb64, $bodyb64, $cryptob64) = explode('.', $jwt);
            $user = $this->mapUserToObject(json_decode(base64_decode($bodyb64), true));
        } else {
            $user = $this->mapUserToObject($this->getUserByToken(
                $token = $this->parseAccessToken($response)
            ));
        }
        $this->credentialsResponseBody = $response;
        if ($user instanceof User) {
            $user->setAccessTokenResponseBody($this->credentialsResponseBody);
        }
        return $user->setToken($this->parseAccessToken($response))
            ->setRefreshToken($this->parseRefreshToken($response))
            ->setExpiresIn($this->parseExpiresIn($response));
    }
}