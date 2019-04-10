<?php
error_reporting(E_ERROR);

$estate_id = (int)$_REQUEST["estate_id"];
$names = $_REQUEST['names'];
$phone = $_REQUEST['phone'];
$email = $_REQUEST['email'];


include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));



$json = array();

$query = 'INSERT INTO appointments(estate_id, names, phone, email) 
                      VALUES ("'.$estate_id.'", "'.$names.'","'.$phone.'", "'.$email.'" )';
					  


if($db->query($query) == TRUE){
	 	$json['success'] = 'Appointment Succefully Set.';

	 }else{
	 	$json['success'] =  'Internet Connection error.';
	 	echo mysqli_error($db);
	 }


print(json_encode($json));

?>