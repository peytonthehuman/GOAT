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
	include 'book_source.php';
	include 'vgame_source.php';
	
	# Run Movie Code
	print("Running movies...\n");
	openMovieSourceConn();
	parseMovieData();
	closeMovieSourceConn();
	print("...movies done.\n");
	
	# Run Book Code
	print("Running Books...\n");
	openBookSourceConn();
	parseAllBookCollections();
	closeBookSourceConn();
	print("...books done.\n");
	
	# Run VGame Code
	print("Running Video Games...\n");
	openVgameSourceConn();
	parseVgameData();
	closeVgameSourceConn();
	print("...games done.\n");
	
	# Exit Genre Table
	disconnectFromDB($genreFile);
	
	# Write Genre & Keywords To Database
	$genreOccArray = $movieGenreArray;
	foreach(array_keys($vgameGenreArray) as $vgameGenreKey) {
		if(array_key_exists($vgameGenreKey, $genreOccArray)) {
			$genreOccArray[$vgameGenreKey] += $vgameGenreArray[$vgameGenreKey];
		} else {
			$genreOccArray[$vgameGenreKey] = $vgameGenreArray[$vgameGenreKey];
		}
	}
	foreach(array_keys($bookGenreArray) as $bookGenreKey) {
		if(array_key_exists($bookGenreKey, $genreOccArray)) {
			$genreOccArray[$bookGenreKey] += $bookGenreArray[$bookGenreKey];
		} else {
			$genreOccArray[$bookGenreKey] = $bookGenreArray[$bookGenreKey];
		}
	}
	$genreOccFile = connectToDB('genreOcc.csv', 'w');
	
	print_r($genreOccArray);
	
	writeLinetoDB($genreOccFile, ['GENRE', 'OCCURANCE']);
		
	foreach(array_keys($genreOccArray) as $genre) {
		writeLinetoDB($genreOccFile, [$genre, $genreOccArray[$genre]]);
	}
?>