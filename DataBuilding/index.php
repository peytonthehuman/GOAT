<?php
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
	}
?>