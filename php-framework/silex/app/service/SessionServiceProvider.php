<?php
/**
 * Created by IntelliJ IDEA.
 * User: huynq12
 * Date: 22/05/2018
 * Time: 18:19
 */

namespace net\friend;

use Pimple\Container;

class SessionServiceProvider extends \Silex\Provider\SessionServiceProvider
{
    public function register(Container $app)
    {
        parent::register($app);

        //Config
        $config = ['ttl'=>3600, 'prefix'=>getenv("CACHE_PREFIX")];

        //Storage
        $client = new \Redis();
        $client->connect(getenv("REDIS_HOST"), getenv("REDIS_PORT"));
        $pool = new \Cache\Adapter\Redis\RedisCachePool($client);

        //Set handler then start
        $sessionHandler = new \Cache\SessionHandler\Psr6SessionHandler($pool, $config);
        $app['session.storage.handler'] = $sessionHandler;
    }
}