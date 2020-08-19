<?php
function createconnection()
    {
	$dbhost = "localhost"; //localhost for local database
	$dbuser = "root";      //User name
	$dbpass = "";      //password
	$dbname = "contactbook";    // Database name
	                       // Create connection
	$con = new mysqli($dbhost, $dbuser, $dbpass, $dbname) ;
     if (!$con){
        die("Connection Failed : " . mysqli_connect_error());
    }
    return $con;
}
