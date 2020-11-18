<?php
# vgame_source.php
	//$dataSourcePath = "..\\..\\dataSources\\";
	
	$vgameGenreArray = array();
	$vgameKeywordArray = array();
	
	$wmddb;
	$wvgdb;
	
	$vgameSourcesConnArray = array();
	
	# Functions
	function openVGameSourceConn() {
		global $dataSourcePath, $vgameSourcesConnArray, $wmddb, $wvgdb;
		$path = $dataSourcePath . 'steam-data.tar\games.json';
		print($path);
		$vgameSourcesConnArray["Steam"] = connectToDB($path, 'r');
		$wmddb = connectToDB($dataSourcePath . 'media.csv', 'a');
		$wvgdb = connectToDB($dataSourcePath . 'vgame.csv', 'w');
	}
	
	function parseVgameJSON($str) {
		$rawObj = json_decode($str, FALSE);
		
		if($rawObj->success == FALSE) {
			return false;
		}
		
		$id = $rawObj->data->steam_appid;
		$platforms = array();
		if($rawObj->data->platforms->windows == 1) {
			array_push($platforms, "Windows");
		}
		if($rawObj->data->platforms->mac == 1) {
			array_push($platforms, "Mac");
		}
		if($rawObj->data->platforms->linux == 1) {
			array_push($platforms, "Linux");
		}
		$platforms = dataAsCSV($platforms);
		
		$tempGenreArray = $rawObj->data->genres;
		if($tempGenreArray == NULL) {
			$tempGenreArray = array();
		}
		$genreArray = array();
		foreach($tempGenreArray as $genreObj) {
			array_push($genreArray, $genreObj->description);
		}
		
		$type = $rawObj->data->type;
		
		$title = $rawObj->data->name;
		
		$release = explode(' ', $rawObj->data->release_date->date)[2];
		
		$retObj = array();
		$retObj["rawObj"] = $rawObj;
		$retObj["id"] = $id;
		$retObj["platforms"] = $platforms;
		$retObj["genres"] = $genreArray;
		$retObj["type"] = $type;
		$retObj["title"] = $title;
		$retObj["releaseDate"] = $release;
		
		return $retObj;
	}
	
	function writeVgame($id, $title, $release, $consoleArray, $genreArray, $keywordArray) {
		global $wmddb, $wvgdb, $genreFile, $index;
		writeLinetoDB($wmddb,
			[$index,
			 rtrim($id),
			 rtrim($title),
			 rtrim($release),
			 '3',
			 'NA']);
		
		writeLinetoDB($wvgdb,
			[$index,
			 rtrim($id),
			 rtrim($duration),
			 $consoleArray]);
			 
		foreach($genreArray as $genre) {
			writeLinetoDB($genreFile, [$index, $id, $genre]);
		}
		
		$index++;
	}
	
	function parseVgameData() {
		global $vgameSourcesConnArray, $vgameGenreArray, $vgameKeywordArray, $wmddb, $wvgdb;
		
		$rdb = $vgameSourcesConnArray["Steam"];
		
		readNextLineFromDB($rdb);
		/*
		$retObj["rawObj"] = $rawObj;
		$retObj["id"] = $id;
		$retObj["platforms"] = $platforms;
		$retObj["genres"] = $genreArray;
		$retObj["type"] = $type;
		$retObj["title"] = $title;
		$retObj["releaseDate"] = $release;
		*/
		while(($line = readNextLineFromDB($rdb)) !== false) {
			//print_r($line);
			$exline = parseVgameJSON($line);
			if($exline == FALSE) {
				continue;
			}
			if($exline["type"] == "game") {
				foreach($exline["genres"] as $genre) {
					if(!(array_key_exists($genre, $vgameGenreArray))) {
						$vgameGenreArray[$genre] = 1;
					} else {
						$vgameGenreArray[$genre] = $vgameGenreArray[$genre] + 1;
					}
				}
				
				writeVgame($exline["id"],
						   $exline["title"],
						   $exline["releaseDate"],
						   $exline["platforms"],
						   $exline["genres"], 0);
			}
		}
	}
	
	function closeVgameSourceConn() {
		global $vgameSourcesConnArray, $wmddb, $wvgdb;
		disconnectFromDB($vgameSourcesConnArray["Steam"]);
		$vgameSourcesConnArray["Steam"] = NULL;
		disconnectFromDB($wmddb);
		$wmddb = NULL;
		disconnectFromDB($wvgdb);
		$wvgdb = NULL;
	}
?>