<?php
error_reporting(E_ERROR);

$user_email = $_REQUEST["user_email"];
$user_password = md5($_REQUEST["user_password"]);

   //    $user_email = "shirambereh@gmail.com";
   // $user_password = md5("@intjava12");
include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));



        $json = array();
        
        $query = 'SELECT * FROM  users 
        WHERE email = "'.$user_email.'" AND user_password = "'.$user_password.'"';
        $result = mysqli_query($db, $query);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

        $count = mysqli_num_rows($result);

        if($count > 0){
                    
                $json['success'] = 'Sign in Successful';
                $json['id'] = $row['id'];
                $json['user_email'] = $row['email'];
                $json['user_phone'] = $row['phone'];
                $json['firstname'] = $row['firstname'];
                $json['lastname'] = $row['lastname'];
                $json['access_level'] = $row['access_level'];
            
            }else{

                $json['success'] = 'Email or Password Invalid';
                $json['id'] = 0;
                $json['user_email'] = "";
                $json['user_phone'] = "";
                $json['firstname'] = "";
                $json['lastname'] = "";
                $json['access_level'] = -1;
            }
  
    print(json_encode($json));


?>