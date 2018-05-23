<?php

//init PHP settings
ini_set('default_mimetype', 'text/html');
ini_set('default_charset', 'UTF-8');

ini_set('date.timezone', 'Asia/Ho_Chi_Minh');

ini_set('session.hash_function', 1);
ini_set('session.hash_bits_per_character', 4);
ini_set('session.cookie_domain', '.icovn.me' );
ini_set('session.name', 'my-secure-session');


$filename = __DIR__.preg_replace('#(\?.*)$#', '', $_SERVER['REQUEST_URI']);
if (php_sapi_name() === 'cli-server' && is_file($filename)) {
    return false;
}

require_once __DIR__ . '/../app.php';

# definitions
$app['debug'] = true;

$app->run();


