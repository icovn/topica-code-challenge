<?php
    //init PHP settings
    ini_set('default_mimetype', 'text/html');
    ini_set('default_charset', 'UTF-8');

    ini_set('date.timezone', 'Asia/Ho_Chi_Minh');

    ini_set('session.save_handler', 'redis');
    ini_set('session.save_path', 'tcp://127.0.0.1:6379');
    ini_set('session.hash_function', 1);
    ini_set('session.hash_bits_per_character', 4);
    ini_set('session.name', 'my-secure-session');
?>

<?php
    //session
    ob_start();
    session_start();
?>
