<?php 
/*
* PHP 5 Sample Sample SOAP Calls
*
* By: Chris Ralph (cralph@bighead.net)
* updated to 1.1 methods by: Ben Johansen (ben@bighead.net)
*/


//initiate PHP 5 soap client with the public wsdl on the HES-Sandbox system
$client = new SoapClient('http://sbapp.hescloud.net/session/wsdl', array('exceptions' => 0,'trace' => 1));

//This is your 3scale client guid, you must get this from HES.
$params['client_guid'] = 'INSERT YOUR 3SCALE Key HERE';

//use a valid zip code to create a new session
$params['zipcode'] = '98661';

//This signifies the website type, just leave as 0
$params['website_type'] = '0';

//This signifies if the session will go thru a calc process upon save
//0 = just save and 1 = save and calc
$params['validate'] = '0';


//------------------------------------------------------
//Now lets create a new session, and grab the valid session id.
//------------------------------------------------------
$result = $client->newSession($params['client_guid'], $params['zipcode'], $params['website_type']);
if (is_soap_fault($result)) {
	trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}

//a lot of data is returned, this is designed to build the forms on a GUI website, but we only need the session id.
//have to extract it from small array returned
$last_result_array = end($result);//$result[61];
$sessionValueArray = $last_result_array->sessionValue;
$sessionValueSubArray = explode(";",$sessionValueArray);
$sessionValueSubSubArray = explode("^",$sessionValueSubArray[0]);
$params['session_id'] = $sessionValueSubSubArray[1];

//------------------------------------------------------
//now lets save session
//------------------------------------------------------

//Setup the classes that will be used by saveSession11

class SaveSessionInput {
  /** 
   * @var SaveSessionExtraInputs $extraInputs
  */
  public $extraInputs;
  /** 
   * @var SaveSessionSaveInputs[] $saveInputs
  */
  public $saveInputs = array();
  /** 
   * @var SaveSessionLightInputs[] $lightInputs
  */
  public $lightInputs = array();
}


class SaveSessionExtraInputs {
  /** 
   * @var string $controller
  */
  public $controller = '';
  /** 
   * @var string $action
  */
  public $action = '';
  /** 
   * @var string $module
  */
  public $module = '';
  /** 
   * @var string $nextPage
  */
  public $nextPage = '';
  /** 
   * @var string $nextTask
  */
  public $nextTask = '';
  /** 
   * @var string $finished-detailed
  */
  public $finished_detailed = '';
  /** 
   * @var string $formGroup
  */
  public $formGroup = '';
  /** 
   * @var string $formForm
  */
  public $formForm = '';
}


class SaveSessionSaveInputs {
  /** 
   * @var string $inputTableName
  */
  public $inputTableName = '';
  /** 
   * @var string $inputColumnName
  */
  public $inputColumnName = '';
  /** 
   * @var string $s_value
  */
  public $s_value = '';
}


class SaveSessionLightInputs {
  /** 
   * @var string $sessionId
  */
  public $sessionId;
  /** 
   * @var string $roomName
  */
  public $roomName;
  /** 
   * @var string $instanceNumber
  */
  public $instanceNumber;
  /** 
   * @var string $lampNumber
  */
  public $lampNumber;
  /** 
   * @var string $lampType
  */
  public $lampType;
  /** 
   * @var string $lampWatts
  */
  public $lampWatts;
  /** 
   * @var string $hoursPerDay
  */
  public $hoursPerDay;
}

//lets load the data for the saveSession using the classes above

//instantiate input_object for saveSession11
$input_object = new SaveSessionInput();
//populate the extra_inputs object with save values
$extra_inputs_object = new SaveSessionExtraInputs();
            $extra_inputs_object->controller = "consumer";
            $extra_inputs_object->action = "save-and-go";
            $extra_inputs_object->module = "default";
            $extra_inputs_object->nextPage = "/consumer/quick-building-design";
            $extra_inputs_object->nextTask = "next";
            $extra_inputs_object->formGroup = "Quick";
            $extra_inputs_object->formForm = "General";
//populate the input_object_array with input_save_objects with save values
$input_save_object = new SaveSessionSaveInputs();            
            $input_save_object->inputTableName = "whole_house_input";
            $input_save_object->inputColumnName = "uniqueName";
            $input_save_object->s_value ="php test";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="emailAddress";
            $input_save_object->s_value = "";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="purpose";
            $input_save_object->s_value = "0";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName = "whole_house_input";
            $input_save_object->inputColumnName ="purposeOther";
            $input_save_object->s_value = "";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="address";
            $input_save_object->s_value ="";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="city";
            $input_save_object->s_value ="Portland";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="stateCode";
            $input_save_object->s_value ="WA";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="weatherCity";
            $input_save_object->s_value ="Portland OR";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="year";
            $input_save_object->s_value ="1971";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="occupants_0_5";
            $input_save_object->s_value ="0";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="occupants_6_13";
            $input_save_object->s_value ="1";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="occupants_14_64";
            $input_save_object->s_value ="2";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="occupants_65_plus";
            $input_save_object->s_value ="0";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="priceElect";
            $input_save_object->s_value ="0.076";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="priceGas";
            $input_save_object->s_value ="1.31";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="priceLpg";
            $input_save_object->s_value ="2.38";
            $inputs_object_array[] = $input_save_object;            
            $input_save_object->inputTableName ="whole_house_input";
            $input_save_object->inputColumnName ="priceOil";
            $input_save_object->s_value ="3.16";
            $inputs_object_array[] = $input_save_object;

//load the input_object class with save objects
$input_object->extraInputs = $extra_inputs_object;
$input_object->saveInputs = $inputs_object_array;
$input_object->lightInputs = array();

//now we call the saveSession11 method, and get the restult
//in this case we are going to force a calc
$params['validate'] = 1;
$result = $client->saveSession11($params['client_guid'], $params['session_id'], $input_object, $params['validate']);
$keyText = "Result: \n";
$keyText .= print_r($result, true);
echo $keyText ."\n\n";

//------------------------------------------------------
//now lets Retrieve the results
//------------------------------------------------------

//now we call the retrieveSummarySessionResults11 method to grab a section of the calculated results, there are many sections (and their associated methods), see 3scale documentation
$result = $client->retrieveSummarySessionResults11($params['client_guid'], $params['session_id']);
$keyText = "Result: \n";
$keyText .= print_r($result, true);
echo $keyText ."\n\n";
if (is_soap_fault($result)) {
	trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}


?>
