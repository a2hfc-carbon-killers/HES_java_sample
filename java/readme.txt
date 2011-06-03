======================================
=  Home Energy Saver API Sample Code =
=           for Java                 =
======================================
=  Note: You will need to obtain a   =
=  3scale key to use the api, once   =
=  you have the key place it where   =
=  you see the text "INSERT YOUR     =
=  3SCALE Key HERE"                  =
======================================

The sample code is a series of methods 
(they are separate code blocks, commented out)
the first is uncommented and will create a new session, 
you will need to get the session id to use in the 
following code blocks which are commented out

Code blocks in this sample
1. callnewsession = create a new session
2. callretrievesessionbyid11 = retrieve all the session information
3. callsaveSession11 = save session data to the database with validate 
4. callretrieveSummarySessionResults11 = retrieve calc summary data
The results are just output to the console

the final method is callAPIViaSOAP, which does the actual SOAP Call.