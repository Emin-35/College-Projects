<?php

if (isset($_POST["submit"])) {

    $Name = $_POST["user_Name"];
    $Lastname = $_POST["user_Lastname"];
    $Email = $_POST["user_Email"];
    $Password = $_POST["user_pwd"];
    $PasswordRepeat = $_POST["user_pwdrepeat"];

    require_once 'dbHandler.inc.php';
    require_once 'functions.inc.php';

    if (invalidName($Name, $Lastname) == false) {
        header("location: ../signup.php?error=Invalidnameorlastname");
        exit();
    }
    if (invalidEmail($Email) !== false) {
        header("location: ../signup.php?error=Invalidemail");
        exit();
    }
    if (pwdMatch($Password, $PasswordRepeat) == false) {
        header("location: ../signup.php?error=Nomatchpasswords");
        exit();
    }
    if (pwdLength($Password) == false) {
        header("location: ../signup.php?error=Passwordmustbebetween6-16characterlength");
        exit();
    }
    if (emailExists($conn, $Email) == true) {
        header("location: ../signup.php?error=Thisemailexists");
        exit();
    }

    createUser($conn, $Name, $Lastname, $Email, $Password);

}
else {
    header("location: ../login.php");
}