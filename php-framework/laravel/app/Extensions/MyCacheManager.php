<?php
/**
 *
 * User: icovn
 * Date: 5/24/2018
 * Time: 5:26 PM
 */

namespace App\Extensions;


class MyCacheManager extends \Illuminate\Cache\CacheManager
{
    /**
     * Create an instance of the Redis cache driver.
     *
     * @param  array  $config
     * @return \Illuminate\Cache\RedisStore
     */
    protected function createRedisDriver(array $config)
    {
        $redis = $this->app['redis'];

        $connection = $config['connection'] ?? 'default';

        return $this->repository(new MyRedisStore($redis, $this->getPrefix($config), $connection));
    }
}