<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 17:36
 */

namespace net\friend;

use Pimple\ServiceProviderInterface;
use Pimple\Container;
use Dotenv\Dotenv;

class DotEnvServiceProvider implements ServiceProviderInterface
{
    public function register(Container $app)
    {
        $app['dotenv'] = function ($app) {
            $dotenv = new Dotenv($app['dotenv.path']);
            return $dotenv->overload();
        };
    }
}