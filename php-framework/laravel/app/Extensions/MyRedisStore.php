<?php
/**
 *
 * User: icovn
 * Date: 5/24/2018
 * Time: 5:21 PM
 */

namespace App\Extensions;


class MyRedisStore extends \Illuminate\Cache\RedisStore
{
    public function setPrefix($prefix)
    {
        $this->prefix = ! empty($prefix) ? $prefix.'|' : '';
    }
}