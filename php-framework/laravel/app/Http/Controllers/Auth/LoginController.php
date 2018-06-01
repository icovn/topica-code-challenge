<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Auth\GenericUser;
use Illuminate\Foundation\Auth\AuthenticatesUsers;
use Illuminate\Support\Facades\Auth;

use Socialite;

class LoginController extends Controller
{
    /*
    |--------------------------------------------------------------------------
    | Login Controller
    |--------------------------------------------------------------------------
    |
    | This controller handles authenticating users for the application and
    | redirecting them to your home screen. The controller uses a trait
    | to conveniently provide its functionality to your applications.
    |
    */

    use AuthenticatesUsers;

    /**
     * Where to redirect users after login.
     *
     * @var string
     */
    protected $redirectTo = '/home';

    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('guest')->except('logout');
    }

    /**
     * Redirect the user to the GitHub authentication page.
     *
     * @return \Illuminate\Http\Response
     */
    public function redirectToProvider()
    {
        return Socialite::driver('github')
            ->scopes(['read:user', 'public_repo'])
            ->redirect();
    }

    /**
     * Obtain the user information from GitHub.
     *
     * @return \Illuminate\Http\Response
     */
    public function handleProviderCallback()
    {
        $user = Socialite::driver('github')->stateless()->user();
        $newUser = array(
            "id" => $user->id,
            "avatar" => $user->avatar,
            "email" => $user->email,
            "name" => $user->email,
            "token" => $user->token,
        );
        Auth::login(new GenericUser($newUser));

        // $user->token;
        return view('home');
    }

    /**
     * Redirect the user to the ICOVN authentication page.
     *
     * @return \Illuminate\Http\Response
     */
    public function redirectToIcovnProvider()
    {
//        $clientId = config('services.icovn.client_id');
//        $clientSecret = config('services.icovn.client_secret');
//        $redirectUrl = config('services.icovn.redirect');
//        $config = new \SocialiteProviders\Manager\Config($clientId, $clientSecret, $redirectUrl);
        return Socialite::driver('icovn')
//            ->setConfig($config)
            ->scopes(['read', 'write'])
            ->redirect();
    }

    /**
     * Obtain the user information from ICOVN.
     *
     * @return \Illuminate\Http\Response
     */
    public function handleIcovnProviderCallback()
    {
        $user = Socialite::driver('icovn')->stateless()->user();
        $newUser = array(
            "id" => $user->email,
//            "email" => $user->email,
            "name" => $user->email,
        );
        Auth::login(new GenericUser($newUser));

        // $user->token;
        return view('home');
    }
}
