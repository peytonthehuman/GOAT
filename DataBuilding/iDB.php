<?php
	# iDB.php
	function connectToDB($dbName, $flag) {
		$file = fopen($dbName, $flag) or die("Unable to connect to DB");
		return $file;
	}
	
	function SQLtoDB($dbObj, $sql) {
		return NULL;
	}
	
	# Specific Functions:
	function parseTSV($data) {
		
	}
	
	function parseCSV($data) {
		return NULL;
	}
	
	function writeLinestoDB($dbObj, $indexArray, $dataArray) {
		
	}
	
	function readLinesFromDB($dbObj, $indexArray) {
		
	}
	
	function readNextLineFromDB($dbObj) {
		var $line;
		
		if(($line = fgets($file)) !== false) {
			#send error
		}
		
		return $line;
	}
?>