<?php
/**
 * Created by IntelliJ IDEA.
 * User: huynq12
 * Date: 17/10/2017
 * Time: 12:04
 */

require __DIR__ . '/ConfigManager.php';
require __DIR__.'/../vendor/autoload.php';

use Monolog\Logger;
use Monolog\Handler\StreamHandler;

function logInfo($name, $message) {
    $LOG_FOLDER = getConfig("LOG_FOLDER");
    // create a log channel
    $log = new Logger($name);
    $log->pushHandler(new StreamHandler($LOG_FOLDER.'/info.log', Logger::INFO));

    // add records to the log
    $log->info($message);
}

function logError($name, $message) {
    $LOG_FOLDER = getConfig("LOG_FOLDER");
    // create a log channel
    $log = new Logger($name);
    $log->pushHandler(new StreamHandler($LOG_FOLDER.'/error.log', Logger::ERROR));

    // add records to the log
    $log->error($message);
}
