<?php
	# Setup
	$dataSourcePath = "..\\..\\dataSources\\";
	
	# includes interface
	include 'iDB.php';
	include 'book_source.php';
	
	# Run Book Code
	function open() {
		openBookSourceConn();
	}
	
	function printParseNextBook($printbool) {
		global $bookSourcesConnArray;
		$retVar = parseNextXMLRecordAsBook($bookSourcesConnArray["01"]);
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