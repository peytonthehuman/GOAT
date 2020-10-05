<?php
	# movie_source.php
	//$dataSourcePath = "..\\..\\dataSources\\";
	
	$movieGenreArray = array();
	$movieKeywordArray = array();
	
	$movieTempArray = array();
	
	$movieSourcesConnArray = array();
	
	# Functions
	function openMovieSourceConn() {
		global $dataSourcePath, $movieSourcesConnArray;
		$path = $dataSourcePath . 'imdb.tsv';
		print_r($path);
		$movieSourcesConnArray["IMDB"] = connectToDB($path, 'r');
	}
	
	function parseDataArrayLine($dataLine) {
		global $movieSourcesConnArray;
		print_r($movieSourcesConnArray);
		return "Hello";
	}
	
	function closeMovieSourceConn() {
		global $movieSourcesConnArray;
		disconnectFromDB($movieSourcesConnArray["IMDB"]);
	}
?>