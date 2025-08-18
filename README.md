This is an End to End REST Assured Framework development project. Below is the tech stack and resources used:
Maven
TestNG
Global Properties to store API endpoints which were read using Properties/Resource bundle objects to access the .properties file
Additionally, I have also implemented Java Classes storing API endpoints as static variables which are being accessed in respective tests.


Walkthrough of Framework:
	1. PACKAGES:
		a. api.endpoints - this package will hold all your API Service Endpoints.
			i. API_Endpoints.java - This is a class where you can store all your url endpoints by creating public static variables. These variables holding the endpoints will be fetched by individual tests during execution for GET, POST, PUT DELETE etc operations. Note this java class is one of the ways to store your endpoints. You may use golbal.properties file as well to house all your endpoints.
			ii. Class1.java (Functionality 1) - Define functions for CRUD operations here. Define RestAssured operations given().when() here and return a response
			iii. Class2.java - (Functionality 2) - same as above
		b. api.payloads - Define your POJO classes here
		c. api.testcases - Write your @Test: Fetch testdata from data provider, setup data via POJO , import the responses from 1a and write your then() here with validations and assertions
		d. api.utilities - write all your reusable functions here.
			i. Excel/File data retrieval - using Apache POI
			ii. DataProvider utilities using 2D Object[][] accomodating all data types
			iii. Extent/Allure report utility
	2. FOLDERS
		a. Reports - 
		b. TestDataFiles - Excel based
	3. Src/test/resources
		a. Global.properties - this can hold your url endpoints and any common global variables which can be directly called using ResourceBundle object or Properties object
	4. POM.xml - Dependencies/Plugins used - testNG, rest assured, apache-poi, java-faker, maven-surefirep to name a few
	5. TestSuites.xmls
 6. Target > Stores all Maven execution reports
