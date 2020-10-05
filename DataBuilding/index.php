<?php
	# Setup
	$dataSourcePath = "..\\..\\dataSources\\";
	
	$mediaArray = array();
	$movieArray = array();
	$bookArray = array();
	$vgameArray = array();
	
	$genreArray = array();
	$keywordArray = array();
	
	# includes
	include 'iDB.php';
	include 'movie_source.php';
	//include 'book_source.php';
	//include 'vgame_source.php';
	
	# Run Movie Code
	openMovieSourceConn();
	print(parseDataArrayLine(array()));
	closeMovieSourceConn();
	
	# Run Book Code
	
	# Run VGame Code
	
	# Write To Database
	
	/*
	
	$file = fopen("..\..\dataSources\imdb.tsv", 'r');
	$genrefile = fopen("genre.csv", 'w') or die("Unable to open file!");
	
	$genreArray = array();
	if($file) {
		fgets($file);
		while(($line = fgets($file)) !== false) {
			$exline = explode("	", $line);
			if(($exline[4] !== '1') && ($exline[1] == 'movie')) {
				$tempGenreList = explode(",", $exline[8]);
				foreach($tempGenreList as $genre) {
					$genreClean = str_replace("\n", "", $genre);
					if(!(array_key_exists($genreClean, $genreArray))) {
						$genreArray[$genreClean] = 1;
					} else {
						$genreArray[$genreClean] = $genreArray[$genreClean] + 1;
					}
				}
			}
		}
		
		fwrite($genrefile, 'GENRE,OCCURANCE' . PHP_EOL);
		
		foreach(array_keys($genreArray) as $genre) {
			$writeString = str_replace("\n", "", $genre);
			$writeString .= ',';
			$writeString .= $genreArray[$genre];
			fwrite($genrefile, $writeString);
			fwrite($genrefile, PHP_EOL);
		}
		
		fclose($genrefile);
		fclose($file);
	} else {
		print("error");
	}*/
	
	$test = connectToDB($dataSourcePath . "imdb.tsv", 'r');
	for($i = 0; $i < 5; $i++) {
		print_r(parseTSVLine(readNextLineFromDB($test)));
	}
?>