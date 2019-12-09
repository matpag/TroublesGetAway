<?php
include "db.php";

if ($con == FALSE)
die ("Errore nella connessione. Verificare i parametri nel file db.php");

mysql_select_db($db_name, $con) 
or die ("Errore nella selezione del database. Verificare i parametri nel file db.php");

$user = $_POST['usr'];
$password = $_POST['psw'];
$pwdMD5 = md5($password);

$query="SELECT id, name FROM users WHERE email='". addslashes($user) ."' AND password='" . $pwdMD5 ."';";

$result = mysql_query($query); 

$count = mysql_num_rows($result);

if($count>=1){
	while ($row = mysql_fetch_assoc($result)) {
		$name = $row['name'];
		$id = $row['id'];
	}
    $a = array('id' => $id, 'name' => $name);
} else {
    $a = array('id' => '-1', 'name' => 'null');
}

print(json_encode($a));


mysql_close($con);

?>