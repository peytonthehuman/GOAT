<?php
# book_source.php
	//$dataSourcePath = "..\\..\\dataSources\\";
	
	$bookGenreArray = array();
	$bookKeywordArray = array();
	
	$wmddb;
	$wbdb;
	
	$bookSourcesConnArray = array();
	
	# Functions
	function openBookSourceConn() {
		global $dataSourcePath, $bookSourcesConnArray, $wmddb, $wbdb;
		
		for($i = 1; $i < 42; $i++) {
			$iterString = '';
			if($i < 10) {
				$iterString = '0' . (string)$i;
			} else {
				$iterString = (string)$i;
			}
			$path = $dataSourcePath . 'Books\\book.p' . $iterString . '.xml';
			print($path);
			$bookSourcesConnArray[$iterString] = connectToDB($path, 'r');
		}
		$wmddb = connectToDB($dataSourcePath . 'media.csv', 'a');
		$wbdb = connectToDB($dataSourcePath . 'book.csv', 'w');
	}
	
	function parseNextXMLRecordAsBook(&$file) {
		$tempRecordString = readNextLineFromDB($file);
		if($tempRecordString == FALSE) {
			return -1;
		}
		$recordString = $tempRecordString;
		if(rtrim($recordString) != "<record>") {
			throw new Exception("Record Misalignment:\n" . $recordString, 42);
		}
		
		do {
			$tempRecordString = readNextLineFromDB($file);
			$recordString .= $tempRecordString;
		} while(rtrim($tempRecordString) !== "</record>");
		
		$recordXML = simplexml_load_string($recordString, "SimpleXMLElement", LIBXML_ERR_FATAL | LIBXML_PARSEHUGE);
		
		$leader = $recordXML->leader;
		
		if((substr($leader, 6, 1) == 'a'
		 or substr($leader, 6, 1) == 't') and (substr($leader, 7, 1) == 'a'
											or substr($leader, 7, 1) == 'c'
											or substr($leader, 7, 1) == 'd'
											or substr($leader, 7, 1) == 'm')
			and (substr($recordXML->controlfield[3], 35, 3) == 'eng')) {
			//and (substr($leader, 17, 1) == ' ')) {
			// ===============================================================
			$retObj = array();
			$retObj["rawObj"] = $recordXML;
			
			$genreArray = array();
			
			$cont008 = (string)$recordXML->controlfield[3];
			for($i = 24; $i <= 27; $i++) {
				switch($cont008[$i]) {
					case 'a':
						return 0; // Maybe remove?
						array_push($genreArray, "Summary");
						break;
					case 'b':
						return 0; // Maybe remove?
						array_push($genreArray, "Bibliography");
						break;
					case 'c':
						return 0; // Maybe remove?
						array_push($genreArray, "Catalog");
						break;
					case 'd':
						return 0; // Maybe remove?
						array_push($genreArray, "Dictionary");
						break;
					case 'e':
						return 0; // Maybe remove?
						array_push($genreArray, "Encyclopedia");
						break;
					case 'f':
						return 0; // Maybe remove?
						array_push($genreArray, "Handbook");
						break;
					case 'g':
						return 0; // Maybe remove?
						array_push($genreArray, "Legal article");
						break;
					case 'i':
						return 0; // Maybe remove?
						array_push($genreArray, "Index");
						break;
					case 'j':
						return 0; // Maybe remove?
						array_push($genreArray, "Patent");
						break;
					case 'k':
						return 0; // Maybe remove?
						array_push($genreArray, "Discography");
						break;
					case 'l':
						return 0; // Maybe remove?
						array_push($genreArray, "Legislation");
						break;
					case 'm':
						return 0; // Maybe remove?
						array_push($genreArray, "Thesis");
						break;
					case 'n':
						return 0; // Maybe remove?
						array_push($genreArray, "Survey");
						break;
					case 'o':
						return 0; // Maybe remove?
						array_push($genreArray, "Review");
						break;
					case 'p':
						return 0; // Maybe remove?
						array_push($genreArray, "Programmed Texts");
						break;
					case 'q':
						array_push($genreArray, "Filmography");
						break;
					case 'r':
						return 0; // Maybe remove?
						array_push($genreArray, "Directory");
						break;
					case 's':
						return 0; // Maybe remove?
						array_push($genreArray, "Statistics");
						break;
					case 't':
						return 0; // Maybe remove?
						array_push($genreArray, "Technical Report");
						break;
					case 'u':
						return 0; // Maybe remove?
						array_push($genreArray, "Standards");
						break;
					case 'v':
						return 0; // Maybe remove?
						array_push($genreArray, "Legal Case");
						break;
					case 'w':
						return 0; // Maybe remove?
						array_push($genreArray, "Law Report");
						break;
					case 'y':
						return 0; // Maybe remove?
						array_push($genreArray, "Yearbook");
						break;
					case 'z':
						return 0; // Maybe remove?
						array_push($genreArray, "Treaty");
						break;
					case '6':
						array_push($genreArray, "Comic/Graphic Novel");
						break;
				}
			}
			
			switch($cont008[33]) {
				case '0':
					array_push($genreArray, "Non fiction");
					break;
				case '1':
					array_push($genreArray, "Fiction");
					break;
				case 'd':
					array_push($genreArray, "Drama");
					break;
				case 'e':
					array_push($genreArray, "Essay");
					break;
				case 'f':
					array_push($genreArray, "Novel");
					break;
				case 'h':
					array_push($genreArray, "Humor");
					break;
				case 'i':
					array_push($genreArray, "Letter");
					break;
				case 'j':
					array_push($genreArray, "Short Story");
					break;
				case 'm':
					array_push($genreArray, "Mixed");
					break;
				case 'p':
					array_push($genreArray, "Poetry");
					break;
				case 's':
					array_push($genreArray, "Speech");
					break;
			}
			
			/*
			 22 - Target audience
         # - Unknown or not specified
         a - Preschool
         b - Primary
         c - Pre-adolescent
         d - Adolescent
         e - Adult
         f - Specialized
         g - General
         j - Juvenile
		 */
			switch($cont008[22]) {
				case 'a':
					array_push($genreArray, "Preschool");
					break;
				case 'b':
					array_push($genreArray, "Primary School");
					break;
				case 'c':
					array_push($genreArray, "Pre-adolescent");
					break;
				case 'd':
					array_push($genreArray, "Adolescent");
					break;
				case 'e':
					array_push($genreArray, "Adult");
					break;
				case 'f':
					return 0; // Maybe remove?
					array_push($genreArray, "Specialized");
					break;
				case 'g':
					array_push($genreArray, "General");
					break;
				case 'j':
					array_push($genreArray, "Juvenile");
					break;
			}
			
			foreach($recordXML->datafield as $dataRecord) {
				// ISBN or other ID
				if($dataRecord['tag'] == '020') {
					if(strlen((string)$dataRecord->subfield[0]) < 10) {
						return 0;
					}
					$retObj["id"] = (string)$dataRecord->subfield[0];//substr($dataRecord->subfield[0], 0, 10);
				}
				
				// Title
				if($dataRecord['tag'] == '245') {
					$title = $dataRecord->subfield[0] . ' ' . $dataRecord->subfield[1];
					$pos = stripos($title, '/');
					if($pos !== FALSE) {
						$title = substr($title, 0, $pos);
					}
					$retObj["title"] = $title;
				}
				
				// Author
				if($dataRecord['tag'] == '100') {
					$retObj["author"] = $dataRecord->subfield[0][0];
				} elseif($dataRecord['tag'] == '700' and ($dataRecord['ind1'] == 0 or $dataRecord['ind1'] == 1)) {
					$retObj["author"] = $dataRecord->subfield[0][0];
				}
				
				$retObj["author"] = rtrim(rtrim($retObj["author"], ','));
				// Genre Array
				/*if($dataRecord['tag'] == '600' or
				   $dataRecord['tag'] == '610' or
				   $dataRecord['tag'] == '611' or
				   $dataRecord['tag'] == '630' or
				   $dataRecord['tag'] == '648' or
				   $dataRecord['tag'] == '650' or
				   $dataRecord['tag'] == '651' ) {
					// =============================
					foreach($dataRecord->subfield as $entry) {
						array_push($genreArray, rtrim($entry, '.'));
					}
				}*/
				
				// Keyword Array
				
				//$retObj["keywords"] = $keywordArray;
				
				// Release Date
				if($dataRecord['tag'] == '260') {
					$date = $dataRecord->subfield[count($dataRecord->subfield) - 1];
					$dateArray = array();
					preg_match_all('/[0-9]{4}/', $date, $dateArray, PREG_PATTERN_ORDER);
					$date = 9999;
					$dateArray = $dateArray[0];
					foreach($dateArray as $indDate) {
						if($indDate < $date) {
							$date = $indDate;
						}
					}
					$retObj["releaseDate"] = $date;
				}
			}
			
			$retObj["genres"] = array_unique($genreArray);
			
			return $retObj;
		} else {
			return 0;
		}
	}
	
	function writeBook($id, $title, $release, $author, $numPages, $genreArray, $keywordArray) {  // Fix these params
		global $wmddb, $wbdb, $genreFile, $index;
		writeLinetoDB($wmddb,
			[$index,
			 rtrim($id),
			 rtrim($title),
			 rtrim($release),
			 '1',
			 'NA']);
		
		writeLinetoDB($wbdb,
			[$index,
			 rtrim($id),
			 rtrim($author),
			 $numPages]);
			 
		foreach($genreArray as $genre) {
			writeLinetoDB($genreFile, [$index, $id, $genre]);
		}
		
		$index++;
	}
	
	function parseAllBookCollections() {
		global $bookSourcesConnArray, $bookGenreArray;
		
		$i = 0;
		
		foreach($bookSourcesConnArray as $collection) {
			parseBookData($collection);
			$i += 1;
			print($i);
			print("\n");
		}
		
		print_r($bookGenreArray);
	}
	
	function parseBookData(&$rdb) {
		global $bookGenreArray, $bookKeywordArray, $wmddb, $wbdb;
		
		print($rdb);
		
		$totalEntries = 0;
		$numBooks = 0;
		$rejEntries = 0;
		
		$cont = TRUE;
		$recoverFromExcept = FALSE;
		
		$modVal = 10000;
		$numCollect = 0;
		
		do {
			try {
				$ret = parseNextXMLRecordAsBook($rdb);
			} catch(Exception $e) {
				if($e->getCode() == 42) {
					print($e->getMessage());
					print("\nReading Next Line...\n");
					$ret = 0;
				} else {
					throw $e;
				}
			} finally {
				if($ret === -1) {
					print("Total num of entries is: " . $totalEntries . "\n");
					print("Number of books is: " . $numBooks . "\n");
					print("Num of reject entries is: " . $rejEntries . "\n");
					$cont = FALSE;
				} elseif($ret === 0) {
					$recoverFromExcept = FALSE;
					$rejEntries ++;
				} else {
					$recoverFromExcept = FALSE;
					$numBooks ++;
					
					foreach($ret["genres"] as $genre) {
						if(!(array_key_exists($genre, $bookGenreArray))) {
							$bookGenreArray[$genre] = 1;
						} else {
							$bookGenreArray[$genre] = $bookGenreArray[$genre] + 1;
						}
					}
					
					writeBook($ret["id"],
							  $ret["title"],
							  $ret["releaseDate"],
							  $ret["author"], 0,
							  $ret["genres"], 0);
				}
				
				if($ret != -1) {
					$totalEntries ++;
				}
				
				if($totalEntries % $modVal === 0) {
					print(number_format($totalEntries) . " processed...\n");
					print("\tNum Books: " . number_format($numBooks) . "\n");
				}
			}
		} while($cont);
	}
	
	function closeBookSourceConn() {
		global $bookSourcesConnArray, $wmddb, $wbdb;
		
		foreach($bookSourcesConnArray as $source) {
			disconnectFromDB($source);
			$source = NULL;
		}
		
		disconnectFromDB($wmddb);
		$wmddb = NULL;
		disconnectFromDB($wbdb);
		$wbdb = NULL;
	}
?>