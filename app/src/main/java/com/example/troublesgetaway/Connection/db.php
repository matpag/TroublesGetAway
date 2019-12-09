<?php
// parametri del database
$db_host = "localhost";
$db_user = "user_db";
$port = "port=5432 ";
$db_password = "user_password";
$db_name = "nome_database";

//$con = mysql_connect($db_host, $db_user, $db_password, $db_name);

$con=mysql_connect($db_host,$db_user,$db_password);
