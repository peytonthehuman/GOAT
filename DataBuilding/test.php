<?php
	# Setup
	$dataSourcePath = "..\\..\\dataSources\\";
	
	# includes interface
	include 'iDB.php';
	include 'book_source.php';
	
	# Run Book Code
	function open() {
		global $bookSourcesConnArray;
		openBookSourceConn();
		readNextLineFromDB($bookSourcesConnArray["LOC"]);
		readNextLineFromDB($bookSourcesConnArray["LOC"]);
		readNextLineFromDB($bookSourcesConnArray["LOC"]);
	}
	
	function printParseNextBook($printbool) {
		global $bookSourcesConnArray;
		$retVar = parseNextXMLRecordAsBook($bookSourcesConnArray["LOC"]);
		if($printbool) print_r($retVar);
		return $retVar;
	}
	
	function printNumofBooks($nums) {
		$i = 0;
		while($i < $nums) {
			print_r(printParseNextBook(FALSE));
			$i += 1;
		}
	}
	
	function close() {
		closeBookSourceConn();
	}
?>