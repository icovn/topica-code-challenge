<?php

require_once __DIR__ . '/../vendor/autoload.php';

use Symfony\Component\HttpFoundation\Request;

$app = new Silex\Application();

$app->register(new net\friend\DotEnvServiceProvider(), array(
    'dotenv.path' => dirname( dirname(__FILE__) ),
));
$app['dotenv'];

$app->register(new Silex\Provider\MonologServiceProvider(), array(
    'monolog.logfile' => __DIR__ . '/../logs/development.log',
));

$app->register(new net\friend\SessionServiceProvider());

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

    if (getenv("APP_USERNAME") === $username && getenv("APP_PASSWORD") === $password) {
        $app['monolog']->info(sprintf("Login ok"));
        $app['session']->set('user', array('username' => $username));
        session_regenerate_id();
        return $app->redirect('/');
    }else{
        $app['monolog']->info(sprintf("Login nok %s %s", $username, getenv("APP_USERNAME")));
    }

    return $app['twig']->render('login.twig', array(
        'message' => "Invalid username or password",
    ));
});

$app->get('/logout', function () use ($app) {
    $app['session']->remove('user');
    return $app->redirect('/');
});