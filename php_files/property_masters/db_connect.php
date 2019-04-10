<?php

DEFINE('DB_HOST', '127.0.0.1');
DEFINE('DB_USER', 'root');
DEFINE('DB_PASSWORD', '');



$db = new mysqli(DB_HOST, DB_USER, DB_PASSWORD);

if ($db->connect_error) {
    die("Connection failed: " . $db->connect_error);
} 
?>