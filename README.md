# CS-532-Team-Project
Team Project: Electro Bike Manager

### Software requirements:
IntelliJ IDEA Ultimate (free version does not build the project)
Java SDK 14
Groovy Version 2.5
Gradle Version 6

#### Opening the project
Git Clone this repository
Open IntelliJ
File > Open Project > Select the CS-532-Team-Project folder

#### Connect to database
Select Database tab on the right border
Click New > Data Source > MySQL
Name: @byerline.me
Host: byerline.me
Port: 3306
User: root
Password: adiumDB
URL: jdbc:mysql://byerline.me:3306
Click OK

#### Build the project
Wait for IntelliJ to index the files
Click Green Run 'BikeappApplication'
 * If the Run bitton does not appear, navigate to the src->main->groovy->com.electro.bikeapp
 * Open the BikeappApplication.groovy file and run the main class

#### Run the website
Go to any browser and access "localhost:8080"

#### Test accounts
##### Owner account
username: tester1
password: password
This account has access to every page

##### Manager account
username: tester2
password: password
This account has access to every page, except for owner's pages

