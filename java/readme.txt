========================================================
=       Home Energy Scoring Tool API Sample Code       =
=                     for Java                         =
========================================================
=  Note: You will need to obtain a 3scale key to use   =
=  the api, once you have the key place it where you   =
=  see the text "INSERT YOUR_3SCALE_KEY_HERE"          =
=                                                      =
=  You will need to obtain a Certified Assesor (CA)    =
=  Number also. Place this where you see the text      =
=  "INSERT_YOUR_CERTIFIED_ASSESOR_NUMBER_HERE"         =
========================================================

The sample code uses JAX-WS for SOAP communication. *** NOTE: THE UPDATED WSDL/API WILL BE AVAILABLE AFTER 06/13 ***

You will need to run the following commands to auto-generate HES SOAP libraries from WSDL:

  cd <your_project_folder>/src
  wsimport -keep -verbose -b binding.xml http://tool-sb-api.hescloud.net/calculate/wsdl
  wsimport -keep -verbose -b binding.xml http://tool-sb-api.hescloud.net/session/wsdl
  
The binding.xml can be found in this folder, this simply has intructions on how wsimport should handle underscores (necessary for the build to work).

Once these commands have completed, you will have two new libraries:

  gov.lbl.hes.scoring_tool.session.*
  gov.lbl.hes.scoring_tool.calculate.*
=====================================================================

The following methods are called in this sample code:

1. newSessionFromAddress()      = creates a new session
2. saveLabelSession()           = saves inputs to the database with validation and runs the calc (assuming the "validate" parameter is set to 1)
3. retrieveLabelDetailResults() = retrieves the calculated results
4. generateHesLabel()           = generates a label and returns an array of urls (original multi-page pdf, and seperate individual pages)

The results and label urls are just outputted to the console.

