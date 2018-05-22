<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:07
 */

use FastRoute\RouteCollector;
$container = require __DIR__ . '/../app/bootstrap.php';
$dispatcher = FastRoute\simpleDispatcher(function (RouteCollector $r) {
    $r->addRoute('GET', '/', 'net\friend\Controller\HomeController');
    $r->addRoute('GET', '/article/{id}', ['net\friend\Controller\ArticleController', 'show']);
});
$route = $dispatcher->dispatch($_SERVER['REQUEST_METHOD'], $_SERVER['REQUEST_URI']);
switch ($route[0]) {
    case FastRoute\Dispatcher::NOT_FOUND:
        echo '404 Not Found';
        break;
    case FastRoute\Dispatcher::METHOD_NOT_ALLOWED:
        echo '405 Method Not Allowed';
        break;
    case FastRoute\Dispatcher::FOUND:
        $controller = $route[1];
        $parameters = $route[2];
        // We could do $container->get($controller) but $container->call()
        // does that automatically
        $container->call($controller, $parameters);
        break;
}

#Session

## Config
$config = ['ttl'=>3600, 'prefix'=>'icovn-'];

## Storage
$client = new \Redis();
$client->connect('127.0.0.1', 6379);
$pool = new \Cache\Adapter\Redis\RedisCachePool($client);
$prefixedPool = new \Cache\Prefixed\PrefixedCachePool($pool, 'cp-');

## Set handler then start
$sessionHandler = new \Cache\SessionHandler\Psr6SessionHandler($prefixedPool, $config);
session_set_save_handler($sessionHandler, true);
session_start();