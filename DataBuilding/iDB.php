<?php
	# iDB.php
	function connectToDB($dbName, $flag) {
		$file = fopen($dbName, $flag) or die("Unable to connect to DB");
		return $file;
	}
	
	# Specific Functions:
	function parseTSVLine($data) {
		$line = explode("	", $data);
		foreach($line as $cell) {
			$cell = str_replace("\n", "", $cell);
		}
		return $line;
	}
	
	function readNextLineFromDB($dbObj) {
		$line;
		
		if(($line = fgets($dbObj)) !== false) {
			#send error
		}
		
		return $line;
	}
	
	function writeLinestoDB($dbObj, $dataArray) {
		
	}
	
	function disconnectFromDB($dbObj) {
		fclose($dbObj);
	}
?>