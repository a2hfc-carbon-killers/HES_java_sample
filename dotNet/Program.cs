//------------------------------------------------------------------------------
// HES API Sample Calls in C#.NET
// By: Ben Johansen (ben@bighead.net)
//
// To get started... 
// A. Create a C Sharp project in Visual Studio.Net Studio
// B. Include this file in the project
//   -- Right click on project and select Add->Existing Item, find Program.cs and select it then press Add.    
// C. Create the api_SOAP_Layer.cs file using this process
//
//      Prior to Using this Code you will need to build the api_SOAP_Layer.cs file from the wdsl and include it in your project
// 
//      use wsdl path: {obtain SOAP URL from HES}
//
//      step 1. Microsoft Visual Studio provides a utilitly called "wsdl.exe" that reads the webservice wsdl and creates the CS objects
//      for me it was in 6.0a SDK, I created a batch folder off of c:\ and put these lines in and ran apiwsdl.bat
//
//           batch file c:\bats\apiwsdl.bat
//           --------------------------------               
//           cd C:\Program Files\Microsoft SDKs\Windows\v6.0A\Bin\
//           wsdl.exe (See use wsdl path above) /language:CS /out:"C:\Users\Your Name Here\Documents\Visual Studio 2008\Projects\HESCSharpSample\HESCSharpSample\api_SOAP_Layer.cs" /protocol:SOAP
//           cd c:\bats
//  
//           the file api_SOAP_Layer.cs is now in my project folder
//
//      step 2. Right click on project and select Add->Existing Item, find api_SOAP_Layer.cs and select it then press Add.
//
//      step 3. Finally, System.Web.Services must be included in the project. 
//      to do this, right click on References and select "Add Reference..." and locate "System.Web.Services" and click OK
//
//      There is a visual explanation of this under section "Using wsdl.exe" at http://sldn.softlayer.com/wiki/index.php/C_Sharp
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;
using System.Xml.Serialization;
using System.Web.Services.Protocols;
using System.Web.Services;
using System.Reflection;


namespace HESCSharpSample 
{
    public class Program
    {

        //class global variables

        //INSERT YOUR 3SCALE GUID HERE
        private static String CLIENT_GUID = "INSERT YOUR 3SCALE KEY HERE";


        //SESSION_TYPE (0 = Quick Session, 1 = Detailed Session)
        private static int SESSION_TYPE = 0;

        //WEBSITE_TYPE (leave this as 0)
        private static int WEBSITE_TYPE = 0;

        //SESSION_ID
        private static int SESSION_ID = 1926003;

        //ZIPCODE
        private static String ZIPCODE = "98661";

        //VALIDATE (0 = just save, 1 = save and calc)
        private static int VALIDATE = 0;
         
        static void Main(string[] args)
            {
                if (args.Length < 1)
                {

                    //instanciate the sessionService
                    HesAPI_SessionService sessionService = new HesAPI_SessionService();

                    //-----------------------------
                    // Sample Create New Session By Zipcode
                    // newSession
                    // Note: The New session_id will be stored in the last entry in returned array in an encoded text array in sessionValue
                    // example: sessionValue = sessionId^1926003;uniqueName^;zipcode^98661;zipCity^Vancouver;zipState^Washington;calculated^0
                    //----------------------------
                    // ------ uncomment block below to run this sample ---------
                    
                    RetrieveSessionByIdResponse[] newResults = sessionService.newSession(CLIENT_GUID, ZIPCODE, WEBSITE_TYPE);
                    Console.WriteLine("New Session:");
                    Console.WriteLine("Session ID = " + newResults[(newResults.Length - 1)].sessionValue);
                     
                    //use reflection to iterate thru the values
                    foreach (RetrieveSessionByIdResponse RetrieveSessionByIdResponse in newResults)
                    {
                        Type type = typeof(RetrieveSessionByIdResponse);
                        PropertyInfo[] properties = type.GetProperties();
                        foreach (PropertyInfo propertyInfo in properties)
                        {
                            object srcValue = RetrieveSessionByIdResponse.GetType().InvokeMember(propertyInfo.Name, BindingFlags.GetProperty, null, RetrieveSessionByIdResponse, null);
                                if (srcValue != null)
                                {
                                    Console.WriteLine(propertyInfo.Name + ":" + srcValue.ToString());
                                }
                                else
                                {
                                    Console.WriteLine(propertyInfo.Name + ":");
                                }
                        }
                        Console.WriteLine();
                        //this is just here to stop between each record
                        Console.ReadLine();
                    }
                      
                    

                    //-----------------------------
                    // Sample Retrieve Session By Id
                    // retrieveSessionById11
                    //----------------------------
                    // ------ uncomment block below to run this sample ---------
                    /*
                    RetrieveSessionByIdResponse[] retrieveResults = sessionService.retrieveSessionById11(CLIENT_GUID, SESSION_ID, SESSION_TYPE, WEBSITE_TYPE);
                    Console.WriteLine("RetrieveSummarySessionResults11 Outputs:");

                    //use reflection to iterate thru the values
                    foreach (RetrieveSessionByIdResponse RetrieveSessionByIdResponse in retrieveResults)
                    {
                        Type type = typeof(RetrieveSessionByIdResponse);
                        PropertyInfo[] properties = type.GetProperties();
                        foreach (PropertyInfo propertyInfo in properties)
                        {
                            object srcValue = RetrieveSessionByIdResponse.GetType().InvokeMember(propertyInfo.Name, BindingFlags.GetProperty, null, RetrieveSessionByIdResponse, null);
                                if (srcValue != null)
                                {
                                    Console.WriteLine(propertyInfo.Name + ":" + srcValue.ToString());
                                }
                                else
                                {
                                    Console.WriteLine(propertyInfo.Name + ":");
                                }
                        }
                        Console.WriteLine();
                        //this is just here to stop between each record
                        Console.ReadLine();
                    }
                    */

                    //-----------------------------
                    // Sample Save Session
                    // saveSession11
                    //----------------------------
                    // ------ uncomment block below to run this sample ---------
                    /*
                       //create saveSession11 input_object data
                       //extraInputs
                       SaveSessionExtraInputs extraInputs = new SaveSessionExtraInputs();
                        extraInputs.controller = "consumer";
                        extraInputs.action = "save-and-go";
                        extraInputs.module = "default";
                        extraInputs.nextPage = "/consumer/quick-building-design";
                        extraInputs.nextTask = "next";
                        extraInputs.formGroup = "Quick";
                        extraInputs.formForm = "General";
                       //saveInputs
                       SaveSessionSaveInputs[] saveInputs = new SaveSessionSaveInputs[19];
                       saveInputs[0] = new SaveSessionSaveInputs();
                        saveInputs[0].inputTableName = "whole_house_input";
                        saveInputs[0].inputColumnName = "useTariff";
                        saveInputs[0].s_value = "no";
                       saveInputs[1] = new SaveSessionSaveInputs();
                        saveInputs[1].inputTableName = "whole_house_input";
                        saveInputs[1].inputColumnName = "uniqueName";
                        saveInputs[1].s_value ="java test";
                       saveInputs[2] = new SaveSessionSaveInputs();
                        saveInputs[2].inputTableName ="whole_house_input";
                        saveInputs[2].inputColumnName ="emailAddress";
                        saveInputs[2].s_value = "";
                       saveInputs[3] = new SaveSessionSaveInputs();
                        saveInputs[3].inputTableName ="whole_house_input";
                        saveInputs[3].inputColumnName ="purpose";
                        saveInputs[3].s_value = "0";
                       saveInputs[4] = new SaveSessionSaveInputs();
                        saveInputs[4].inputTableName = "whole_house_input";
                        saveInputs[4].inputColumnName ="purposeOther";
                        saveInputs[4].s_value = "";
                       saveInputs[5] = new SaveSessionSaveInputs();
                        saveInputs[5].inputTableName ="whole_house_input";
                        saveInputs[5].inputColumnName ="address";
                        saveInputs[5].s_value ="";
                       saveInputs[6] = new SaveSessionSaveInputs();
                        saveInputs[6].inputTableName ="whole_house_input";
                        saveInputs[6].inputColumnName ="city";
                        saveInputs[6].s_value ="Portland";
                       saveInputs[7] = new SaveSessionSaveInputs();
                        saveInputs[7].inputTableName ="whole_house_input";
                        saveInputs[7].inputColumnName ="stateCode";
                        saveInputs[7].s_value ="WA";
                       saveInputs[8] = new SaveSessionSaveInputs();
                        saveInputs[8].inputTableName ="whole_house_input";
                        saveInputs[8].inputColumnName ="weatherCity";
                        saveInputs[8].s_value ="Portland OR";
                       saveInputs[9] = new SaveSessionSaveInputs();
                        saveInputs[9].inputTableName ="whole_house_input";
                        saveInputs[9].inputColumnName ="year";
                        saveInputs[9].s_value ="1971";
                       saveInputs[10] = new SaveSessionSaveInputs();
                        saveInputs[10].inputTableName ="whole_house_input";
                        saveInputs[10].inputColumnName ="occupants_0_5";
                        saveInputs[10].s_value ="0";
                       saveInputs[11] = new SaveSessionSaveInputs();
                        saveInputs[11].inputTableName ="whole_house_input";
                        saveInputs[11].inputColumnName ="occupants_6_13";
                        saveInputs[11].s_value ="1";
                       saveInputs[12] = new SaveSessionSaveInputs();
                        saveInputs[12].inputTableName ="whole_house_input";
                        saveInputs[12].inputColumnName ="occupants_14_64";
                        saveInputs[12].s_value ="2";
                       saveInputs[13] = new SaveSessionSaveInputs();
                        saveInputs[13].inputTableName ="whole_house_input";
                        saveInputs[13].inputColumnName ="occupants_65_plus";
                        saveInputs[13].s_value ="0";
                       saveInputs[14] = new SaveSessionSaveInputs();
                        saveInputs[14].inputTableName ="whole_house_input";
                        saveInputs[14].inputColumnName ="priceElect";
                        saveInputs[14].s_value ="0.076";
                       saveInputs[15] = new SaveSessionSaveInputs();
                        saveInputs[15].inputTableName ="whole_house_input";
                        saveInputs[15].inputColumnName ="priceGas";
                        saveInputs[15].s_value ="1.31";
                       saveInputs[16] = new SaveSessionSaveInputs();
                        saveInputs[16].inputTableName ="whole_house_input";
                        saveInputs[16].inputColumnName ="priceLpg";
                        saveInputs[16].s_value ="2.38";
                       saveInputs[17] = new SaveSessionSaveInputs();
                        saveInputs[17].inputTableName ="whole_house_input";
                        saveInputs[17].inputColumnName ="priceOil";
                        saveInputs[17].s_value ="3.16";
                       saveInputs[18] = new SaveSessionSaveInputs();
                        saveInputs[18].inputTableName ="whole_house_input";
                        saveInputs[18].inputColumnName ="tariffId";
                        saveInputs[18].s_value ="ag-4";
                       //lightInputs
                       SaveSessionLightInputs[] lightInputs = new SaveSessionLightInputs[0];

                   SaveSessionInput input_object = new SaveSessionInput();
                    input_object.extraInputs = extraInputs;
                    input_object.saveInputs = saveInputs;
                    input_object.lightInputs = lightInputs;


                    //now call the webservice
                    SaveSessionResponse saveResults = sessionService.saveSession11(CLIENT_GUID, SESSION_ID, input_object, VALIDATE);

                    Console.WriteLine("SaveSession11 Outputs:");
                    Console.WriteLine("ReturnCode = " + saveResults.returnCode);
                    Console.WriteLine("ReturnText = " + saveResults.returnText);
                    
                    */

                    //-----------------------------
                    // Retrieve Session Summary Result 
                    // retrieveSummarySessionResults11
                    //----------------------------
                    // ------ uncomment block below to run this sample ---------
                    /*
                    RetrieveSessionSummaryResponse restrieveSummaryResults = sessionService.retrieveSummarySessionResults11(CLIENT_GUID, SESSION_ID);
                    Console.WriteLine("RetrieveSummarySessionResults11 Outputs:");
                    Console.WriteLine(restrieveSummaryResults.BaseCost);

                    //use reflection to iterate thru the values
                        Type type = typeof(RetrieveSessionSummaryResponse);
                        PropertyInfo[] properties = type.GetProperties();
                        foreach (PropertyInfo propertyInfo in properties)
                        {
                            String ptype = propertyInfo.Name;

                            if (ptype.Equals("existing") || ptype.Equals("efficient") || ptype.Equals("existingHome") || ptype.Equals("withUpgrades"))
                            {
                                if (ptype.Equals("existing")) 
                                {
                                    //existing
                                    Console.WriteLine("existing:");
                                    RetrieveSessionCompareDetails existingObj = restrieveSummaryResults.existing;
                                    //use reflection to iterate thru the values
                                        Type stype = typeof(RetrieveSessionCompareDetails);
                                        PropertyInfo[] sproperties = stype.GetProperties();
                                        foreach (PropertyInfo spropertyInfo in sproperties)
                                        {
                                            object srcValue = existingObj.GetType().InvokeMember(spropertyInfo.Name, BindingFlags.GetProperty, null, existingObj, null);
                                            if (srcValue != null)
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":" + srcValue.ToString());
                                            }
                                            else
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":");
                                            }

                                        }

                                } 
                                else if (ptype.Equals("efficient")) 
                                {
                                    //efficient
                                    Console.WriteLine("efficient:");
                                    RetrieveSessionCompareDetails efficientObj = restrieveSummaryResults.efficient;
                                    //use reflection to iterate thru the values
                                        Type stype = typeof(RetrieveSessionCompareDetails);
                                        PropertyInfo[] sproperties = stype.GetProperties();
                                        foreach (PropertyInfo spropertyInfo in sproperties)
                                        {
                                            object srcValue = efficientObj.GetType().InvokeMember(spropertyInfo.Name, BindingFlags.GetProperty, null, efficientObj, null);
                                            if (srcValue != null)
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":" + srcValue.ToString());
                                            }
                                            else
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":");
                                            }
                                        }

                                }
                                else if (ptype.Equals("existingHome")) 
                                {
                                    //existingHome
                                    Console.WriteLine("existingHome:");
                                    RetrieveSessionCompareDetails2 existingHomeObj = restrieveSummaryResults.existingHome;
                                    //use reflection to iterate thru the values
                                        Type stype = typeof(RetrieveSessionCompareDetails2);
                                        PropertyInfo[] sproperties = stype.GetProperties();
                                        foreach (PropertyInfo spropertyInfo in sproperties)
                                        {
                                            object srcValue = existingHomeObj.GetType().InvokeMember(spropertyInfo.Name, BindingFlags.GetProperty, null, existingHomeObj, null);
                                            if (srcValue != null)
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":" + srcValue.ToString());
                                            }
                                            else
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":");
                                            }
                                        }

                                }
                                else 
                                {
                                    //withUpgrades
                                    Console.WriteLine("withUpgrades:");
                                    RetrieveSessionCompareDetails2 withUpgradesHomeObj = restrieveSummaryResults.withUpgrades;
                                    //use reflection to iterate thru the values
                                        Type stype = typeof(RetrieveSessionCompareDetails2);
                                        PropertyInfo[] sproperties = stype.GetProperties();
                                        foreach (PropertyInfo spropertyInfo in sproperties)
                                        {
                                            object srcValue = withUpgradesHomeObj.GetType().InvokeMember(spropertyInfo.Name, BindingFlags.GetProperty, null, withUpgradesHomeObj, null);
                                            if (srcValue != null)
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":" + srcValue.ToString());
                                            }
                                            else
                                            {
                                                Console.WriteLine(" -" + spropertyInfo.Name + ":");
                                            }
                                        }

                                }

                            }
                            else
                            {
                                object srcValue = restrieveSummaryResults.GetType().InvokeMember(propertyInfo.Name, BindingFlags.GetProperty, null, restrieveSummaryResults, null);
                                if (srcValue != null)
                                {
                                    Console.WriteLine(propertyInfo.Name + ":" + srcValue.ToString());
                                }
                                else
                                {
                                    Console.WriteLine(propertyInfo.Name + ":");
                                }
                            }
                        }
                        Console.WriteLine();
                        //this is just here to stop between each record
                        //Console.ReadLine();

                    

                    //this is just here to stop the console from closing until you press enter
                    Console.ReadLine();
                   */


                }
                    


            }

        

    }
}
