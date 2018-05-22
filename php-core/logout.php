<?php
   include_once "../init.php";
?>

<?php
   session_start();
   unset($_SESSION["username"]);
   unset($_SESSION["password"]);

   echo 'You have cleaned session';
?>

<br/><br/>Click <a href="index.php">here</a> to homepage.