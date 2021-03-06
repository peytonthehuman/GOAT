<?php

	// Call repeatedly
	// Will return 0 when it reaches the end of the CSV files
	// Otherwise will return an array of values from CSV with named keys:
	// Array Structure:
	//		"Media_Id"
	//		"Title"
	//		"Date_Released"
	//		"Media_Type"
	//		"Secondary" ->
	//			// If Media_type == 1
	//			"Author"
	//			"Num_Of_Pages"
	//			// If Media_Type == 2
	//			"Duration"
	//			"Producers"
	//			"Actors"
	//			// If Media_Type == 3
	//			"Console"
	//		"Genres"
	//			0, 1, 2
	
	function readEntryFromCSVs($index) {
		$dataPath = "C:\\Users\\Peyton\\Documents\\NetBeansProjects\\dataSources\\";
		$media = fopen($dataPath . "media.csv", 'r');
		$genre = fopen($dataPath . "genre.csv", 'r');
		$movie = fopen($dataPath . "movie.csv", 'r');
		$book  = fopen($dataPath . "book.csv", 'r');
		$vgame = fopen($dataPath . "vgame.csv", 'r');
		
		for($i = 0; $i < $index; $i ++) {
			fgets($media);
		}
		
		$entry = fgetcsv($media, 0, '|');
		
		$retVal = array();
		
		$retVal["Media_Id"] = $entry[1];
		$retVal["Title"] = $entry[2];
		$retVal["Date_Released"] = $entry[3];
		$retVal["Media_Type"] = $entry[4];
		
		// 1 = books
		// 2 = movies
		// 3 = vgames
		if($entry[4] == 1) {
			// Fix Media_Id
			$indEntry = seekToIndex($book, $index);
			if($indEntry == false) {
				throw new Exception("Entry " . $index . " not found in book database.");
			}
			$retVal["Secondary"]["Author"] = $indEntry[2];
			$retVal["Secondary"]["Num_Of_Pages"] = $indEntry[3];
		} else if($entry[4] == 2) {
			$indEntry = seekToIndex($movie, $index, 25); // will need to fix
			if($indEntry == false) {
				throw new Exception("Entry " . $index . " not found in movie database.");
			}
			$retVal["Secondary"]["Duration"] = $indEntry[2];
			$retVal["Secondary"]["Producers"] = $indEntry[3];
			$retVal["Secondary"]["Actors"] = $indEntry[4];
		} else if($entry[4] == 3) {
			$indEntry = seekToIndex($vgame, $index, 30); // Will need to fix
			if($indEntry == false) {
				throw new Exception("Entry " . $index . " not found in vgame database.");
			}
			$retVal["Secondary"]["Console"] = $indEntry[2];
		}
		
		$line = seekToIndex($genre, $index);
		if($line == false) {
			throw new Exception("Entry " . $index . " not found in genre table.");
		} else {
			$genreIdx = 0;
			do {
				$retVal["Genres"][$genreIdx] = $line[2];
				$line = fgetcsv($genre, 90, '|');
				$genreIdx++;
			} while($line[0] == $index);
		}
		
		fclose($media);
		fclose($genre);
		fclose($movie);
		fclose($book);
		fclose($vgame);
		
		return $retVal;
	}
	
	function seekToIndex($file, $index, $length = 0, $col = 0) {
		while(true) {
			$array = fgetcsv($file, $length, '|');
			if($array == false) {
				return false;
			} else if($array[$col] == $index) {
				return $array;
			}
		}
	}
?>