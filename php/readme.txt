========================================================
=       Home Energy Scoring Tool API Sample Code       =
=                     for PHP                          =
========================================================
=  Note: You will need to obtain a 3scale key to use   =
=  the api, once you have the key place it where you   =
=  see the text "INSERT YOUR_3SCALE_KEY_HERE"          =
=                                                      =
=  You will need to obtain a Certified Assesor (CA)    =
=  Number also. Place this where you see the text      =
=  "INSERT_YOUR_CERTIFIED_ASSESOR_NUMBER_HERE"         =
========================================================

The sample code uses PHP's native SoapClient() for SOAP communication. No additional libraries are required.

=====================================================================

The following methods are called in this sample code:

1. newSessionFromAddress()      = creates a new session
2. saveLabelSession()           = saves inputs to the database with validation and runs the calc (assuming the "validate" parameter is set to 1)
3. retrieveLabelDetailResults() = retrieves the calculated results
4. generateHesLabel()           = generates a label and returns an array of urls (original multi-page pdf, and seperate individual pages)

The results and label urls are just outputted to the browser.

