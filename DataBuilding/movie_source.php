<?php
	# movie_source.php
	//$dataSourcePath = "..\\..\\dataSources\\";
	
	$movieGenreArray = array();
	$movieKeywordArray = array();
	
	$movieSourcesConnArray = array();
	
	# Functions
	function openMovieSourceConn() {
		global $dataSourcePath, $movieSourcesConnArray;
		$path = $dataSourcePath . 'title.basics.tsv\data.tsv';
		print($path);
		$movieSourcesConnArray["IMDB"] = connectToDB($path, 'r');
	}
	
	function parseMovieData() {
		global $movieSourcesConnArray, $movieGenreArray, $movieKeywordArray;
		
		$rdb = $movieSourcesConnArray["IMDB"];
		$wmddb = connectToDB('media.csv', 'w');
		$wmvdb = connectToDB('movie.csv', 'w');
		
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
				
				writeLinetoDB($wmddb,
					[rtrim($exline[0]),
					 rtrim($exline[2]),
					 rtrim($exline[5]),
					 '2',
					 'NA']);
				
				writeLinetoDB($wmvdb,
					[rtrim($exline[0]),
					 rtrim($exline[7]),
					 'NA',
					 'NA']);
			}
		}
		
		disconnectFromDB($wmddb);
		disconnectFromDB($wmvdb);
	}
	
	function closeMovieSourceConn() {
		global $movieSourcesConnArray;
		disconnectFromDB($movieSourcesConnArray["IMDB"]);
		$movieSourcesConnArray["IMDB"] = NULL;
	}
?>