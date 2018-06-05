<?php
/**
 * Created by IntelliJ IDEA.
 * User: huynq12
 * Date: 17/10/2017
 * Time: 11:06
 */

$HOST = "127.0.0.1";
$PORT = 6379;
$PASSWORD = null;
$PREFIX = "";

require __DIR__ . '/LogFunctions.php';
require __DIR__.'/../vendor/autoload.php';

function connect(){
    $HOST = getConfig("REDIS_HOST");
    $PORT = getConfig("REDIS_PORT");
    $PASSWORD = getConfig("REDIS_PASSWORD");
    if(strlen($PASSWORD) == 0){
        $PASSWORD = null;
    }
    try {
        $redis = new Predis\Client(array(
            "scheme" => "tcp",
            "host" => $HOST,
            "port" => $PORT,
            "password" => $PASSWORD
        ));
        return $redis;
    }
    catch (Exception $e) {
        logError("MemberServiceFunctions", "Redis connect error:".$e->getMessage());
        return null;
    }
}

/**
 * @param $key
 * @return $string
 */
function get($key) {
    logInfo("(get)",$key);
    $redis = connect();
    if($redis == null) {
        return '';
    }else {
        return redisGet($redis, $key);
    }
}

/**
 * @param $key
 * @param $value
 * @return bool
 */
function set($key, $value) {
    logInfo("(set)",$key."___".$value);
    $redis = connect();
    if($redis == null) {
        return false;
    }else {
        redisSet($redis, $key, $value);
    }
}

function redisGet($redis, $key) {
    $PREFIX = getConfig("REDIS_PREFIX_KEY");
    try {
        logInfo("(redisGet)",$PREFIX . $key);
        return $redis->get($PREFIX . $key);
    }
    catch (Exception $e) {
        logError("RedisFunctions", "Redis get error:".$roomId.",".$e->getMessage());
        return null;
    }
}

function redisSet($redis, $key, $value) {
    $PREFIX = getConfig("REDIS_PREFIX_KEY");
    try {
        logInfo("set", $PREFIX . $key."___".$value);
        $redis->set($PREFIX . $key, $value);        
        return true;
    }
    catch (Exception $e) {
        logError("RedisFunctions", "Redis set error:".$key.",".$value.",".$e->getMessage());
        return false;
    }
}
