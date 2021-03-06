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
	
	function dataAsCSV($dataArray) {
		$writeString = "";
		foreach($dataArray as $data) {
			$dataW = str_replace("|", ":", $data);
			$writeString .= $dataW;
			$writeString .= '|';
		}
		return rtrim($writeString, ',');
	}
	
	function readNextLineFromDB(&$dbObj) {
		$retVal = fgets($dbObj);
		return $retVal;
	}
	
	function writeLinetoDB($dbObj, $dataArray) {
		fwrite($dbObj, dataAsCSV($dataArray));
		fwrite($dbObj, PHP_EOL);
	}
	
	function disconnectFromDB($dbObj) {
		fclose($dbObj);
	}
?>