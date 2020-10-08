<?php
	# iDB.php
	function connectToDB($dbName, $flag) {
		$file = fopen($dbName, $flag);
		return $file;
	}
	
	# Specific Functions:
	function cleanStrArray($strArray) {
		$newline = array();
		foreach($strArray as $cell) {
			array_push($newline, rtrim($cell));
		}
		return $newline;
	}
	
	function parseTSVLine($data) {
		$line = explode("\t", $data);
		return cleanStrArray($line);
	}
	
	function parseCSVLine($data) {
		$line = explode(",", $data);
		return cleanStrArray($line);
	}
	
	function readNextLineFromDB($dbObj) {
		return fgets($dbObj);
	}
	
	function writeLinetoDB($dbObj, $dataArray) {
		$writeString = "";
		
		foreach($dataArray as $data) {
			$writeString .= $data;
			$writeString .= ',';
		}
		
		$writeString = rtrim($writeString, ',');
		
		fwrite($dbObj, $writeString);
		fwrite($dbObj, PHP_EOL);
	}
	
	function disconnectFromDB($dbObj) {
		fclose($dbObj);
	}
?>