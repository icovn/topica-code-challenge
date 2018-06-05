<?php
/**
 * Created by IntelliJ IDEA.
 * User: huynq12
 * Date: 17/10/2017
 * Time: 12:56
 */

require __DIR__.'/../vendor/autoload.php';

function getConfig($key) {
    $dotenv = new Dotenv\Dotenv(__DIR__.'/../');
    $dotenv->overload();
    return getenv($key);
}
