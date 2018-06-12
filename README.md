# ProjectBid
Marketplace for Self-Employed
Exercise Difficulty:  Moderate
How did you feel about the exercise itself? (7)
How do you feel about coding an exercise as a step in the interview process? (9)
What would you change in the exercise and/or process? None

APIs for inserting and retrieval of Buyer, Seller, Projects and Bidding for projects using Spring Boot and Java. The web service uses in-memory data storage using H2 Database. The Unique ID Generator is marked as a @Component which allows the Spring Boot to recognize the generator class as an injectable coponent.
************************
Controller: http://localhost:8080/buyers
************************

JSON Request body
	{
		"bName": "ram"
	}

GET
/buyers
Obtains the list of all buyers currently created in the system. If the list is successfully obtained, the list of existing reservations is returned, along with an HTTP 200(OK) status.

POST
/buyers
Creates a new buyer. This request should carry a request body that includes the data that should be associated with the newly created buyer. If the buyer is created, an HTTP 200 (OK) status is returned along with the message "Buyer has been added" in the response body.

GET
/buyers/{id}
Obtains the buyer associated with the given ID. If the buyer can be found, an HTTP 200 status is returned and the response body contains the information associated with the buyer.

************************
Controller: http://localhost:8080/sellers
************************

JSON Request body
	{
		"sName": "ram"
	}

GET
/sellers
Obtains the list of all sellers currently created in the system. If the list is successfully obtained, the list of existing sellers is returned, along with an HTTP 200(OK) status.

POST
/sellers
Creates a new sellers. This request should carry a request body that includes the data that should be associated with the newly created sellers. If the sellers is created, an HTTP 200 (OK) status is returned along with the message "Seller has been added" in the response body.

GET
/sellers/{id}
Obtains the seller associated with the given ID. If the seller can be found, an HTTP 200 status is returned and the response body contains the information associated with the seller.
************************
Controller: http://localhost:8080/projects
************************

JSON Request body
    {
        "pName": "test",
        "pDesc": "test",
        "maxBudget": 1000.00,
        "pTimeLimit": "2018-12-17",
        "sID": 1
        
    }

GET
/projects
Obtains the list of all project currently created in the system. If the list is successfully obtained, the list of existing projects is returned, along with an HTTP 200(OK) status.

POST
/projects
Creates a new project. This request should carry a request body that includes the data that should be associated with the newly created projects. If the project is created, an HTTP 200 (OK) status is returned along with the int value 1 in the response body.

GET
/projects/{id}
Obtains the project associated with the given ID. If the project can be found, an HTTP 200 status is returned and the response body contains the information associated with the project and the minimum bid amount. The status of the project is displayed based on the date. If the current date is after the time limit of the project the project-status changes to "closed" and the buyer with the minimum bid wins the project.
************************
Controller: http://localhost:8080/backendtechassessment/bids
************************

JSON Request body
	{
        "bAmount": 15,
        "buyID": 0,
        "projID": 1  
    }

GET
/backendtechassessment/bids
Obtains the list of all bids currently created in the system. If the list is successfully obtained, the list of existing bids is returned, along with an HTTP 200(OK) status.

POST
/backendtechassessment/bids
Creates a new bid. This request should carry a request body that includes the data that should be associated with the newly created bid. If the bids is created, an HTTP 200 (OK) status is returned along with the int value 1 in the response body.

GET
/backendtechassessment/bids/{id}
Obtains the bid associated with the given ID. If the bid can be found, an HTTP 200 status is returned and the response body contains the information associated with the bid.
************************
Controller: http://localhost:8080/backendtechassessment/autoBid/
************************
POST
/backendtechassessment//autoBid/Project_ID/Buyer_ID

Creates a new bid. This request should carry the project id and buyer id as the path variables for which the bid is being inserted. If the bid is created, an HTTP 200 (OK) status is returned along with the int value 1 in the response body.
The auto-bid retrieves the minimum bid amount for the given project ID and calculates a random amount which is less than the existing minimum bid amount.

************************
A Copy of the Maven can be downloaded from https://maven.apache.org/install.html
To start maven navigate to the path where maven is downloaded.
Export the location to the PATH variable
MAC: export PATH=/{local directory}apache-maven-3.5.3/bin:$PATH
Windows: Set the PATH in the Environment variables
Execute the following command in Terminal/Command prompt:
mvn spring-boot:run

Navigate to the URL (Controller info above) in your local browser.

For inserting new data download a rest client and make sure the request body format is set as JSON(application/json)