#Steps to run in your lappys

## Fresh install

###Step 1: Delete \bin in \ELibrary25-26

###Step 2: Import the ELibrary25-26
1. Create a new Java Project if you don't have one dedicated already
2. Right click the project in the Package Explorer -> Import -> General -> File System -> select the downloaded project -> click Select All -> Finish
3. Right click the project in the Package Explorer -> Build Path -> Add External Archives -> select the .jar files for mssql and jfreechart
4. Right click \src in your project -> Properties -> select Java Build Path on the left panel -> select Source up on the ribbon -> click Browse on the bottom -> dropdown the project -> Create New Folder > name it "bin" -> Apply and Close
5. Select the project in the Package Explorer -> Project in the menu bar up top -> Clean -> select the project then clean

###Step 3: File Path Management
####Fonts
1. Right click one of the fonts under view.fonts -> Properties -> copy the Location until fonts\
2. Paste the copied string in the view.FilePath -> fontFilePath = ""

####Images
1. Right click one of the images under view.img -> Properties -> copy the Location until img\
2. Paste the copied string in the view.FilePath -> imgFilePath = ""


## Installing again
1. Delete the \src folder
2. Paste the new and lastest \src folder

###File Path Management
####Fonts
1. Right click one of the fonts under view.fonts -> Properties -> copy the Location until fonts\
2. Paste the copied string in the view.FilePath -> fontFilePath = ""

####Images
1. Right click one of the images under view.img -> Properties -> copy the Location until img\
2. Paste the copied string in the view.FilePath -> imgFilePath = ""
