<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:12
 */

use function DI\create;
use net\friend\Model\ArticleRepository;
use net\friend\Persistence\InMemoryArticleRepository;

//init PHP settings
ini_set('default_mimetype', 'text/html');
ini_set('default_charset', 'UTF-8');

ini_set('date.timezone', 'Asia/Ho_Chi_Minh');

ini_set('session.name', 'my-secure-session');

return [
    // Bind an interface to an implementation
    ArticleRepository::class => create(InMemoryArticleRepository::class),
    // Configure Twig
    Twig_Environment::class => function () {
        $loader = new Twig_Loader_Filesystem(__DIR__ . '/../src/net/friend/Views');
        return new Twig_Environment($loader);
    },
];