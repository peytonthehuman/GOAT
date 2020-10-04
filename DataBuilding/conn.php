<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$servername = "localhost";
$username = "root";
$password = "Peyton18@";

try {
  $conn = new PDO("mysql:host=$servername;dbname=Media_Recommender", $username, $password);
  // set the PDO error mode to exception
  //$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  echo "Connected successfully";
} catch(PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}

    ?>