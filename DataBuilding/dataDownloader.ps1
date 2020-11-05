$bookURL = "https://www.loc.gov/cds/downloads/MDSConnect/BooksAll.2016.part"
$bookURLExt = ".xml.gz"

$dataDir = "C:\Users\pvanh\Documents\NetBeansProjects\dataSources\Books"

$writtenFileName = "books.p"

$ProgressPreference = 'silentlyContinue'

$7ZipPath = "$env:ProgramFiles\7-Zip\7z.exe"

if(-not (Test-Path -Path $7ZipPath -PathType Leaf)) {
    throw "7-zip file not found"
}

Set-Alias 7z $7ZipPath

for($i = 1; $i -lt 42; $i++) {
    $interString = ""
    if($i -lt 10) {
        $iterString = "0" + $i.ToString()
    } else {
        $iterString = $i.ToString()
    }
    $fullURL = "$bookURL$iterString$bookURLExt"
    echo $fullURL

    $dirToFile = "$dataDir\$writtenFileName$iterString"
    $dirToXML = "$dirToFile.xml"
    $dirToDown = "$dirToFile.xml.gz"
    echo $dirToTest

    if(Test-Path -Path $dirToXML) {
        $resultString = $iterString + " Found!"
        echo $resultString
        if(Test-Path -Path $dirToDown) {
            Remove-Item "$dirToDown"
        }

    } else {
        if(-Not (Test-Path -Path $dirToDown)) {
            $resultString = $iterString + " Not Found!"
            echo $resultString

            Invoke-WebRequest $fullURL -OutFile $dirToDown
        }

        7z e "-o$dirToXML.f" "$dirToDown"

        Move-Item -Path "$dirToFile.xml.f\Books.All.2016.part$iterString.xml" -Destination "$dirToXML"
        Remove-Item "$dirToFile.xml.f" -Recurse
        Remove-Item "$dirToDown"
    }

    Start-Sleep -Seconds 5
}

$ProgressPreference = 'Continue'