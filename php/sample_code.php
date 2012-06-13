<?php

// Preliminary PHP 5 Sample Code for the Home Energy Score API
//
// By: G. K. Homan (gkhoman@lbl.gov)
// this version updated June 12th, 2012
// based on previous work by Ben Johansen (ben@bighead.net), Adrian DeVivo (adrian@bighead.net), Chris Ralph (cralph@bighead.net), and Leo Rainer (lirainer@lbl.gov).
//

//--------------------------------------------------------------------
// Set the WSDL location, and the initial parameters for a new session
//--------------------------------------------------------------------

// Production - Requires production 3scale key and authorization from DOE
// $session_wsdl_url = 'http://tool-proapp.hescloud.net/session/wsdl/';
// $calc_wsdl_url = 'http://tool-proapp.hescloud.net/calculate/wsdl/';

// Sandbox - use for development and testing
$session_wsdl = 'http://api-l.hescloud.net/session/wsdl';
$calculate_wsdl = 'http://api-l.hescloud.net/calculate/wsdl';

//initiate PHP 5 soap clients with the selected wsld

$session_client = new SoapClient($session_wsdl, array('exceptions' => 0,'trace' => 1));
$calculate_client = new SoapClient($calculate_wsdl, array('exceptions' => 0,'trace' => 1));

$client_guid = 'INSERT YOUR_3SCALE_KEY_HERE';

$address_data = array
(
'Address' => '1 Cyclotron Road',
'City' => 'Berkeley',
'State' => 'CA',
'Zip' => '94720'
);

$authentication_type = 'DOE'; //only DOE for now
$qa_number = 'INSERT_YOUR_CERTIFIED_ASSESOR_NUMBER_HERE'; // must be obtained from DOE
$gui_session_id = '123'; // Just an arbitrary value

//----------------------------------------------------
// call the new session and get the session ID number
//----------------------------------------------------

$result = $session_client->newSessionFromAddress(array('client_guid' => $client_guid, 'address_data' => $address_data, 'ca_number' => $qa_number, 'authentication_type' => $authentication_type, 'gui_session_id' => $gui_session_id));

if (is_soap_fault($result)) {
    trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}


$temp = $result->retrieveSessionByIdResponse->sessionInput; // this selects the last element within formSchema, itself a 2-dimensional array
$temp = end($temp); // this is a 32 element array of the classProperties
$session_value_array = $temp->sessionValue; //select just the sessionValue elementm the contents of which will look something like this:
//sessionId^******;uniqueName^;zipcode^94720;zipCity^berkeley;zipState^CA;calculated^0
$sessionValueSubArray = explode(";",$session_value_array); //break that array into the key-value pairs on the semi-colon
$sessionValueSubArray = explode("^",$sessionValueSubArray[0]);  // break that up on the caret character
$session_id = $sessionValueSubArray[1]; // take the second field which is the ID number

echo $session_id; // just to check, print the session ID
// for human readable display in a browser wrap in PRE tags, here and below.


//-------------------------------------------
// Setup the inputs to customize the session
//-------------------------------------------

$website_type = '2'; // scoring tool

$extra_inputs = array();
$session_inputs = array();
//page 1 (Audit-Details)
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"auditDate",'value'=>"03/14/2012");
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"year",'value'=>"1976");
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"numberBedrooms",'value'=>"5");
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"storiesAboveGround",'value'=>"2");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"ceilingHeight",'value'=>"9.00");
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"floorArea",'value'=>"2500");
$session_inputs[] = array('inputTableName'=>"whole_house_input",'inputColumnName'=>"houseOrientation",'value'=>"180");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"airLeakage50ip",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"airSealingPresent",'value'=>"1");

//page 2 (Attic-Roof)
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"roofConstruction",'value'=>"rfwf13lc");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"roofAbsorptance",'value'=>".65");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"atticType",'value'=>"uncond_attic");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"ceilingConstruction",'value'=>"ecwf11");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"foundationType",'value'=>"uncond_base");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"floorConstruction",'value'=>"efwf11ca");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"foundationSideInsulationRValue",'value'=>"11");

//page 3 (Walls)
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"wallsSameAllSides",'value'=>"1");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"wallConstructionFront",'value'=>"ewih13vi");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"wallConstructionBack",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"wallConstructionRight",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"wallConstructionLeft",'value'=>"");

//page 4 (Windows-Skylights)
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"skylightsPresent",'value'=>"0");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"skylightType",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"skylightArea",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowAreaFront",'value'=>"100");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowAreaRight",'value'=>"100");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowAreaBack",'value'=>"100");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowAreaLeft",'value'=>"100");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowTypesDifferBySide",'value'=>"1");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowTypeFront",'value'=>"dcaw");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowUValueFront",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowSolarGainFront",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowTypeRight",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowUValueRight",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowSolarGainRight",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowTypeBack",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowUValueBack",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowSolarGainBack",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowTypeLeft",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowUValueLeft",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"windowSolarGainLeft",'value'=>"");

//page 5 (Heating-Cooling)
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"heatingType",'value'=>"gfn");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"heatingYearPurchased",'value'=>"2005");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"heatingEfficiency",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"coolingType",'value'=>"ehp");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"coolingYearPurchased",'value'=>"2005");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"coolingEfficiency",'value'=>"");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"ductLocation",'value'=>"uncond_attic");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"ductInsulationPresent",'value'=>"1");
$session_inputs[] = array('inputTableName'=>"heat_cool_input",'inputColumnName'=>"ductSealingPresent",'value'=>"1");
$session_inputs[] = array('inputTableName'=>"hot_water_input",'inputColumnName'=>"hwFuel",'value'=>"ele");
$session_inputs[] = array('inputTableName'=>"hot_water_input",'inputColumnName'=>"hwYearPurchased",'value'=>"2005");
$session_inputs[] = array('inputTableName'=>"hot_water_input",'inputColumnName'=>"hwEnergyFactor",'value'=>"");
$session_inputs[] = array('inputTableName'=>"hot_water_input",'inputColumnName'=>"hwFromBoiler",'value'=>"separate");

//This signifies if the session will go thru a calc process upon save
$validation_flag = '1';   //0 = just save, 1 = save and calc

$input_array = array();
$input_array['client_guid']     = $client_guid;
$input_array['session_id']      = $session_id;
$input_array['gui_session_id']  = $gui_session_id;
$input_array['validate']        = $validation_flag;
$input_array['meta_data']       = array('formGroup'=>'detailed', 'formForm'=>'Heating-Cooling'); // Which page are we on (doesn't really apply if we save the whole session at once)
$input_array['session_inputs']  = array('session_input' => $session_inputs);

//------------------------------------------
// Save the inputs and run the calculations
//------------------------------------------
$response = $session_client->saveLabelSession($input_array);

echo print_r($response,true);

if (is_soap_fault($response)) {
    trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}

//--------------------------------------
// Get the detailed results from the run
//--------------------------------------
$response = $session_client->retrieveLabelDetailResults(array('client_guid'=>$client_guid,'session_id'=>$session_id));

echo print_r($response,true);

if (is_soap_fault($response)) {
    trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}

//-------------------------------------------------
// Generate the label (returns URLs to label files)
//-------------------------------------------------

$response = $calculate_client->generateHesLabel(array('client_guid'=>$client_guid,'session_id'=>$session_id,'force_regenerate'=>1)); // The last flag forces it to regenerate the label if one already exists

echo print_r($response,true);

if (is_soap_fault($response)) {
    trigger_error("SOAP Fault: (faultcode: {$result->faultcode}, faultstring: {$result->faultstring})", E_USER_ERROR);
}

?>
