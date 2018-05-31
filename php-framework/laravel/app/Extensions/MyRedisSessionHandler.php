<?php
/**
 *
 * User: icovn
 * Date: 5/23/2018
 * Time: 5:40 PM
 */

namespace App\Extensions;


class MyRedisSessionHandler implements \SessionHandlerInterface
{
    public function open($savePath, $sessionName) {
        return true;
    }

    public function close() {
        return true;
    }

    public function read($sessionId) {
//        var_dump($sessionId);
//        die;
    }

    public function write($sessionId, $data) {
//        var_dump($sessionId);
//        die;
    }

    public function destroy($sessionId) {

    }

    public function gc($lifetime) {

    }
}