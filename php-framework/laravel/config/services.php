<?php

return [

    /*
    |--------------------------------------------------------------------------
    | Third Party Services
    |--------------------------------------------------------------------------
    |
    | This file is for storing the credentials for third party services such
    | as Stripe, Mailgun, SparkPost and others. This file provides a sane
    | default location for this type of information, allowing packages
    | to have a conventional place to find your various credentials.
    |
    */

    'mailgun' => [
        'domain' => env('MAILGUN_DOMAIN'),
        'secret' => env('MAILGUN_SECRET'),
    ],

    'ses' => [
        'key' => env('SES_KEY'),
        'secret' => env('SES_SECRET'),
        'region' => env('SES_REGION', 'us-east-1'),
    ],

    'sparkpost' => [
        'secret' => env('SPARKPOST_SECRET'),
    ],

    'stripe' => [
        'model' => App\User::class,
        'key' => env('STRIPE_KEY'),
        'secret' => env('STRIPE_SECRET'),
    ],

    'github' => [
        'client_id' => env('GITHUB_CLIENT_ID'),         // Your GitHub Client ID
        'client_secret' => env('GITHUB_CLIENT_SECRET'), // Your GitHub Client Secret
        'redirect' => env('GITHUB_CALLBACK_URL'),
    ],

    'icovn' => [
        'client_id' => env('ICOVN_CLIENT_ID', 'acme'),
        'client_secret' => env('ICOVN_CLIENT_SECRET', 'acmesecret'),
        'redirect' => env('ICOVN_CALLBACK_URL', 'http://localhost:8000/login/icovn/callback'),
        'access_token_url' => env('ICOVN_ACCESS_TOKEN_URL', 'http://sso.icovn.me/oauth/token'),
        'user_authorization_url' => env('ICOVN_USER_AUTHORIZATION_URL', 'http://sso.icovn.me/oauth/authorize'),
        'user_logout_url' => env('ICOVN_USER_LOGOUT_URL', 'http://sso.icovn.me/oauth/exit'),
        'user_info_url' => env('ICOVN_USER_INFO_URL', 'http://sso.icovn.me/user/me'),
    ],
];
