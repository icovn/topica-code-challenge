<?php
/**
 *
 * User: icovn
 * Date: 5/24/2018
 * Time: 5:52 PM
 */

namespace App\Providers;

use Illuminate\Support\Facades\Session;
use Illuminate\Support\ServiceProvider;

class MySessionServiceProvider extends ServiceProvider
{
    /**
     * Perform post-registration booting of services.
     *
     * @return void
     */
    public function boot()
    {
        Session::extend('redis', function ($app) {
            // Return implementation of SessionHandlerInterface...
            return new \App\Extensions\MyRedisSessionHandler;
        });
    }

    /**
     * Register bindings in the container.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}