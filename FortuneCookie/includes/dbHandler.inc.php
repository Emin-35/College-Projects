<?php

$serverName = "localhost";
$dBUsername = "";
$dBPassword = "root";
$dBName = "fortuneCookie";

$conn = mysqli_connect($serverName, $dBUsername, $dBPassword, $dBName);

if (!$conn) {
    die("Connection Failed: " . mysqli_connect_error("Failed"));
}