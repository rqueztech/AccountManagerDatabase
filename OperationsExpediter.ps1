#Open the powershell script in the directory of the .ps1 script
Set-Location $PSScriptRoot

#Prime the user input to start as a blank string
$userInput = ""

#Create a while loop that won't end until exit is typed as a prompt.
#This will allow the program to keep looping and make it easier to open
#.csv files to view contents/monitor potential errors
while($userInput -ne "exit") {
    Write-Host "`n`nProduction Increaserererer"
    Write-Host "----------------------------`n"
	Write-Host "1. Open Admin.csv Notepad"
	Write-Host "2. Open Database.csv Notepad"
	Write-Host "3. Open Config.csv Notepad"
	Write-Host "4. Delete all .csv Files"
    Write-Host "5. Print Current .csv Files"
    Write-Host "6. --> Open File Location In Explorer`n"
    Write-Host "----------------------------"

	#Prompt the user to enter which file they want to open
	$userInput = Read-Host "Enter Operation"

	#Create a try block to handle file not found exception
	try {

		switch($userInput) {
			#If the user enters 1, open the admin database
			"1" {
				
				#If the admindatabase.csv file is found, open it
				if(Test-Path .\AdminDatabase.csv) {
					#Start-Process "notepad" .\AdminDatabase.csv
                    type .\AdminDatabase.csv
				}
				
				#If the file is not found, throw an exception that the
				#file was not found
				else {
					throw "AdminDatabase.csv Not Found: $filePath"
				}
			}

			#If the user enters 2, open the database
			"2" {
				
				#If the database.csv file is found, open it
				if(Test-Path .\database.csv) {					#Start-Process "notepad" .\database.csv                    type .\database.csv				}
				#If the database file is not found, throw an exception that the
				#file was not found				else{					throw "database.csv Not Found: $filePath"
				}
			}

			"3" {
				
				#If the config file is found, open it
				if(Test-Path .\config.csv) {					#Start-Process "notepad" .\config.csv                    type .\config.csv				}
				#If the config.csv file is not found, throw an exception that the
				#file was not found				else{					throw "config.csv Not Found: $filePath"
				}
			}
			
			#If the user enters 4, delete ALL the .csv files in the current folder
			"4" {
				$deleteAllConfirmation = ""

				#While the user doesn't enter a valid answer
				while($deleteAllConfirmation -ne "Y" -and $deleteAllConfirmation -ne "N" -and
                      $deleteAllConfirmation -ne "n" -and $deleteAllConfirmation -ne "n") {
					#Print a Warning Message
					$deleteAllConfirmation = Read-Host "Are you sure you want to delete all csv files (Y/N)?"
					
                    #Switch statement to handle the deletion operation.
                    #Either y for deletion or n for cancellation.
					switch($deleteAllConfirmation) {
						"Y" {
                            Get-Childitem -filter "*.csv" | ForEach-Object {
                                Remove-Item $_.FullName
                                Write-Host "Deleted file: $($_.Name)"
                            }
					    }

                        "y" {
                            Get-Childitem -filter "*.csv" | ForEach-Object {
                                Remove-Item $_.FullName
                                Write-Host "Deleted file: $($_.Name)"
                            }
					    }

                        default {
                            Clear-Host
                            Write-Host "Enter Valid Input"
                        }
                    }
				}
			}

            "5" {
                Get-Childitem -filter "*.csv"
            }

            #If the user enters 6, open the file location in explorer
			"6" {
				
				#If the database.csv file is found, open it
				Start-Process "explorer" $PSScriptRoot
			}
		}
	}
	
	catch {
		Write-Host "An error occured:  $($_.Exception.Message)"
	}
	
	Write-Host "Press any key to continue"
	Read-Host
	
	Clear-Host
}