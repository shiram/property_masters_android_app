<?php
error_reporting(E_ERROR);

//$user_id = (int)$_REQUEST["user_id"];
$item_name = $_REQUEST['item_name'];
$item_price = $_REQUEST['item_price'];
$item_desc = $_REQUEST['item_desc'];
$item_cat = $_REQUEST['item_cat'];
$country = $_REQUEST["country"];
$city = $_REQUEST["city"];
$address = $_REQUEST["address"];
$address = $_REQUEST["address2"];
$item_image = $_REQUEST['item_image_file'];
$item_image_en = $_REQUEST['item_image_en'];

/*
$user_id = (int)"1";
$item_name = "Perfume";
$item_price = "20000";
$item_desc = "Uploading images to our server is a very frequently used thing. In most of the apps, we need user avatar, i.e. user profile image. So here is Android Upload Image to Server Tutorial.";
$item_cat = "Cosmetics";
$country = "Uganda";
$city = "Kampala";
$address = "Wandegeya";
$lat = "-2.11";
$lng = "6.99";
$item_image = "jjj.png";
$item_image_en = "hello";
*/

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$rand = substr(md5(microtime()),rand(0,26),5);
$time_date = date('Ymd');
$item_image_dir = 'images/'.$time_date.$rand.$item_image;


$json = array();

$query = 'INSERT INTO estates(estate_name, estate_price, estate_description, estate_category, estate_image, estate_country, estate_city, estate_address_one, estate_address_two) 
                      VALUES ("'.$item_name.'", "'.$item_price.'","'.$item_desc.'", "'.$item_cat.'", "'.$item_image_dir.'", "'.$country.'", "'.$city.'", "'.$address.'" , "'.$address2.'" )';
					  


if($db->query($query) == TRUE){
	 	$json['success'] = 'Estate Succefully Added.';
	
$binary1 = base64_decode($item_image_en);
header('Content-Type: bitmap; charset=utf-8');

$file1 = fopen($item_image_dir, 'wb');
fwrite($file1, $binary1);
fclose($file1);

	 }else{
	 	$json['success'] =  'Internet Connection error.';
	 	echo mysqli_error($db);
	 }


print(json_encode($json));

?>