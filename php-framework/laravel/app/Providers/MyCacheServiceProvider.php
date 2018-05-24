<?php
/**
 *
 * User: icovn
 * Date: 5/24/2018
 * Time: 5:27 PM
 */

namespace App\Providers;

class MyCacheServiceProvider extends \Illuminate\Cache\CacheServiceProvider
{
    public function register()
    {
        $this->app->singleton('cache', function ($app) {
            return new \App\Extensions\MyCacheManager($app);
        });

        $this->app->singleton('cache.store', function ($app) {
            return $app['cache']->driver();
        });

        $this->app->singleton('memcached.connector', function () {
            return new \Illuminate\Cache\MemcachedConnector;
        });
    }
}