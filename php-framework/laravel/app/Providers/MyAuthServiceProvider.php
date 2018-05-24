<?php
/**
 * Created by IntelliJ IDEA.
 * User: NguyenQuangHuy
 * Date: 5/24/2018
 * Time: 6:31 PM
 */

namespace App\Providers;

use Illuminate\Support\Facades\Auth;

class MyAuthServiceProvider extends \Illuminate\Foundation\Support\Providers\AuthServiceProvider
{
    /**
     * Register any application authentication / authorization services.
     *
     * @return void
     */
    public function boot()
    {
        $this->registerPolicies();

        Auth::provider('myUsers', function ($app, array $config) {
            // Return an instance of Illuminate\Contracts\Auth\UserProvider...

            return new \App\Extensions\MyUserProvider($app->make('app.default.username'), $app->make('app.default.password'));
        });
    }
}