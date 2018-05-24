<?php
/**
 * Created by IntelliJ IDEA.
 * User: huynq12
 * Date: 22/05/2018
 * Time: 18:19
 */

namespace net\friend;

use Pimple\Container;
use Silex\Provider\Session\SessionListener;
use Silex\Provider\Session\TestSessionListener;
use Symfony\Component\HttpFoundation\Session\Storage\NativeSessionStorage;
use Symfony\Component\HttpFoundation\Session\Storage\MockFileSessionStorage;
use Symfony\Component\HttpFoundation\Session\Session;

class SessionServiceProvider extends \Silex\Provider\SessionServiceProvider
{
    public function register(Container $app)
    {
        $app['session.test'] = false;

        $app['session'] = function ($app) {
            return new Session($app['session.storage'], $app['session.attribute_bag'], $app['session.flash_bag']);
        };

        $app['session.storage'] = function ($app) {
            if ($app['session.test']) {
                return $app['session.storage.test'];
            }

            return $app['session.storage.native'];
        };

        $this::initSessionStorage();

        $app['session.storage.native'] = function ($app) {
            return new NativeSessionStorage(
                $app['session.storage.options'],
                $app['session.storage.handler']
            );
        };

        $app['session.listener'] = function ($app) {
            return new SessionListener($app);
        };

        $app['session.storage.test'] = function () {
            return new MockFileSessionStorage();
        };

        $app['session.listener.test'] = function ($app) {
            return new TestSessionListener($app);
        };

        $app['session.storage.options'] = [];
        $app['session.storage.save_path'] = null;
        $app['session.attribute_bag'] = null;
        $app['session.flash_bag'] = null;

        $app['monolog']->info("init redis session done");
    }

    private function initSessionStorage(){
        //Config
        $config = ['ttl'=>3600, 'prefix'=>'icovn-'];

        //Storage
        $client = new \Redis();
        $client->connect('127.0.0.1', 6379);
        $pool = new \Cache\Adapter\Redis\RedisCachePool($client);

        //Set handler then start
        $sessionHandler = new \Cache\SessionHandler\Psr6SessionHandler($pool, $config);
        $app['session.storage.handler'] = $sessionHandler;
    }
}