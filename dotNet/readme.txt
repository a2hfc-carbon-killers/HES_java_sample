======================================
=  Home Energy Saver API Sample Code =
=           for C#.NET               =
======================================


NOTE: The original sample client code was created for php5 and so that sample and documentation should be reviewed for greater detail on the methods.

First you will need to create autogenerated classes for .net to use generated from the WSDLs. In this directory you will see a BAT file named svcutil.bat. Run this file to update the classes that the program.cs file will use to do the work. The svcutil.bat file is setup to use the SANDBOX wsdl urls:

http://tool-sb-api.hescloud.net/session/wsdl
http://tool-sb-api.hescloud.net/calculate/wsdl

You will need to contact LBNL to get the urls for the production wsdls. You will also need a 3scale client key, and DOE CA Number to use. Once you receive those you can paste them in the appropriate places in the program.cs file in this Vistual Studio 2008 project and then build and run.

The svcutil.bat file will place the class files in calculate and session folders in the project, and will also include sample config files. The main app.config file in this project was made by combining those 2 from the 2 wsdls. See the app.config file for comments related to making it work.