<?php

function invalidName($Name, $Lastname) {
    $result;

    if ( (preg_match("/^[a-zA-Z]*$/", $Name)) && (preg_match("/^[a-zA-Z]*$/", $Lastname)) ) {
        $result = true;
    }
    else {
        $result = false;
    }
    return $result;
}

function invalidEmail($Email) {
    $result;

    if (!filter_var($Email, FILTER_VALIDATE_EMAIL)) {
        $result = true;
    }
    else {
        $result = false;
    }
    return $result;
}

function pwdMatch($Password, $PasswordRepeat) {
    $result;

    if ($Password == $PasswordRepeat) {
        $result = true;
    }
    else {
        $result = false;
    }
    return $result;
}

function pwdLength($Password) {
    $result;

    if (6 <= strlen($Password) && strlen($Password) <= 16 ) {
        $result = true;
    }
    else {
        $result = false;
    }
    return $result;
}

function emailExists($conn, $Email) {
    $sql = "SELECT * FROM users WHERE user_Email = ?;";

    $stmt = mysqli_stmt_init($conn);

    if (!mysqli_stmt_prepare($stmt,$sql)) {
        header("location: ../signup.php?error=stmtfailedEmailExists");
        exti();
    }

    mysqli_stmt_bind_param($stmt, "s", $Email);
    mysqli_stmt_execute($stmt);

    $resultData = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($resultData)) {
        return $row;
    }
    else {
        $result = false;
        return $result;
    }

    mysqli_stmt_close($stmt);
}

function createUser($conn, $Name, $Lastname, $Email, $Password) {
    $sql = "INSERT INTO users (user_Name, user_Lastname,	user_Email, user_pwd) VALUES (?, ?, ?, ?);";

    $stmt = mysqli_stmt_init($conn);

    if (!mysqli_stmt_prepare($stmt,$sql)) {
        header("location: ../signup.php?error=stmtfailedCreateUser");
        exti();
    }

    $hashedPwd = password_hash($Password, PASSWORD_DEFAULT);

    mysqli_stmt_bind_param($stmt, "ssss", $Name, $Lastname, $Email, $hashedPwd);
    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);

    header("location: ../fortuneCookies/home.html?error=none");
    exit();
}

function loginUser($conn, $Email, $Password) {
    $emailExists = emailExists($conn, $Email);

    if($emailExists === false) {
        header("location: ../login.php?error=WrongEmail");
    }

    $pwdHashed = $emailExists["user_pwd"];
    $checkPwd = password_verify($Password, $pwdHashed);

    if ($checkPwd === false) {
        header("location: ../login.php?error=WrongPassword");
    }
    else if ($checkPwd === true) {
        session_start();
        $_SESSION["userid"] = $emailExists["user_id"];
        $_SESSION["useremail"] = $emailExists["user_Email"];
        header("location: ../fortuneCookies/home.html");
        exit();
    }
}