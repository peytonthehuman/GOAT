<?php
	# Setup
	$dataSourcePath = "..\\..\\dataSources\\";
	
	$genreOccArray = array();
	$keywordOccArray = array();
	
	# includes interface
	include 'iDB.php';

	$genreFile = connectToDB($dataSourcePath . 'genre.csv', 'w');
	
	if($genreFile == NULL) {
		print("Can't open genre file");
		exit();
	}
	
	# includes sources
	include 'movie_source.php';
	//include 'book_source.php';
	//include 'vgame_source.php';
	
	# Run Movie Code
	openMovieSourceConn();
	parseMovieData();
	closeMovieSourceConn();
	
	# Run Book Code
	
	# Run VGame Code
	
	# Exit Genre Table
	disconnectFromDB($genreFile);
	
	# Merge Genre & keywords lists
	
	# Write Genre & Keywords To Database
	$genreOccArray = $movieGenreArray;
	$genreOccFile = connectToDB('genreOcc.csv', 'w');
	
	writeLinetoDB($genreOccFile, ['GENRE', 'OCCURANCE']);
		
	foreach(array_keys($genreOccArray) as $genre) {
		writeLinetoDB($genreOccFile, [$genre, $genreOccArray[$genre]]);
	}
?>