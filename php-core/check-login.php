<?php
   include_once "../init.php";
?>

<?php
   if (!isset($_SESSION['_sf2_attributes']) || !isset($_SESSION['_sf2_attributes']['user'])) {
      header("Location: /login.php");
      die();
   }
?>

<?php
    echo "Username: ".$_SESSION['_sf2_attributes']['user']['username'];
?>

<br/><br/>Click <a href="logout.php">here</a> to log out.