<?php

if (isset($_POST["submit"])) {

    $Email = $_POST["user_Email"];
    $Password = $_POST["user_pwd"];

    include_once 'dbHandler.inc.php';
    include_once 'functions.inc.php';

    loginUser($conn, $Email, $Password);
}
else {
    header("location: ../login.php");
    exit();
}