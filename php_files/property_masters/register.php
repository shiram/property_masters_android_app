<?php

error_reporting(E_ERROR);

$firstname = $_REQUEST['firstname'];
$lastname = $_REQUEST['lastname'];
$email = $_REQUEST['user_email'];
$phone = $_REQUEST['user_phone'];
$password = md5($_REQUEST['user_password']);


include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();

$query = 'INSERT INTO users(firstname, lastname, email, phone, user_password) VAlUES("'.$firstname.'", "'.$lastname.'", "'.$email.'", "'.$phone.'", "'.$password.'")';

if($db->query($query) == TRUE){
        $json['success'] = 'Registration Successful, Please Login to Update profile.';
        $user_id = $db->insert_id;
        $json['id'] = $user_id;

     }else{
        $json['success'] =  'Registration failed, try again.';
        $json['id'] = 0;
        echo mysqli_error($db);
     }

     print(json_encode($json));

?>