<?php
	# Setup
	$dataSourcePath = "..\\..\\dataSources\\";
	
	$genreArray = array();
	$keywordArray = array();
	
	# includes
	include 'iDB.php';
	include 'movie_source.php';
	//include 'book_source.php';
	//include 'vgame_source.php';
	
	# Run Movie Code
	openMovieSourceConn();
	parseMovieData();
	closeMovieSourceConn();
	
	# Run Book Code
	
	# Run VGame Code
	
	# Merge Genre & keywords lists
	
	# Write Genre & Keywords To Database
	$genreArray = $movieGenreArray;
	$genreFile = connectToDB('genre.csv', 'w');
	
	writeLinetoDB($genreFile, ['GENRE', 'OCCURANCE']);
		
	foreach(array_keys($genreArray) as $genre) {
		writeLinetoDB($genreFile, [$genre, $genreArray[$genre]]);
	}
?>