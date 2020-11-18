<?php
	# movie_source.php
	//$dataSourcePath = "..\\..\\dataSources\\";
	
	$movieGenreArray = array();
	$movieKeywordArray = array();
	
	$wmddb;
	$wmvdb;
	
	$movieSourcesConnArray = array();
	
	# Functions
	function openMovieSourceConn() {
		global $dataSourcePath, $movieSourcesConnArray, $wmddb, $wmvdb;
		$path = $dataSourcePath . 'title.basics.tsv\data.tsv';
		print($path);
		$movieSourcesConnArray["IMDB"] = connectToDB($path, 'r');
		$wmddb = connectToDB($dataSourcePath . 'media.csv', 'w');
		$wmvdb = connectToDB($dataSourcePath . 'movie.csv', 'w');
	}
	
	function writeMovie($id, $title, $release, $duration, $prodArray, $actArray, $genreArray, $keywordArray) {
		global $wmddb, $wmvdb, $genreFile, $index;
		writeLinetoDB($wmddb,
			[$index,
			 rtrim($id),
			 rtrim($title),
			 rtrim($release),
			 '2',
			 'NA']);
		
		writeLinetoDB($wmvdb,
			[$index,
			 rtrim($id),
			 rtrim($duration),
			 'NA',
			 'NA']);
			 
		foreach($genreArray as $genre) {
			writeLinetoDB($genreFile, [$index, $id, $genre]);
		}
		
		$index++;
	}
	
	function parseMovieData() {
		global $movieSourcesConnArray, $movieGenreArray, $movieKeywordArray, $wmddb, $wmvdb;
		
		$rdb = $movieSourcesConnArray["IMDB"];
		
		readNextLineFromDB($rdb);
		
		while(($line = readNextLineFromDB($rdb)) !== false) {
			$exline = parseTSVLine($line);
			
			if(($exline[4] !== '1') && ($exline[1] == 'movie')) {
				$tempGenreList = parseCSVLine($exline[8]);
				foreach($tempGenreList as $genre) {
					if(!(array_key_exists($genre, $movieGenreArray))) {
						$movieGenreArray[$genre] = 1;
					} else {
						$movieGenreArray[$genre] = $movieGenreArray[$genre] + 1;
					}
				}
				
				writeMovie($exline[0],
						   $exline[2],
						   $exline[5],
						   $exline[7],
						   0, 0, $tempGenreList, 0);
			}
		}
	}
	
	function closeMovieSourceConn() {
		global $movieSourcesConnArray, $wmddb, $wmvdb;
		disconnectFromDB($movieSourcesConnArray["IMDB"]);
		$movieSourcesConnArray["IMDB"] = NULL;
		disconnectFromDB($wmddb);
		$wmddb = NULL;
		disconnectFromDB($wmvdb);
		$wmvdb = NULL;
	}
?>