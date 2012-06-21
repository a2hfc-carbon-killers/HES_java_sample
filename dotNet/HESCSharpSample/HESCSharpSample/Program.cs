//------------------------------------------------------------------------------
// HES API Sample Calls in C#.NET
// By: RJ Garcia (rj@bighead.net)
// Date Modified: 2012-06-20 03:30
// Port of the release version of the php api for label done by G. K. Homan (gkhoman@lbl.gov) and the old .NET from Ben Johansen (ben@bighead.net)
// For more documentation, please see the php version of this api.
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
            static void Main(string[] args)
            {
                if (args.Length < 1)
                {

                    //instantiate the sessionService
                    HesAPI_SessionPortClient session_service = new HesAPI_SessionPortClient();
                    HesAPI_CalculatePortClient calculate_service = new HesAPI_CalculatePortClient();

                    //initialize parameters for session_service call
                    String client_guid = "INSERT 3SCALE CLIENT KEY HERE";
                    
                    address_data a_data = new address_data();
                    a_data.Address = "1 Cyclotron Road";
                    a_data.City = "Berkeley";
                    a_data.State = "CA";
                    a_data.Zip = "94720";

                    String ca_number = "INSERT DOE CA NUMBER HERE"; // must be obtained from DOE
                    String authentication_type = "DOE"; //only DOE for now
                    String gui_session_id = "123"; // Just an arbitrary value to make this session unique, must be nummeric, not alphanumeric

                    //create empty place holder variables to be passed as referenced into wsdl call
                    String return_comment;
                    String read_only;
                    AddressList a_list = new AddressList();
                    sessionInput_type[] retrieve_sessionid_by_response = new sessionInput_type[20];

                    int result = session_service.newSessionFromAddress(client_guid, a_data, ca_number, authentication_type, gui_session_id, out return_comment, out read_only, out a_list, out retrieve_sessionid_by_response);

                    if (result > 0) //1 is success, 0 is failure
                    {
                        //get last row of input_types
                        int length = retrieve_sessionid_by_response.Length;
                        sessionInput_type last_row = retrieve_sessionid_by_response[length - 1];
                        String[] session_response_parts = last_row.sessionValue.Split(';'); //value is something like this 
                        String[] session_value_parts = session_response_parts[0].Split('^');
                        String session_id = session_value_parts[1];

                        Console.WriteLine("Session_id: " + session_id);

                        session_input[] session_inputs = new session_input[53];

                        //page 1 (Audit-Details)
                        session_inputs[0] = new session_input();
                        session_inputs[0].inputTableName = "whole_house_input";
                        session_inputs[0].inputColumnName = "auditDate";
                        session_inputs[0].value = "03/14/2012";

                        session_inputs[1] = new session_input();
                        session_inputs[1].inputTableName = "whole_house_input";
                        session_inputs[1].inputColumnName = "year";
                        session_inputs[1].value = "1976";

                        session_inputs[2] = new session_input();
                        session_inputs[2].inputTableName = "whole_house_input";
                        session_inputs[2].inputColumnName = "numberBedrooms";
                        session_inputs[2].value = "5";

                        session_inputs[2] = new session_input();
                        session_inputs[2].inputTableName = "whole_house_input";
                        session_inputs[2].inputColumnName = "storiesAboveGround";
                        session_inputs[2].value = "2";

                        session_inputs[3] = new session_input();
                        session_inputs[3].inputTableName = "heat_cool_input";
                        session_inputs[3].inputColumnName = "ceilingHeight";
                        session_inputs[3].value = "9.00";

                        session_inputs[4] = new session_input();
                        session_inputs[4].inputTableName = "whole_house_input";
                        session_inputs[4].inputColumnName = "floorArea";
                        session_inputs[4].value = "2500";

                        session_inputs[5] = new session_input();
                        session_inputs[5].inputTableName = "whole_house_input";
                        session_inputs[5].inputColumnName = "houseOrientation";
                        session_inputs[5].value = "180";

                        session_inputs[6] = new session_input();
                        session_inputs[6].inputTableName = "heat_cool_input";
                        session_inputs[6].inputColumnName = "airLeakage50ip";
                        session_inputs[6].value = "";

                        session_inputs[7] = new session_input();
                        session_inputs[7].inputTableName = "heat_cool_input";
                        session_inputs[7].inputColumnName = "airSealingPresent";
                        session_inputs[7].value = "1";


                        //page 2 (Attic-Roof)
                        session_inputs[8] = new session_input();
                        session_inputs[8].inputTableName = "heat_cool_input";
                        session_inputs[8].inputColumnName = "roofConstruction";
                        session_inputs[8].value = "rfwf13lc";

                        session_inputs[9] = new session_input();
                        session_inputs[9].inputTableName = "heat_cool_input";
                        session_inputs[9].inputColumnName = "roofAbsorptance";
                        session_inputs[9].value = ".65";

                        session_inputs[10] = new session_input();
                        session_inputs[10].inputTableName = "heat_cool_input";
                        session_inputs[10].inputColumnName = "atticType";
                        session_inputs[10].value = "uncond_attic";

                        session_inputs[11] = new session_input();
                        session_inputs[11].inputTableName = "heat_cool_input";
                        session_inputs[11].inputColumnName = "ceilingConstruction";
                        session_inputs[11].value = "ecwf11";

                        session_inputs[12] = new session_input();
                        session_inputs[12].inputTableName = "heat_cool_input";
                        session_inputs[12].inputColumnName = "foundationType";
                        session_inputs[12].value = "uncond_base";

                        session_inputs[13] = new session_input();
                        session_inputs[13].inputTableName = "heat_cool_input";
                        session_inputs[13].inputColumnName = "floorConstruction";
                        session_inputs[13].value = "efwf11ca";

                        session_inputs[14] = new session_input();
                        session_inputs[14].inputTableName = "heat_cool_input";
                        session_inputs[14].inputColumnName = "foundationSideInsulationRValue";
                        session_inputs[14].value = "11";


                        //page 3 (Walls)
                        session_inputs[15] = new session_input();
                        session_inputs[15].inputTableName = "heat_cool_input";
                        session_inputs[15].inputColumnName = "wallsSameAllSides";
                        session_inputs[15].value = "1";

                        session_inputs[16] = new session_input();
                        session_inputs[16].inputTableName = "heat_cool_input";
                        session_inputs[16].inputColumnName = "wallConstructionFront";
                        session_inputs[16].value = "ewih13vi";

                        session_inputs[17] = new session_input();
                        session_inputs[17].inputTableName = "heat_cool_input";
                        session_inputs[17].inputColumnName = "wallConstructionBack";
                        session_inputs[17].value = "";

                        session_inputs[18] = new session_input();
                        session_inputs[18].inputTableName = "heat_cool_input";
                        session_inputs[18].inputColumnName = "wallConstructionRight";
                        session_inputs[18].value = "";

                        session_inputs[19] = new session_input();
                        session_inputs[19].inputTableName = "heat_cool_input";
                        session_inputs[19].inputColumnName = "wallConstructionLeft";
                        session_inputs[19].value = "";


                        //page 4 (Windows-Skylights)
                        session_inputs[20] = new session_input();
                        session_inputs[20].inputTableName = "heat_cool_input";
                        session_inputs[20].inputColumnName = "skylightsPresent";
                        session_inputs[20].value = "0";

                        session_inputs[21] = new session_input();
                        session_inputs[21].inputTableName = "heat_cool_input";
                        session_inputs[21].inputColumnName = "skylightType";
                        session_inputs[21].value = "";

                        session_inputs[22] = new session_input();
                        session_inputs[22].inputTableName = "heat_cool_input";
                        session_inputs[22].inputColumnName = "skylightArea";
                        session_inputs[22].value = "";

                        session_inputs[23] = new session_input();
                        session_inputs[23].inputTableName = "heat_cool_input";
                        session_inputs[23].inputColumnName = "windowAreaFront";
                        session_inputs[23].value = "100";

                        session_inputs[24] = new session_input();
                        session_inputs[24].inputTableName = "heat_cool_input";
                        session_inputs[24].inputColumnName = "windowAreaRight";
                        session_inputs[24].value = "100";

                        session_inputs[25] = new session_input();
                        session_inputs[25].inputTableName = "heat_cool_input";
                        session_inputs[25].inputColumnName = "windowAreaBack";
                        session_inputs[25].value = "100";

                        session_inputs[26] = new session_input();
                        session_inputs[26].inputTableName = "heat_cool_input";
                        session_inputs[26].inputColumnName = "windowAreaLeft";
                        session_inputs[26].value = "100";

                        session_inputs[27] = new session_input();
                        session_inputs[27].inputTableName = "heat_cool_input";
                        session_inputs[27].inputColumnName = "windowTypesDifferBySide";
                        session_inputs[27].value = "1";

                        session_inputs[28] = new session_input();
                        session_inputs[28].inputTableName = "heat_cool_input";
                        session_inputs[28].inputColumnName = "windowTypeFront";
                        session_inputs[28].value = "dcaw";

                        session_inputs[29] = new session_input();
                        session_inputs[29].inputTableName = "heat_cool_input";
                        session_inputs[29].inputColumnName = "windowUValueFront";
                        session_inputs[29].value = "";

                        session_inputs[30] = new session_input();
                        session_inputs[30].inputTableName = "heat_cool_input";
                        session_inputs[30].inputColumnName = "windowSolarGainFront";
                        session_inputs[30].value = "";

                        session_inputs[31] = new session_input();
                        session_inputs[31].inputTableName = "heat_cool_input";
                        session_inputs[31].inputColumnName = "windowTypeRight";
                        session_inputs[31].value = "";

                        session_inputs[32] = new session_input();
                        session_inputs[32].inputTableName = "heat_cool_input";
                        session_inputs[32].inputColumnName = "windowUValueRight";
                        session_inputs[32].value = "";

                        session_inputs[33] = new session_input();
                        session_inputs[33].inputTableName = "heat_cool_input";
                        session_inputs[33].inputColumnName = "windowSolarGainRight";
                        session_inputs[33].value = "";

                        session_inputs[34] = new session_input();
                        session_inputs[34].inputTableName = "heat_cool_input";
                        session_inputs[34].inputColumnName = "windowTypeBack";
                        session_inputs[34].value = "";

                        session_inputs[35] = new session_input();
                        session_inputs[35].inputTableName = "heat_cool_input";
                        session_inputs[35].inputColumnName = "windowUValueBack";
                        session_inputs[35].value = "";

                        session_inputs[36] = new session_input();
                        session_inputs[36].inputTableName = "heat_cool_input";
                        session_inputs[36].inputColumnName = "windowSolarGainBack";
                        session_inputs[36].value = "";

                        session_inputs[37] = new session_input();
                        session_inputs[37].inputTableName = "heat_cool_input";
                        session_inputs[37].inputColumnName = "windowTypeLeft";
                        session_inputs[37].value = "";

                        session_inputs[38] = new session_input();
                        session_inputs[38].inputTableName = "heat_cool_input";
                        session_inputs[38].inputColumnName = "windowUValueLeft";
                        session_inputs[38].value = "";

                        session_inputs[39] = new session_input();
                        session_inputs[39].inputTableName = "heat_cool_input";
                        session_inputs[39].inputColumnName = "windowSolarGainLeft";
                        session_inputs[39].value = "";


                        //page 5 (Heating-Cooling)
                        session_inputs[40] = new session_input();
                        session_inputs[40].inputTableName = "heat_cool_input";
                        session_inputs[40].inputColumnName = "heatingType";
                        session_inputs[40].value = "gfn";

                        session_inputs[41] = new session_input();
                        session_inputs[41].inputTableName = "heat_cool_input";
                        session_inputs[41].inputColumnName = "heatingYearPurchased";
                        session_inputs[41].value = "2005";

                        session_inputs[42] = new session_input();
                        session_inputs[42].inputTableName = "heat_cool_input";
                        session_inputs[42].inputColumnName = "heatingEfficiency";
                        session_inputs[42].value = "";

                        session_inputs[43] = new session_input();
                        session_inputs[43].inputTableName = "heat_cool_input";
                        session_inputs[43].inputColumnName = "coolingType";
                        session_inputs[43].value = "ehp";

                        session_inputs[44] = new session_input();
                        session_inputs[44].inputTableName = "heat_cool_input";
                        session_inputs[44].inputColumnName = "coolingYearPurchased";
                        session_inputs[44].value = "2005";

                        session_inputs[45] = new session_input();
                        session_inputs[45].inputTableName = "heat_cool_input";
                        session_inputs[45].inputColumnName = "coolingEfficiency";
                        session_inputs[45].value = "";

                        session_inputs[46] = new session_input();
                        session_inputs[46].inputTableName = "heat_cool_input";
                        session_inputs[46].inputColumnName = "ductLocation";
                        session_inputs[46].value = "uncond_attic";

                        session_inputs[47] = new session_input();
                        session_inputs[47].inputTableName = "heat_cool_input";
                        session_inputs[47].inputColumnName = "ductInsulationPresent";
                        session_inputs[47].value = "1";

                        session_inputs[48] = new session_input();
                        session_inputs[48].inputTableName = "heat_cool_input";
                        session_inputs[48].inputColumnName = "ductSealingPresent";
                        session_inputs[48].value = "1";

                        session_inputs[49] = new session_input();
                        session_inputs[49].inputTableName = "hot_water_input";
                        session_inputs[49].inputColumnName = "hwFuel";
                        session_inputs[49].value = "ele";

                        session_inputs[50] = new session_input();
                        session_inputs[50].inputTableName = "hot_water_input";
                        session_inputs[50].inputColumnName = "hwYearPurchased";
                        session_inputs[50].value = "2005";

                        session_inputs[51] = new session_input();
                        session_inputs[51].inputTableName = "hot_water_input";
                        session_inputs[51].inputColumnName = "hwEnergyFactor";
                        session_inputs[51].value = "";

                        session_inputs[52] = new session_input();
                        session_inputs[52].inputTableName = "hot_water_input";
                        session_inputs[52].inputColumnName = "hwFromBoiler";
                        session_inputs[52].value = "separate";

                        int validate = 1;
                        meta_data m_data = new meta_data();
                        m_data.formForm = "Heating-Cooling";
                        m_data.formGroup = "detailed";

                        //setting up some variables to be passed as reference to the save method
                        validationError[] validation_errors;
                        int calculated;

                        int i_session_id = 0;

                        try
                        {
                            i_session_id = Convert.ToInt32(session_id);
                        }
                        catch (OverflowException)
                        {
                            Console.WriteLine("Failed to convert session_id from string to int: Overflow");
                        }
                        catch (FormatException)
                        {
                            Console.WriteLine("Failed to convert session_id from string to int: Incorrect Format");
                        }
                        

                        result = session_service.saveLabelSession(client_guid, i_session_id, gui_session_id, validate, m_data, session_inputs, out return_comment, out calculated, out validation_errors);

                        if (result > 0)
                        {
                            Console.WriteLine("saveLabelSession: " + return_comment);

                            int force_refridgerate = 1;
                            hes.lbl.gov.scoring_tool.calculate.pdf_urls urls;

                            result = calculate_service.generateHesLabel(client_guid, i_session_id, force_refridgerate, out return_comment, out urls);

                            if (result > 0)
                            {
                                Console.WriteLine("Calculation: " + return_comment);
                            }
                            Console.WriteLine("Finished!");
                        }
                    }
                }
            }
        }
}
