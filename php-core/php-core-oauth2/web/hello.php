<?php
/**
 *
 * User: icovn
 * Date: 6/2/2018
 * Time: 9:45 AM
 */

include_once "../vendor/autoload.php";
require_once("../src/RedisFunctions.php");

if (isset($_SESSION['username']) && !empty($_SESSION['username'])) {
	echo 'Username: ' . $_SESSION['username'];
}else{
	$key = $_COOKIE['my-secure-session'];
	$redisUsername = get("loggedIn:".$key);
	if(!empty($redisUsername)){
		echo 'Username: ' . $redisUsername;	
	}else {
		echo "You are not logged in";
	}
}