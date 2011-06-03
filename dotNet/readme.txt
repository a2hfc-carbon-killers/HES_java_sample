======================================
=  Home Energy Saver API Sample Code =
=           for C#.NET               =
======================================
=  Note: You will need to obtain a   =
=  3scale key to use the api, once   =
=  you have the key place it where   =
=  you see the text "INSERT YOUR     =
=  3SCALE Key HERE"                  =
======================================

First step you will need to create 
api_SOAP_Layer.cs file that will be added 
into the project.

use wsdl path: http://sbapp.hescloud.net/session/wsdl/
step 1. Microsoft Visual Studio provides a utilitly called "wsdl.exe" that reads the webservice wsdl and creates the CS objects
      for me it was in 6.0a SDK, I created a batch folder off of c:\ and put these lines in and ran apiwsdl.bat

      batch file c:\bats\apiwsdl.bat
      --------------------------------               
      cd C:\Program Files\Microsoft SDKs\Windows\v6.0A\Bin\
      wsdl.exe (See use wsdl path above) /language:CS /out:"C:\Users\Ben Johansen\Documents\Visual Studio 2008\Projects\HESCSharpSample\HESCSharpSample\api_SOAP_Layer.cs" /protocol:SOAP
      cd c:\bats
  
      the file api_SOAP_Layer.cs is now in my project folder

 step 2. Right click on project and select Add->Existing Item, find api_SOAP_Layer.cs and select it then press Add.

 step 3. Finally, System.Web.Services must be included in the project. 
         to do this, right click on References and select "Add Reference..." and locate "System.Web.Services" and click OK

      There is a visual explanation of this under section "Using wsdl.exe" at http://sldn.softlayer.com/wiki/index.php/C_Sharp



The sample code is a series of methods 
(they are separate code blocks, commented out)
the first is uncommented and will create a new session, 
you will need to get the session id to use in the 
following code blocks which are commented out

Code blocks in this sample
1. newSession= create a new session
2. retrieveSessionById11 = retrieve all the session information
3. saveSession11 = save session data to the database with validate 
4. retrieveSummarySessionResults11 = retrieve calc summary data
The results are just output to the console

