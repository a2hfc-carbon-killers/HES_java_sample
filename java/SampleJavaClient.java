
package hesapijavaclient;

import java.net.*;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.ByteArrayOutputStream;
import java.io.Writer;
import java.io.StringWriter;
import javax.xml.soap.SOAPMessage;
import java.util.*;





/**
 * SampleJavaClient
 *
 * this is to be the sample given out in JAVA to access the HES APIs
 *
 * @author ben johansen (ben@bighead.net)
 */
public class SampleJavaClient {

    //class global variables

    //INSERT YOUR 3SCALE GUID HERE
    private static String CLIENT_GUID = "INSERT YOUR 3SCALE KEY HERE";

    //SOAP_URL (this is where the webservice will call
    //do not put the trailing slash "/"
    private static String SOAP_URL = "http://sbapp.hescloud.net/session";


    //SESSION_TYPE (0 = Quick Session, 1 = Detailed Session)
    private static String SESSION_TYPE = "0";

    //WEBSITE_TYPE (leave this as 0)
    private static String WEBSITE_TYPE = "0";

    //SESSION_ID
    private static Integer SESSION_ID = 1910566;

    //ZIPCODE
    private static String ZIPCODE = "98661";

    //VALIDATE (0 = just save, 1 = save and calc)
    private static Integer VALIDATE = 1;



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //this main is not designed to take inputs from the arg string array
        if (args.length == 0) {




           /*
           --------------------------------
           sample create new session by zipcode
           --------------------------------
            * note: look for rowid = -2 in return data, this will have the session_id of the newly created record
            * sessionValue = sessionId^1925977;uniqueName^;zipcode^98661;zipCity^Vancouver;zipState^Washington;calculated^0
            */
           //----- un-comment line below to run this sample -----
           System.out.println(callAPIViaSOAP(callnewSession(CLIENT_GUID,ZIPCODE,WEBSITE_TYPE)));

           /*
           --------------------------------
           sample retrive a session by session_id
           --------------------------------
           */
           //----- un-comment line below to run this sample -----
           //System.out.println(callAPIViaSOAP(callretrieveSessionById11(CLIENT_GUID,SESSION_ID,SESSION_TYPE,WEBSITE_TYPE)));


           /*
           --------------------------------
           sample saveSession11
           --------------------------------
            * note: on of the params of this method is an object called "input_object"
            * the input_object will be used in the callAPIViaSOAP to turn the data stored in this object
            * into the complex types that the hes saveSession11 webservice.
            */

           //----- un-comment block below to run this sample -----
           /*
           //create saveSession11 input_object data
            //extraInputs
           SaveSessionExtraInputs extraInputs = new SaveSessionExtraInputs();
            extraInputs.setController("consumer");
            extraInputs.setAction("save-and-go");
            extraInputs.setModule("default");
            extraInputs.setNextPage("/consumer/quick-building-design");
            extraInputs.setNextTask("next");
            extraInputs.setFormGroup("Quick");
            extraInputs.setFormForm("General");
            //saveInputs
           SaveSessionSaveInputs[] saveInputs = new SaveSessionSaveInputs[18];
           saveInputs[0] = new SaveSessionSaveInputs();
            saveInputs[0].setInputTableName("whole_house_input");
            saveInputs[0].setInputColumnName("uniqueName");
            saveInputs[0].setS_value("java test");
           saveInputs[1] = new SaveSessionSaveInputs();
            saveInputs[1].setInputTableName("whole_house_input");
            saveInputs[1].setInputColumnName("emailAddress");
            saveInputs[1].setS_value("");
           saveInputs[2] = new SaveSessionSaveInputs();
            saveInputs[2].setInputTableName("whole_house_input");
            saveInputs[2].setInputColumnName("purpose");
            saveInputs[2].setS_value("0");
           saveInputs[3] = new SaveSessionSaveInputs();
            saveInputs[3].setInputTableName("whole_house_input");
            saveInputs[3].setInputColumnName("purposeOther");
            saveInputs[3].setS_value("");
           saveInputs[4] = new SaveSessionSaveInputs();
            saveInputs[4].setInputTableName("whole_house_input");
            saveInputs[4].setInputColumnName("address");
            saveInputs[4].setS_value("");
           saveInputs[5] = new SaveSessionSaveInputs();
            saveInputs[5].setInputTableName("whole_house_input");
            saveInputs[5].setInputColumnName("city");
            saveInputs[5].setS_value("Portland");
           saveInputs[6] = new SaveSessionSaveInputs();
            saveInputs[6].setInputTableName("whole_house_input");
            saveInputs[6].setInputColumnName("stateCode");
            saveInputs[6].setS_value("WA");
           saveInputs[7] = new SaveSessionSaveInputs();
            saveInputs[7].setInputTableName("whole_house_input");
            saveInputs[7].setInputColumnName("weatherCity");
            saveInputs[7].setS_value("Portland OR");
           saveInputs[8] = new SaveSessionSaveInputs();
            saveInputs[8].setInputTableName("whole_house_input");
            saveInputs[8].setInputColumnName("year");
            saveInputs[8].setS_value("1971");
           saveInputs[9] = new SaveSessionSaveInputs();
            saveInputs[9].setInputTableName("whole_house_input");
            saveInputs[9].setInputColumnName("occupants_0_5");
            saveInputs[9].setS_value("0");
           saveInputs[10] = new SaveSessionSaveInputs();
            saveInputs[10].setInputTableName("whole_house_input");
            saveInputs[10].setInputColumnName("occupants_6_13");
            saveInputs[10].setS_value("1");
           saveInputs[11] = new SaveSessionSaveInputs();
            saveInputs[11].setInputTableName("whole_house_input");
            saveInputs[11].setInputColumnName("occupants_14_64");
            saveInputs[11].setS_value("2");
           saveInputs[12] = new SaveSessionSaveInputs();
            saveInputs[12].setInputTableName("whole_house_input");
            saveInputs[12].setInputColumnName("occupants_65_plus");
            saveInputs[12].setS_value("0");
           saveInputs[13] = new SaveSessionSaveInputs();
            saveInputs[13].setInputTableName("whole_house_input");
            saveInputs[13].setInputColumnName("priceElect");
            saveInputs[13].setS_value("0.076");
           saveInputs[14] = new SaveSessionSaveInputs();
            saveInputs[14].setInputTableName("whole_house_input");
            saveInputs[14].setInputColumnName("priceGas");
            saveInputs[14].setS_value("1.31");
           saveInputs[15] = new SaveSessionSaveInputs();
            saveInputs[15].setInputTableName("whole_house_input");
            saveInputs[15].setInputColumnName("priceLpg");
            saveInputs[15].setS_value("2.38");
           saveInputs[16] = new SaveSessionSaveInputs();
            saveInputs[16].setInputTableName("whole_house_input");
            saveInputs[16].setInputColumnName("priceOil");
            saveInputs[16].setS_value("3.16");
           saveInputs[17] = new SaveSessionSaveInputs();
            saveInputs[17].setInputTableName("whole_house_input");
            saveInputs[17].setInputColumnName("formsCompletionArray");
            saveInputs[17].setS_value("Quick_General^2;Quick_Building-Design^0;Quick_Appliances-Equipment^0;Detailed_General^0;Detailed_Exterior-Shading^0;Detailed_Air-Tightness^0;Detailed_Foundation-Floor^0;Detailed_Walls^0;Detailed_Doors-Windows^0;Detailed_Attic-Roof^0;Detailed_Ducts-Pipes^0;Detailed_Thermostat^0;Detailed_Heating-Equipment^0;Detailed_Cooling-Equipment^0;Detailed_Water-Heating^0;Detailed_Lighting^0;Detailed_Refrigerators-Freezers^0;Detailed_Cooking-Dishwashing^0;Detailed_Laundry^0;Detailed_HotTubs-Spas-Pumps^0;Detailed_Entertainment^0;Detailed_Home-Office^0;Detailed_Miscellaneous-Kitchen-Equipment^0;Detailed_Other-Appliances^0;Detailed_House-Shape-Size^0;Detailed_Skylights^0");

            //lightInputs
           SaveSessionLightInputs lightInputs[] = new SaveSessionLightInputs[0];

           SaveSessionInput input_object = new SaveSessionInput();
            input_object.setExtraInputs(extraInputs);
            input_object.setSaveInputs(saveInputs);
            input_object.setLightInputs(lightInputs);


            System.out.println(callAPIViaSOAP(callsaveSession11(CLIENT_GUID,SESSION_ID,input_object,VALIDATE)));

            */


            /*
             * --------------------------------------
             * Sample retrieveSummarySessionResults11
             * --------------------------------------
             */
            //---- un-comment line below to run this sample -----
            //System.out.println(callAPIViaSOAP(callretrieveSummarySessionResults11(CLIENT_GUID,SESSION_ID)));


        }



    }

    /* class to define the each Save Session Inputs
     *
     *
     */
    private static class SaveSessionInput  {
        private SaveSessionExtraInputs extraInputs;
        private SaveSessionSaveInputs[] saveInputs;
        private SaveSessionLightInputs[] lightInputs;

        /**
         * @return the extraInputs
         */
        public SaveSessionExtraInputs getExtraInputs() {
            return extraInputs;
        }

        /**
         * @param extraInputs the extraInputs to set
         */
        public void setExtraInputs(SaveSessionExtraInputs extraInputs) {
            this.extraInputs = extraInputs;
        }

        /**
         * @return the saveInputs
         */
        public SaveSessionSaveInputs[] getSaveInputs() {
            return saveInputs;
        }

        /**
         * @param saveInputs the saveInputs to set
         */
        public void setSaveInputs(SaveSessionSaveInputs[] saveInputs) {
            this.saveInputs = saveInputs;
        }

        /**
         * @return the lightInputs
         */
        public SaveSessionLightInputs[] getLightInputs() {
            return lightInputs;
        }

        /**
         * @param lightInputs the lightInputs to set
         */
        public void setLightInputs(SaveSessionLightInputs[] lightInputs) {
            this.lightInputs = lightInputs;
        }

    }

    /* class to define the each Save Session Extra Inputs
     *
     *
     */
    private static class SaveSessionLightInputs {
        private String sessionId = "";
        private String roomName = "";
        private String instanceName = "";
        private String lampNumber = "";
        private String lampType = "";
        private String lampWatts = "";
        private String hoursPerDay = "";

        /**
         * @return the sessionId
         */
        public String getSessionId() {
            return sessionId;
        }

        /**
         * @param sessionId the sessionId to set
         */
        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        /**
         * @return the roomName
         */
        public String getRoomName() {
            return roomName;
        }

        /**
         * @param roomName the roomName to set
         */
        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        /**
         * @return the instanceName
         */
        public String getInstanceName() {
            return instanceName;
        }

        /**
         * @param instanceName the instanceName to set
         */
        public void setInstanceName(String instanceName) {
            this.instanceName = instanceName;
        }

        /**
         * @return the lampNumber
         */
        public String getLampNumber() {
            return lampNumber;
        }

        /**
         * @param lampNumber the lampNumber to set
         */
        public void setLampNumber(String lampNumber) {
            this.lampNumber = lampNumber;
        }

        /**
         * @return the lampType
         */
        public String getLampType() {
            return lampType;
        }

        /**
         * @param lampType the lampType to set
         */
        public void setLampType(String lampType) {
            this.lampType = lampType;
        }

        /**
         * @return the lampWatts
         */
        public String getLampWatts() {
            return lampWatts;
        }

        /**
         * @param lampWatts the lampWatts to set
         */
        public void setLampWatts(String lampWatts) {
            this.lampWatts = lampWatts;
        }

        /**
         * @return the hoursPerDay
         */
        public String getHoursPerDay() {
            return hoursPerDay;
        }

        /**
         * @param hoursPerDay the hoursPerDay to set
         */
        public void setHoursPerDay(String hoursPerDay) {
            this.hoursPerDay = hoursPerDay;
        }

    }

    /* class to define the each Save Session Extra Inputs
     *
     *
     */
    private static class SaveSessionExtraInputs {
        private String controller = "";
        private String action = "";
        private String module = "";
        private String nextPage = "";
        private String nextTask = "";
        private String finished_detailed = "";
        private String formGroup = "";
        private String formForm = "";

        /**
         * @return the controller
         */
        public String getController() {
            return controller;
        }

        /**
         * @param controller the controller to set
         */
        public void setController(String controller) {
            this.controller = controller;
        }

        /**
         * @return the action
         */
        public String getAction() {
            return action;
        }

        /**
         * @param action the action to set
         */
        public void setAction(String action) {
            this.action = action;
        }

        /**
         * @return the module
         */
        public String getModule() {
            return module;
        }

        /**
         * @param module the module to set
         */
        public void setModule(String module) {
            this.module = module;
        }

        /**
         * @return the nextPage
         */
        public String getNextPage() {
            return nextPage;
        }

        /**
         * @param nextPage the nextPage to set
         */
        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }

        /**
         * @return the nextTask
         */
        public String getNextTask() {
            return nextTask;
        }

        /**
         * @param nextTask the nextTask to set
         */
        public void setNextTask(String nextTask) {
            this.nextTask = nextTask;
        }

        /**
         * @return the finished_detailed
         */
        public String getFinished_detailed() {
            return finished_detailed;
        }

        /**
         * @param finished_detailed the finished_detailed to set
         */
        public void setFinished_detailed(String finished_detailed) {
            this.finished_detailed = finished_detailed;
        }

        /**
         * @return the formGroup
         */
        public String getFormGroup() {
            return formGroup;
        }

        /**
         * @param formGroup the formGroup to set
         */
        public void setFormGroup(String formGroup) {
            this.formGroup = formGroup;
        }

        /**
         * @return the formForm
         */
        public String getFormForm() {
            return formForm;
        }

        /**
         * @param formForm the formForm to set
         */
        public void setFormForm(String formForm) {
            this.formForm = formForm;
        }

    }

    /* class to define the each Save Session Save Input
     *
     *
     */
    private static class SaveSessionSaveInputs {
        private String inputTableName = "";
        private String inputColumnName = "";
        private String s_value = "";

        /**
         * @return the inputTableName
         */
        public String getInputTableName() {
            return inputTableName;
        }

        /**
         * @param inputTableName the inputTableName to set
         */
        public void setInputTableName(String inputTableName) {
            this.inputTableName = inputTableName;
        }

        /**
         * @return the inputColumnName
         */
        public String getInputColumnName() {
            return inputColumnName;
        }

        /**
         * @param inputColumnName the inputColumnName to set
         */
        public void setInputColumnName(String inputColumnName) {
            this.inputColumnName = inputColumnName;
        }

        /**
         * @return the s_value
         */
        public String getS_value() {
            return s_value;
        }

        /**
         * @param s_value the s_value to set
         */
        public void setS_value(String s_value) {
            this.s_value = s_value;
        }
    }


    /* class to define the each soap parameter
     *
     *
     */
    private static class soapParam {
        private String name = "";
        private Object value = null;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the value
         */
        public Object getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(Object value) {
            this.value = value;
        }

    }


    /* callretrieveSessionById11
     *
     * this method defines the soapParam structure to send to soap client
     *
     * @return soapParam[] array
     *
     */
    private static soapParam[] callretrieveSessionById11(String client_guid,Integer session_id, String session_type, String website_type) {
           /*
            * This code is to setup the params for calling callretrieveSessionById11
            */

          soapParam[] params = new soapParam[5];


           //param 0.name is the name of the method the soap call is running
           //param 0.value is the soap url  (no ending slashin url)
           params[0] = new soapParam();
           params[0].setName("retrieveSessionById11");
           params[0].setValue(SOAP_URL);

           //param 1 is client_guid
           params[1] = new soapParam();
           params[1].name = "client_guid";
           params[1].value = client_guid;
           //param 2 is session_id
           params[2] = new soapParam();
           params[2].name = "session_id";
           params[2].value = session_id;
           //param 3 is session_type
           params[3] = new soapParam();
           params[3].name = "session_type";
           params[3].value = session_type;
           //param 4 is website_type
           params[4] = new soapParam();
           params[4].name = "website_type";
           params[4].value = website_type;

        return params;
    }


    /* callnewSession
     *
     * this method defines the soapParam structure to send to soap client
     *
     * @return soapParam[] array
     *
     */
    private static soapParam[] callnewSession(String client_guid,String zipcode, String website_type) {
           /*
            * This code is to setup the params for calling callnewSession
            */

          soapParam[] params = new soapParam[4];


           //param 0.name is the name of the method the soap call is running
           //param 0.value is the soap url  (no ending slashin url)
           params[0] = new soapParam();
           params[0].setName("newSession");
           params[0].setValue(SOAP_URL);

           //param 1 is client_guid
           params[1] = new soapParam();
           params[1].name = "client_guid";
           params[1].value = client_guid;
           //param 2 is session_id
           params[2] = new soapParam();
           params[2].name = "zipcode";
           params[2].value = zipcode;
           //param 3 is website_type
           params[3] = new soapParam();
           params[3].name = "website_type";
           params[3].value = website_type;

        return params;
    }


    /* callsaveSession11
     *
     * this method defines the soapParam structure to send to soap client
     *
     * @return soapParam[] array
     *
     */
    private static soapParam[] callsaveSession11(String client_guid, Integer session_id, Object input_object, Integer validate) {
           /*
            * This code is to setup the params for calling callsaveSession11
            */


          soapParam[] params = new soapParam[5];


           //param 0.name is the name of the method the soap call is running
           //param 0.value is the soap url  (no ending slashin url)
           params[0] = new soapParam();
           params[0].setName("saveSession11");
           params[0].setValue(SOAP_URL);

           //param 1 is client_guid
           params[1] = new soapParam();
           params[1].name = "client_guid";
           params[1].value = client_guid;
           //param 2 is session_id
           params[2] = new soapParam();
           params[2].name = "session_id";
           params[2].value = session_id;
           //param 3 is website_type
           params[3] = new soapParam();
           params[3].name = "input_object";
           params[3].value = input_object;
           //param 4 is validationFlag
           params[4] = new soapParam();
           params[4].name = "validate";
           params[4].value = validate;

        return params;
    }


    /* saveSession11
     *
     * this method defines the soapParam structure to send to soap client
     *
     * @return soapParam[] array
     *
     */
    private static soapParam[] callretrieveSummarySessionResults11(String client_guid, Integer session_id) {
           /*
            * This code is to setup the params for calling callretrieveSummarySessionResults11
            */


          soapParam[] params = new soapParam[3];


           //param 0.name is the name of the method the soap call is running
           //param 0.value is the soap url  (no ending slashin url)
           params[0] = new soapParam();
           params[0].setName("retrieveSummarySessionResults11");
           params[0].setValue(SOAP_URL);

           //param 1 is client_guid
           params[1] = new soapParam();
           params[1].name = "client_guid";
           params[1].value = client_guid;
           //param 2 is session_id
           params[2] = new soapParam();
           params[2].name = "session_id";
           params[2].value = session_id;

        return params;
    }



    /* callAPIviaSoap menthod
     *
     * This method takes the soapParam[] array and creates a soap xml request
     * and send it to the soap server and then formats the return
     * it returns a String that contains
     *  1. soap request (xml)
     *  2. soap response (xml)
     *  3. data return in text format (name = value pairs)
     *
     */
    private static String callAPIViaSOAP(soapParam[] params) {

        //setup varible to collect output
        String returnOutput = "";

    try {


         //Create the connection
         SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
         SOAPConnection connection = scf.createConnection();
         SOAPFactory sf = SOAPFactory.newInstance();

         //Create the message
         MessageFactory mf = MessageFactory.newInstance();
         SOAPMessage message = mf.createMessage();

         //Create objects for the message parts
         SOAPPart soapPart = message.getSOAPPart();
         SOAPEnvelope envelope = soapPart.getEnvelope();
         envelope.addAttribute(sf.createName("xmlns:ns1"), (String)params[0].value+"/");
         envelope.addAttribute(sf.createName("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");
         envelope.addAttribute(sf.createName("xmlns:enc"), "http://www.w3.org/2003/05/soap-encoding");
         SOAPBody body = envelope.getBody();

         //Populate the body of the message
         Name bodyName = sf.createName(params[0].name);
         SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

         //loop thru param and add to message body
         for (int i = 1; i < params.length; i++) {
             if (params[i].name.equals((String)"input_object")) {

                 //set to match the
                 SaveSessionInput inObj = (SaveSessionInput)params[i].value;
                 //create main input_object element
                 SOAPElement inputObject = bodyElement.addChildElement(sf.createName(params[i].name));
                 inputObject.addAttribute(sf.createName("xsi:type"), "ns1:SaveSessionInput");
                 //extra inputs
                 SOAPElement extraInputObject = inputObject.addChildElement(sf.createName("extrInputs"));
                 extraInputObject.addAttribute(sf.createName("xsi:type"), "ns1:SaveSessionExtraInputs");
                    //extra inputs properties
                    //controller
                    SOAPElement extracontroller = extraInputObject.addChildElement(sf.createName("controller"));
                    extracontroller.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extracontroller.addTextNode(inObj.extraInputs.controller);
                    //action
                    SOAPElement extraaction = extraInputObject.addChildElement(sf.createName("action"));
                    extraaction.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extraaction.addTextNode(inObj.extraInputs.action);
                    //module
                    SOAPElement extramodule = extraInputObject.addChildElement(sf.createName("module"));
                    extramodule.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extramodule.addTextNode(inObj.extraInputs.module);
                    //nextPage
                    SOAPElement extranextPage = extraInputObject.addChildElement(sf.createName("nextPage"));
                    extranextPage.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extranextPage.addTextNode(inObj.extraInputs.nextPage);
                    //nextTask
                    SOAPElement extranextTask = extraInputObject.addChildElement(sf.createName("nextTask"));
                    extranextTask.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extranextTask.addTextNode(inObj.extraInputs.nextTask);
                    //finished_detailed
                    SOAPElement extrafinished_detailed = extraInputObject.addChildElement(sf.createName("finished_detailed"));
                    extrafinished_detailed.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extrafinished_detailed.addTextNode(inObj.extraInputs.finished_detailed);
                    //formGroup
                    SOAPElement extraformGroup = extraInputObject.addChildElement(sf.createName("formGroup"));
                    extraformGroup.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extraformGroup.addTextNode(inObj.extraInputs.formGroup);
                    //formForm
                    SOAPElement extraformForm = extraInputObject.addChildElement(sf.createName("formForm"));
                    extraformForm.addAttribute(sf.createName("xsi:type"), "xsd:string");
                    extraformForm.addTextNode(inObj.extraInputs.formForm);
                //saveInputs
                 SOAPElement saveInputObject = inputObject.addChildElement(sf.createName("saveInputs"));
                 saveInputObject.addAttribute(sf.createName("enc:itemType"), "ns1:SaveSessionSaveInputs");
                 saveInputObject.addAttribute(sf.createName("enc:arraySize"), Integer.toString(inObj.saveInputs.length));
                 saveInputObject.addAttribute(sf.createName("xsi:type"), "ns1:ArrayOfSaveSessionSaveInputs");
                 if (inObj.saveInputs.length > 0) {
                    //Save inputs item elements
                    for (int j = 0; j < inObj.saveInputs.length; j++) {                            
                        SOAPElement saveItem = saveInputObject.addChildElement(sf.createName("item"));
                        saveItem.addAttribute(sf.createName("xsi:type"), "ns1:SaveSessionSaveInputs");
                        //new for the sub save inputs element
                        //inputTableName
                        SOAPElement inputTableName = saveItem.addChildElement(sf.createName("inputTableName"));
                        inputTableName.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        inputTableName.addTextNode(inObj.saveInputs[j].inputTableName);
                        //inputTableName
                        SOAPElement inputColumnName = saveItem.addChildElement(sf.createName("inputColumnName"));
                        inputColumnName.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        inputColumnName.addTextNode(inObj.saveInputs[j].inputColumnName);
                        //inputTableName
                        SOAPElement s_value = saveItem.addChildElement(sf.createName("s_value"));
                        s_value.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        s_value.addTextNode(inObj.saveInputs[j].s_value);

                    }
                 }
                //lightInputs
                 SOAPElement lightInputsObject = inputObject.addChildElement(sf.createName("lightInputs"));
                 lightInputsObject.addAttribute(sf.createName("enc:itemType"), "ns1:SaveSessionLightInputs");
                 lightInputsObject.addAttribute(sf.createName("enc:arraySize"), Integer.toString(inObj.lightInputs.length));
                 lightInputsObject.addAttribute(sf.createName("xsi:type"), "ns1:ArrayOfSaveSessionLightInputs");
                 if (inObj.lightInputs.length > 0) {
                    //Save light inputs item elements
                    for (int j = 0; j < inObj.lightInputs.length; j++) {
                        SOAPElement saveItem = lightInputsObject.addChildElement(sf.createName("item"));
                        saveItem.addAttribute(sf.createName("xsi:type"), "ns1:SaveSessionExtraInputs");
                        //new for the sub save inputs element
                        //hoursPerDay
                        SOAPElement hoursPerDay = saveItem.addChildElement(sf.createName("hoursPerDay"));
                        hoursPerDay.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        hoursPerDay.addTextNode(inObj.lightInputs[j].hoursPerDay);
                        //instanceName
                        SOAPElement instanceName = saveItem.addChildElement(sf.createName("instanceName"));
                        instanceName.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        instanceName.addTextNode(inObj.lightInputs[j].instanceName);
                        //lampNumber
                        SOAPElement lampNumber = saveItem.addChildElement(sf.createName("lampNumber"));
                        lampNumber.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        lampNumber.addTextNode(inObj.lightInputs[j].lampNumber);
                        //lampType
                        SOAPElement lampType = saveItem.addChildElement(sf.createName("lampType"));
                        lampType.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        lampType.addTextNode(inObj.lightInputs[j].lampType);
                        //lampWatts
                        SOAPElement lampWatts = saveItem.addChildElement(sf.createName("lampWatts"));
                        lampWatts.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        lampWatts.addTextNode(inObj.lightInputs[j].lampWatts);
                        //roomName
                        SOAPElement roomName = saveItem.addChildElement(sf.createName("roomName"));
                        roomName.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        roomName.addTextNode(inObj.lightInputs[j].roomName);
                        //sessionId
                        SOAPElement sessionId = saveItem.addChildElement(sf.createName("sessionId"));
                        sessionId.addAttribute(sf.createName("xsi:type"), "xsd:string");
                        sessionId.addTextNode(inObj.lightInputs[j].sessionId);

                    }

                 }

             } else if (params[i].name.equals((String)"validate") || params[i].name.equals((String)"session_id")) {
                 //standard string
                 Name name = sf.createName(params[i].name);
                 SOAPElement param = bodyElement.addChildElement(name);
                 param.addAttribute(sf.createName("xsi:type"), "xsd:int");
                 param.addTextNode(Integer.toString((Integer)params[i].value));

             } else {
                 //standard string
                 Name name = sf.createName(params[i].name);
                 SOAPElement param = bodyElement.addChildElement(name);
                 param.addAttribute(sf.createName("xsi:type"), "xsd:string");
                 param.addTextNode((String) params[i].value);
                 
             }
         }


         //Display the request sent
         returnOutput = (returnOutput+"\nSOAP Request Sent:");
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         message.writeTo(out);
         returnOutput = (returnOutput+"\n"+out.toString());

         //Set the destination
         URL endpoint = new URL((String)params[0].value);
         //Send the message
         SOAPMessage response = connection.call(message, endpoint);

         //Close the connection
         connection.close();

         SOAPBody soapBody = response.getSOAPBody();



         //Display the response received
         returnOutput = (returnOutput+"\nSOAP Response Received:");


         //Create a transformer
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         //Retrieve content of the response
         Source content = response.getSOAPPart().getContent();

         //Display it on the console
         Writer outWriter = new StringWriter();
         StreamResult result = new StreamResult(outWriter);
         transformer.transform(content, result);
         returnOutput = (returnOutput+outWriter.toString());


         returnOutput = (returnOutput+"\nSOAP Return values:");




      //call response formater
      returnOutput = (returnOutput+"\n"+outHESSoapMessage(response));
      


      returnOutput = (returnOutput+"\n");
         //System.out.println();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

      return returnOutput;


    }


    /*outHESSoapMessage
     *
     * this method is designed to take the return soap message and
     * itterate through it and return the values as text name = value pairs
     *
     */
    private static String outHESSoapMessage(SOAPMessage message) {

        String values = null;
        String retString = "";

        try {
           // message.writeTo(System.out);

            SOAPPart sp = message.getSOAPPart();
            SOAPEnvelope env = sp.getEnvelope();
            SOAPHeader hdr = env.getHeader();
            SOAPBody bdy = env.getBody();

            Iterator ii = bdy.getChildElements();
            while (ii.hasNext()) {

                SOAPElement e = (SOAPElement)ii.next();
                String iname = e.getElementName().getLocalName();

            if (iname.equals("Fault")) {
                //show fault info
                Iterator jj = e.getChildElements();
                while (jj.hasNext()) {
                    SOAPElement ee = (SOAPElement)jj.next();
                    String lname = ee.getElementName().getLocalName();
                    values = ee.getValue();
                    retString = (retString + lname + " = " + values + "\n");
                }

            } else {

                Iterator jj = e.getChildElements();

            while (jj.hasNext()) {

                SOAPElement ee = (SOAPElement)jj.next();
                String jname = ee.getElementName().getLocalName();
                
                //return element

                Name attType = env.createName("type","xsi","http://www.w3.org/2001/XMLSchema-instance");
                String rType = ee.getAttributeValue(attType);

                if (rType.equals("xsd:string")) {
                    
                    values = ee.getValue();
                    retString = (retString + jname + " = " + values + "\n");


                } else if(rType.indexOf("Array") > 0) {

                Iterator kk = ee.getChildElements();

                    while (kk.hasNext()) {

                        SOAPElement eee = (SOAPElement)kk.next();
                        String kname = eee.getElementName().getLocalName();

                        if( kname != null && kname.equals("item") ) {

                            //if items iterate thru items
                            Iterator ll = eee.getChildElements();
                            while (ll.hasNext()) {

                                SOAPElement eeee = (SOAPElement)ll.next();
                                String lname = eeee.getElementName().getLocalName();
                                values = eeee.getValue();
                                retString = (retString + lname + " = " + values + "\n");

                            
                            } // end while ll
                        }
                    } //end while kk

                } else {
                    //

                    Name attType1 = env.createName("type","xsi","http://www.w3.org/2001/XMLSchema-instance");
                    String rType1 = ee.getAttributeValue(attType1);

                    if (rType1.equals("ns1:RetrieveSessionSummaryResponse")) {

                        Iterator kk = ee.getChildElements();

                        while (kk.hasNext()) {

                            SOAPElement eee = (SOAPElement)kk.next();
                            String kname = eee.getElementName().getLocalName();

                            if( kname != null && (kname.equals("existing") || kname.equals("efficient") || kname.equals("existingHome") || kname.equals("withUpgrades") ) ) {

                                values = eee.getValue();
                                retString = (retString + kname + " = " + values + "\n");

                                Iterator ll = eee.getChildElements();
                                while (ll.hasNext()) {

                                    SOAPElement eeee = (SOAPElement)ll.next();
                                    String lname = eeee.getElementName().getLocalName();
                                    values = eeee.getValue();
                                    retString = (retString + " - "+ lname + " = " + values + "\n");


                                } // end while ll
                            } else {

                            values = eee.getValue();
                            retString = (retString + kname + " = " + values + "\n");

                            }


                        } //end while kk



                    } else {


                        Iterator kk = ee.getChildElements();

                        while (kk.hasNext()) {

                            SOAPElement eeee = (SOAPElement)kk.next();
                            String lname = eeee.getElementName().getLocalName();
                            values = eeee.getValue();
                            retString = (retString + lname + " = " + values + "\n");
                        } //end while kk
                    }
                    

                } //end if rType

                } // end while jj

                }
            } // end while ii


        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return retString;
    }

}