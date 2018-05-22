<?php

require_once __DIR__ . '/../vendor/autoload.php';

use Symfony\Component\HttpFoundation\Request;

$app = new Silex\Application();

$app->register(new net\friend\DotEnvServiceProvider());

$app->register(new net\friend\SessionServiceProvider(), array(
    'dotenv.path' => __DIR__ . '/../',
));

$app->register(new Silex\Provider\MonologServiceProvider(), array(
    'monolog.logfile' => __DIR__ . '/../logs/development.log',
));

$app->register(new Silex\Provider\TwigServiceProvider(), array(
    'twig.path' => __DIR__ . '/views',
));

$app->get('/', function() use($app) {
    if (null === $user = $app['session']->get('user')) {
        return $app->redirect('/login');
    }

    return $app['twig']->render('home.twig', array(
        'username' => $user['username'],
    ));
});

$app->get('/login', function () use ($app) {
    return $app['twig']->render('login.twig', array(
        'message' => "",
    ));
});

$app->post('/login', function (Request $request) use ($app) {
    $username = $request->get('username', false);
    $password = $request->get('password');

    $configUsername = getenv("APP_USERNAME") === '' ? getenv("APP_USERNAME") : "topica";
    $configPassword = getenv("APP_PASSWORD") === '' ? getenv("APP_PASSWORD") : "123";

    if ($configUsername === $username && $configPassword === $password) {
        $app['monolog']->info(sprintf("Login ok"));
        $app['session']->set('user', array('username' => $username));
        return $app->redirect('/');
    }else{
        $app['monolog']->info(sprintf("Login nok %s %s %s", $username, $password, $configUsername));
    }

    return $app['twig']->render('login.twig', array(
        'message' => "Invalid username or password",
    ));
});

$app->get('/logout', function () use ($app) {
    $app['session']->remove('user');
    return $app->redirect('/');
});