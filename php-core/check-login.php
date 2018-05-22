<?php
   include_once "../init.php";
?>

<?php
   if (!isset($_SESSION['username']) || empty($_SESSION['username'])) {
      header("Location: /login.php");
      die();
   }
?>

<?php
    echo "Username: ".$_SESSION['username'];
?>

<br/><br/>Click <a href="logout.php">here</a> to log out.