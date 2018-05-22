<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:11
 */

use DI\ContainerBuilder;
require __DIR__ . '/../vendor/autoload.php';
$containerBuilder = new ContainerBuilder;
$containerBuilder->addDefinitions(__DIR__ . '/config.php');
$container = $containerBuilder->build();
return $container;