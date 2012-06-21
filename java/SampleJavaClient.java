/**
 * SampleJavaClient - June 21st, 2012
 *
 * This code will generate a Home Energy Scoring Tool (HEST) Label using JAX-WS
 *
 * @author Chris Ralph (chris.ralph@bighead.net)
 *
 * Important: You will need to run the following commands to generate scoring tool libraries from WSDL:
 *
 *   cd <your_project_folder>/src
 *   wsimport -keep -verbose -b binding.xml http://tool-sb-api.hescloud.net/calculate/wsdl
 *   wsimport -keep -verbose -b binding.xml http://tool-sb-api.hescloud.net/session/wsdl
 *
 * Note: wsimport is included as part of JDK 1.5 onwards. Tested with jdk1.6.0_24
 */

package hestapijavaclient;

import gov.lbl.hes.scoring_tool.session.*;
import gov.lbl.hes.scoring_tool.calculate.*;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SampleJavaClient{

    public static void main(String[] args) throws Exception {

        HesAPISessionService hes = new HesAPISessionService();
        HesAPISessionPort sp = hes.getHesAPISessionPort();

        HesAPICalculateService hcs = new HesAPICalculateService();
        HesAPICalculatePort cp = hcs.getHesAPICalculatePort();

        String       clientGuid   = "INSERT_YOUR_3SCALE_KEY_HERE"; // 3scale key
        Address_Data addressData  = new Address_Data();

        addressData.setAddress("1 Cyclotron Road");
        addressData.setCity("Berkeley");
        addressData.setState("CA");
        addressData.setZip("94720");

        String          caNumber  = "INSERT_YOUR_CERTIFIED_ASSESOR_NUMBER_HERE"; // Asessor ID
        String authenticationType = "DOE"; // DOE is the only method available currently
        String       guiSessionId = "123"; // Any value you want, doesn't matter

        // Return parameters
        Holder returnCodeObj = new Holder();
        Holder returnComment = new Holder();
        Holder readOnly = new Holder();
        Holder addressList = new Holder();
        Holder retrieveSessionByIdResponse = new Holder();

        // Call the SOAP method newSessionFromAddress()
        sp.newSessionFromAddress(clientGuid,addressData,caNumber,authenticationType,guiSessionId,returnCodeObj,returnComment,readOnly,addressList,retrieveSessionByIdResponse);

        // We need returnCode as an int
        Integer tmp    = (Integer) returnCodeObj.value;
        int returnCode = tmp.intValue();

        // returnCode = 1 means success
        if (returnCode > 0)
        {
            // Grab the RetrieveSessionByIdResponse object
            RetrieveSessionByIdResponse_Type response = (RetrieveSessionByIdResponse_Type) retrieveSessionByIdResponse.value;

            // Get a list of session inputs
            List sessionInput = response.getSessionInput();

            // Get the number of elements in the list
            int size = sessionInput.size();

            // Retrieve the last sessionInput row (meta data)
            SessionInput_Type lastRow = (SessionInput_Type) sessionInput.get(sessionInput.size()-1);

            // Grab the sessionValue for this row
            String sessionValue = lastRow.getSessionValue();

            // Split into multiple lines
            String delims = "[;]";
            String[] tokens = sessionValue.split(delims);

            // Grab the sessionId (sessionId^xxxxxx)
            delims = "[\\^]";
            String[] tokens2 = tokens[0].split(delims);

            // Session Id should be an int
            int sessionId = Integer.parseInt(tokens2[1]);

            // Print our session id
            System.out.println("Session created with session_id #" + sessionId);


            // Customize some inputs
            Session_Inputs sessionInputs = new Session_Inputs();
            List        sessionInputList = sessionInputs.getSession_Input();

            Session_Input[] inputRow = new Session_Input[53];

            //page 1 (Audit-Details)
            inputRow[0] = new Session_Input();
            inputRow[0].setInputTableName("whole_house_input");
            inputRow[0].setInputColumnName("auditDate");
            inputRow[0].setValue("2012-03-14");
            sessionInputList.add(inputRow[0]);

            inputRow[1] = new Session_Input();
            inputRow[1].setInputTableName("whole_house_input");
            inputRow[1].setInputColumnName("year");
            inputRow[1].setValue("1976");
            sessionInputList.add(inputRow[1]);

            inputRow[2] = new Session_Input();
            inputRow[2].setInputTableName("whole_house_input");
            inputRow[2].setInputColumnName("numberBedrooms");
            inputRow[2].setValue("5");
            sessionInputList.add(inputRow[2]);

            inputRow[3] = new Session_Input();
            inputRow[3].setInputTableName("whole_house_input");
            inputRow[3].setInputColumnName("storiesAboveGround");
            inputRow[3].setValue("2");
            sessionInputList.add(inputRow[3]);

            inputRow[4] = new Session_Input();
            inputRow[4].setInputTableName("heat_cool_input");
            inputRow[4].setInputColumnName("ceilingHeight");
            inputRow[4].setValue("9.00");
            sessionInputList.add(inputRow[4]);

            inputRow[5] = new Session_Input();
            inputRow[5].setInputTableName("whole_house_input");
            inputRow[5].setInputColumnName("floorArea");
            inputRow[5].setValue("2500");
            sessionInputList.add(inputRow[5]);

            inputRow[6] = new Session_Input();
            inputRow[6].setInputTableName("whole_house_input");
            inputRow[6].setInputColumnName("houseOrientation");
            inputRow[6].setValue("180");
            sessionInputList.add(inputRow[6]);

            inputRow[7] = new Session_Input();
            inputRow[7].setInputTableName("heat_cool_input");
            inputRow[7].setInputColumnName("airLeakage50ip");
            inputRow[7].setValue("");
            sessionInputList.add(inputRow[7]);

            inputRow[8] = new Session_Input();
            inputRow[8].setInputTableName("heat_cool_input");
            inputRow[8].setInputColumnName("airSealingPresent");
            inputRow[8].setValue("1");
            sessionInputList.add(inputRow[8]);


            //page 2 (Attic-Roof)
            inputRow[9] = new Session_Input();
            inputRow[9].setInputTableName("heat_cool_input");
            inputRow[9].setInputColumnName("roofConstruction");
            inputRow[9].setValue("rfwf13lc");
            sessionInputList.add(inputRow[9]);

            inputRow[10] = new Session_Input();
            inputRow[10].setInputTableName("heat_cool_input");
            inputRow[10].setInputColumnName("roofAbsorptance");
            inputRow[10].setValue(".65");
            sessionInputList.add(inputRow[10]);

            inputRow[11] = new Session_Input();
            inputRow[11].setInputTableName("heat_cool_input");
            inputRow[11].setInputColumnName("atticType");
            inputRow[11].setValue("uncond_attic");
            sessionInputList.add(inputRow[11]);

            inputRow[12] = new Session_Input();
            inputRow[12].setInputTableName("heat_cool_input");
            inputRow[12].setInputColumnName("ceilingConstruction");
            inputRow[12].setValue("ecwf11");
            sessionInputList.add(inputRow[12]);

            inputRow[13] = new Session_Input();
            inputRow[13].setInputTableName("heat_cool_input");
            inputRow[13].setInputColumnName("foundationType");
            inputRow[13].setValue("uncond_base");
            sessionInputList.add(inputRow[13]);

            inputRow[14] = new Session_Input();
            inputRow[14].setInputTableName("heat_cool_input");
            inputRow[14].setInputColumnName("floorConstruction");
            inputRow[14].setValue("efwf11ca");
            sessionInputList.add(inputRow[14]);

            inputRow[15] = new Session_Input();
            inputRow[15].setInputTableName("heat_cool_input");
            inputRow[15].setInputColumnName("foundationSideInsulationRValue");
            inputRow[15].setValue("11");
            sessionInputList.add(inputRow[15]);


            //page 3 (Walls)
            inputRow[16] = new Session_Input();
            inputRow[16].setInputTableName("heat_cool_input");
            inputRow[16].setInputColumnName("wallsSameAllSides");
            inputRow[16].setValue("1");
            sessionInputList.add(inputRow[16]);

            inputRow[17] = new Session_Input();
            inputRow[17].setInputTableName("heat_cool_input");
            inputRow[17].setInputColumnName("wallConstructionFront");
            inputRow[17].setValue("ewih13vi");
            sessionInputList.add(inputRow[17]);

            inputRow[18] = new Session_Input();
            inputRow[18].setInputTableName("heat_cool_input");
            inputRow[18].setInputColumnName("wallConstructionBack");
            inputRow[18].setValue("");
            sessionInputList.add(inputRow[18]);

            inputRow[19] = new Session_Input();
            inputRow[19].setInputTableName("heat_cool_input");
            inputRow[19].setInputColumnName("wallConstructionRight");
            inputRow[19].setValue("");
            sessionInputList.add(inputRow[19]);

            inputRow[20] = new Session_Input();
            inputRow[20].setInputTableName("heat_cool_input");
            inputRow[20].setInputColumnName("wallConstructionLeft");
            inputRow[20].setValue("");
            sessionInputList.add(inputRow[20]);


            //page 4 (Windows-Skylights)
            inputRow[21] = new Session_Input();
            inputRow[21].setInputTableName("heat_cool_input");
            inputRow[21].setInputColumnName("skylightsPresent");
            inputRow[21].setValue("0");
            sessionInputList.add(inputRow[21]);

            inputRow[22] = new Session_Input();
            inputRow[22].setInputTableName("heat_cool_input");
            inputRow[22].setInputColumnName("skylightType");
            inputRow[22].setValue("");
            sessionInputList.add(inputRow[22]);

            inputRow[23] = new Session_Input();
            inputRow[23].setInputTableName("heat_cool_input");
            inputRow[23].setInputColumnName("skylightArea");
            inputRow[23].setValue("");
            sessionInputList.add(inputRow[23]);

            inputRow[24] = new Session_Input();
            inputRow[24].setInputTableName("heat_cool_input");
            inputRow[24].setInputColumnName("windowAreaFront");
            inputRow[24].setValue("100");
            sessionInputList.add(inputRow[24]);

            inputRow[25] = new Session_Input();
            inputRow[25].setInputTableName("heat_cool_input");
            inputRow[25].setInputColumnName("windowAreaRight");
            inputRow[25].setValue("100");
            sessionInputList.add(inputRow[25]);

            inputRow[26] = new Session_Input();
            inputRow[26].setInputTableName("heat_cool_input");
            inputRow[26].setInputColumnName("windowAreaBack");
            inputRow[26].setValue("100");
            sessionInputList.add(inputRow[26]);

            inputRow[27] = new Session_Input();
            inputRow[27].setInputTableName("heat_cool_input");
            inputRow[27].setInputColumnName("windowAreaLeft");
            inputRow[27].setValue("100");
            sessionInputList.add(inputRow[27]);

            inputRow[28] = new Session_Input();
            inputRow[28].setInputTableName("heat_cool_input");
            inputRow[28].setInputColumnName("windowTypesDifferBySide");
            inputRow[28].setValue("1");
            sessionInputList.add(inputRow[28]);

            inputRow[29] = new Session_Input();
            inputRow[29].setInputTableName("heat_cool_input");
            inputRow[29].setInputColumnName("windowTypeFront");
            inputRow[29].setValue("dcaw");
            sessionInputList.add(inputRow[29]);

            inputRow[30] = new Session_Input();
            inputRow[30].setInputTableName("heat_cool_input");
            inputRow[30].setInputColumnName("windowUValueFront");
            inputRow[30].setValue("");
            sessionInputList.add(inputRow[30]);

            inputRow[31] = new Session_Input();
            inputRow[31].setInputTableName("heat_cool_input");
            inputRow[31].setInputColumnName("windowSolarGainFront");
            inputRow[31].setValue("");
            sessionInputList.add(inputRow[31]);

            inputRow[32] = new Session_Input();
            inputRow[32].setInputTableName("heat_cool_input");
            inputRow[32].setInputColumnName("windowTypeRight");
            inputRow[32].setValue("");
            sessionInputList.add(inputRow[32]);

            inputRow[33] = new Session_Input();
            inputRow[33].setInputTableName("heat_cool_input");
            inputRow[33].setInputColumnName("windowUValueRight");
            inputRow[33].setValue("");
            sessionInputList.add(inputRow[33]);

            inputRow[34] = new Session_Input();
            inputRow[34].setInputTableName("heat_cool_input");
            inputRow[34].setInputColumnName("windowSolarGainRight");
            inputRow[34].setValue("");
            sessionInputList.add(inputRow[34]);

            inputRow[35] = new Session_Input();
            inputRow[35].setInputTableName("heat_cool_input");
            inputRow[35].setInputColumnName("windowTypeBack");
            inputRow[35].setValue("");
            sessionInputList.add(inputRow[35]);

            inputRow[36] = new Session_Input();
            inputRow[36].setInputTableName("heat_cool_input");
            inputRow[36].setInputColumnName("windowUValueBack");
            inputRow[36].setValue("");
            sessionInputList.add(inputRow[36]);

            inputRow[37] = new Session_Input();
            inputRow[37].setInputTableName("heat_cool_input");
            inputRow[37].setInputColumnName("windowSolarGainBack");
            inputRow[37].setValue("");
            sessionInputList.add(inputRow[37]);

            inputRow[38] = new Session_Input();
            inputRow[38].setInputTableName("heat_cool_input");
            inputRow[38].setInputColumnName("windowTypeLeft");
            inputRow[38].setValue("");
            sessionInputList.add(inputRow[38]);

            inputRow[39] = new Session_Input();
            inputRow[39].setInputTableName("heat_cool_input");
            inputRow[39].setInputColumnName("windowUValueLeft");
            inputRow[39].setValue("");
            sessionInputList.add(inputRow[39]);

            inputRow[40] = new Session_Input();
            inputRow[40].setInputTableName("heat_cool_input");
            inputRow[40].setInputColumnName("windowSolarGainLeft");
            inputRow[40].setValue("");
            sessionInputList.add(inputRow[40]);


            //page 5 (Heating-Cooling)
            inputRow[41] = new Session_Input();
            inputRow[41].setInputTableName("heat_cool_input");
            inputRow[41].setInputColumnName("heatingType");
            inputRow[41].setValue("gfn");
            sessionInputList.add(inputRow[41]);

            inputRow[42] = new Session_Input();
            inputRow[42].setInputTableName("heat_cool_input");
            inputRow[42].setInputColumnName("heatingYearPurchased");
            inputRow[42].setValue("2005");
            sessionInputList.add(inputRow[42]);

            inputRow[43] = new Session_Input();
            inputRow[43].setInputTableName("heat_cool_input");
            inputRow[43].setInputColumnName("heatingEfficiency");
            inputRow[43].setValue("");
            sessionInputList.add(inputRow[43]);

            inputRow[44] = new Session_Input();
            inputRow[44].setInputTableName("heat_cool_input");
            inputRow[44].setInputColumnName("coolingType");
            inputRow[44].setValue("ehp");
            sessionInputList.add(inputRow[44]);

            inputRow[45] = new Session_Input();
            inputRow[45].setInputTableName("heat_cool_input");
            inputRow[45].setInputColumnName("coolingYearPurchased");
            inputRow[45].setValue("2005");
            sessionInputList.add(inputRow[45]);

            inputRow[46] = new Session_Input();
            inputRow[46].setInputTableName("heat_cool_input");
            inputRow[46].setInputColumnName("coolingEfficiency");
            inputRow[46].setValue("");
            sessionInputList.add(inputRow[46]);

            inputRow[47] = new Session_Input();
            inputRow[47].setInputTableName("heat_cool_input");
            inputRow[47].setInputColumnName("ductLocation");
            inputRow[47].setValue("uncond_attic");
            sessionInputList.add(inputRow[47]);

            inputRow[48] = new Session_Input();
            inputRow[48].setInputTableName("heat_cool_input");
            inputRow[48].setInputColumnName("ductInsulationPresent");
            inputRow[48].setValue("1");
            sessionInputList.add(inputRow[48]);

            inputRow[49] = new Session_Input();
            inputRow[49].setInputTableName("heat_cool_input");
            inputRow[49].setInputColumnName("ductSealingPresent");
            inputRow[49].setValue("1");
            sessionInputList.add(inputRow[49]);

            inputRow[50] = new Session_Input();
            inputRow[50].setInputTableName("hot_water_input");
            inputRow[50].setInputColumnName("hwFuel");
            inputRow[50].setValue("ele");
            sessionInputList.add(inputRow[50]);

            inputRow[51] = new Session_Input();
            inputRow[51].setInputTableName("hot_water_input");
            inputRow[51].setInputColumnName("hwYearPurchased");
            inputRow[51].setValue("2005");
            sessionInputList.add(inputRow[51]);

            inputRow[52] = new Session_Input();
            inputRow[52].setInputTableName("hot_water_input");
            inputRow[52].setInputColumnName("hwEnergyFactor");
            inputRow[52].setValue("");
            sessionInputList.add(inputRow[52]);

            inputRow[52] = new Session_Input();
            inputRow[52].setInputTableName("hot_water_input");
            inputRow[52].setInputColumnName("hwFromBoiler");
            inputRow[52].setValue("separate");
            sessionInputList.add(inputRow[52]);


            // Meta Data
            Meta_Data metaData = new Meta_Data();
            metaData.setFormGroup("detailed");
            metaData.setFormForm("Heating-Cooling");

            // Validate and run calc ?
            int validate = 1;

            // Return parameters
            returnCodeObj           = new Holder();
            returnComment           = new Holder();
            Holder calculated       = new Holder();
            Holder validationErrors = new Holder();

            // Call the SOAP method saveLabelSession()
            sp.saveLabelSession(clientGuid,sessionId,guiSessionId,validate,metaData,sessionInputs,returnCodeObj,returnComment,calculated,validationErrors);

            // We need returnCode as an int
            tmp        = (Integer) returnCodeObj.value;
            returnCode = tmp.intValue();

            if (returnCode > 0)
            {
                // Lets us know the save was successful (ie. "Save & Calc Ok")
                System.out.println("saveLabelSession: " + returnComment.value);

                // Return parameters
                returnCodeObj  = new Holder();
                returnComment  = new Holder();
                Holder address = new Holder();
                Holder climateZone = new Holder();
                Holder numberBedrooms = new Holder();
                Holder floorArea = new Holder();
                Holder houseSizeGroup = new Holder();
                Holder caNumberReturn = new Holder();
                Holder auditDate = new Holder();
                Holder printDate = new Holder();
                Holder airConditioned = new Holder();
                Holder labelResult = new Holder();
                Holder sessionResult = new Holder();

                // Get detailed results
                sp.retrieveLabelDetailResults(clientGuid,sessionId,returnCodeObj,returnComment,address,climateZone,numberBedrooms,floorArea,houseSizeGroup,caNumberReturn,auditDate,printDate,airConditioned,labelResult,sessionResult);

                // We need returnCode as an int
                tmp        = (Integer) returnCodeObj.value;
                returnCode = tmp.intValue();

                if (returnCode > 0)
                {
                    // Lets us know the retrieve was successful (ie. "Save & Calc Ok")
                    System.out.println("retrieveLabelDetailResults: " + returnComment.value);

                    // Grab the labelResult object
                    LabelResult label_result = (LabelResult) labelResult.value;

                    System.out.println("\n-------------------------------------\nDumping label results:\n-------------------------------------\n");
                    dump_parameters(label_result);

                    // Grab the sessionResult object
                    SessionResult result = (SessionResult) sessionResult.value;

                    SessionResultRow base_results = result.getBase();
                    SessionResultRow pkg_results  = result.getPackage1();

                    System.out.println("\n-------------------------------------\nDumping session results for base:\n-------------------------------------\n");
                    dump_parameters(base_results);

                    System.out.println("\n-------------------------------------\nDumping session results for package1:\n-------------------------------------\n");
                    dump_parameters(pkg_results);

                    System.out.println("-------------------------------------");

                    // Force the label to be re-generated each time ?
                    Integer forceRegenerate = 1;

                    // Return parameters
                    returnCodeObj  = new Holder();
                    returnComment  = new Holder();
                    Holder pdfUrls = new Holder();

                    // Call the SOAP method generateHesLabel()
                    cp.generateHesLabel(clientGuid,sessionId,forceRegenerate,returnCodeObj,returnComment,pdfUrls);

                    // We need returnCode as an int
                    tmp        = (Integer) returnCodeObj.value;
                    returnCode = tmp.intValue();

                    if (returnCode > 0)
                    {
                        // Lets us know the generate was successful (ie. "Save & Calc Ok")
                        System.out.println("generateHesLabel: " + returnComment.value);

                        // Grab the pdf_urls object
                        Pdf_Urls pdf_urls = (Pdf_Urls) pdfUrls.value;

                        System.out.println("\n-------------------------------------\nDumping pdf_urls:\n-------------------------------------\n");
                        dump_parameters(pdf_urls);

                        System.out.println("-------------------------------------");
				    }else{
                        // Something went wrong, dump the error message
                        System.out.println("\n" + "returnCode from generateHesLabel() was: " + returnCode + "\n" + "returnComment was: " + returnComment.value);
			        }

                }else{
                    // Something went wrong, dump the error message
                    System.out.println("\n" + "returnCode from retrieveLabelDetailResults() was: " + returnCode + "\n" + "returnComment was: " + returnComment.value);
                }

            }else{
                // Something went wrong, dump the error message
                System.out.println("\n" + "returnCode from saveLabelSession() was: " + returnCode + "\n" + "returnComment was: " + returnComment.value);
            }

        }else{

            // Something went wrong, dump the error message
            System.out.println("\n" + "returnCode from newSessionFromAddress() was: " + returnCode + "\n" + "returnComment was: " + returnComment.value);
        }
    }

    public static void dump_parameters(Object resource)
    {
        Method[] methods = resource.getClass().getMethods();

        String methodName;
        String parameterName;
        Object returnData;

        for (Method method : methods)
        {
            if (method.getName().startsWith("get") && !method.getName().equals("get") && !method.getName().equals("getClass") && method.getParameterTypes().length == 0 && method.getReturnType() != void.class)
            {
                try
                {
                    // Convert the getter method name to a parameter name (e.g. getFooBar becomes fooBar)
                    methodName    = method.getName();
                    parameterName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);

                    // Call the getter method to grab the value
                    returnData = method.invoke(resource);

                    System.out.println("    " + parameterName + " = " + returnData.toString());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(SampleJavaClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(SampleJavaClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(SampleJavaClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}