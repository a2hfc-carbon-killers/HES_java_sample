﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.269
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

[assembly: System.Runtime.Serialization.ContractNamespaceAttribute("http://hes.lbl.gov/scoring_tool/calculate", ClrNamespace="hes.lbl.gov.scoring_tool.calculate")]

namespace hes.lbl.gov.scoring_tool.calculate
{
    using System.Runtime.Serialization;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="summaryGraphDataType", Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
    public partial class summaryGraphDataType : object, System.Runtime.Serialization.IExtensibleDataObject
    {
        
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        private float widthField;
        
        private float homeField;
        
        private float heatingField;
        
        private float coolingField;
        
        private float water_heatingField;
        
        private float major_appliancesField;
        
        private float lightingField;
        
        private float small_appliancesField;
        
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData
        {
            get
            {
                return this.extensionDataField;
            }
            set
            {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public float width
        {
            get
            {
                return this.widthField;
            }
            set
            {
                this.widthField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=1)]
        public float home
        {
            get
            {
                return this.homeField;
            }
            set
            {
                this.homeField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=2)]
        public float heating
        {
            get
            {
                return this.heatingField;
            }
            set
            {
                this.heatingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=3)]
        public float cooling
        {
            get
            {
                return this.coolingField;
            }
            set
            {
                this.coolingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=4)]
        public float water_heating
        {
            get
            {
                return this.water_heatingField;
            }
            set
            {
                this.water_heatingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=5)]
        public float major_appliances
        {
            get
            {
                return this.major_appliancesField;
            }
            set
            {
                this.major_appliancesField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=6)]
        public float lighting
        {
            get
            {
                return this.lightingField;
            }
            set
            {
                this.lightingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=7)]
        public float small_appliances
        {
            get
            {
                return this.small_appliancesField;
            }
            set
            {
                this.small_appliancesField = value;
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="summaryResultsType", Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
    public partial class summaryResultsType : object, System.Runtime.Serialization.IExtensibleDataObject
    {
        
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        private float TotalField;
        
        private float HeatingField;
        
        private float CoolingField;
        
        private float HotWaterField;
        
        private float LargeAppliancesField;
        
        private float SmallAppliancesField;
        
        private float LightingField;
        
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData
        {
            get
            {
                return this.extensionDataField;
            }
            set
            {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public float Total
        {
            get
            {
                return this.TotalField;
            }
            set
            {
                this.TotalField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=1)]
        public float Heating
        {
            get
            {
                return this.HeatingField;
            }
            set
            {
                this.HeatingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=2)]
        public float Cooling
        {
            get
            {
                return this.CoolingField;
            }
            set
            {
                this.CoolingField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=3)]
        public float HotWater
        {
            get
            {
                return this.HotWaterField;
            }
            set
            {
                this.HotWaterField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=4)]
        public float LargeAppliances
        {
            get
            {
                return this.LargeAppliancesField;
            }
            set
            {
                this.LargeAppliancesField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=5)]
        public float SmallAppliances
        {
            get
            {
                return this.SmallAppliancesField;
            }
            set
            {
                this.SmallAppliancesField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=6)]
        public float Lighting
        {
            get
            {
                return this.LightingField;
            }
            set
            {
                this.LightingField = value;
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="pdf_urls", Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
    public partial class pdf_urls : object, System.Runtime.Serialization.IExtensibleDataObject
    {
        
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        private string originalField;
        
        private string page1Field;
        
        private string page2Field;
        
        private string page3Field;
        
        private string page4Field;
        
        private string page5Field;
        
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData
        {
            get
            {
                return this.extensionDataField;
            }
            set
            {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string original
        {
            get
            {
                return this.originalField;
            }
            set
            {
                this.originalField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string page1
        {
            get
            {
                return this.page1Field;
            }
            set
            {
                this.page1Field = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string page2
        {
            get
            {
                return this.page2Field;
            }
            set
            {
                this.page2Field = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string page3
        {
            get
            {
                return this.page3Field;
            }
            set
            {
                this.page3Field = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string page4
        {
            get
            {
                return this.page4Field;
            }
            set
            {
                this.page4Field = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, EmitDefaultValue=false)]
        public string page5
        {
            get
            {
                return this.page5Field;
            }
            set
            {
                this.page5Field = value;
            }
        }
    }
}


[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ServiceModel.ServiceContractAttribute(Namespace="http://hes.lbl.gov/scoring_tool/calculate", ConfigurationName="HesAPI_CalculatePort")]
public interface HesAPI_CalculatePort
{
    
    // CODEGEN: Generating message contract since element name client_guid from namespace http://hes.lbl.gov/scoring_tool/calculate is not marked nillable
    [System.ServiceModel.OperationContractAttribute(Action="http://api-l.hescloud.net/calculate/#calcBaseHome", ReplyAction="*")]
    calcBaseHomeResponse calcBaseHome(calcBaseHomeRequest request);
    
    // CODEGEN: Generating message contract since element name client_guid from namespace http://hes.lbl.gov/scoring_tool/calculate is not marked nillable
    [System.ServiceModel.OperationContractAttribute(Action="http://api-l.hescloud.net/calculate/#generateHesLabel", ReplyAction="*")]
    generateHesLabelResponse generateHesLabel(generateHesLabelRequest request);
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class calcBaseHomeRequest
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Name="calcBaseHome", Namespace="http://hes.lbl.gov/scoring_tool/calculate", Order=0)]
    public calcBaseHomeRequestBody Body;
    
    public calcBaseHomeRequest()
    {
    }
    
    public calcBaseHomeRequest(calcBaseHomeRequestBody Body)
    {
        this.Body = Body;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.Runtime.Serialization.DataContractAttribute(Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
public partial class calcBaseHomeRequestBody
{
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
    public string client_guid;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=1)]
    public long session_id;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=2)]
    public System.Nullable<int> profile;
    
    public calcBaseHomeRequestBody()
    {
    }
    
    public calcBaseHomeRequestBody(string client_guid, long session_id, System.Nullable<int> profile)
    {
        this.client_guid = client_guid;
        this.session_id = session_id;
        this.profile = profile;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class calcBaseHomeResponse
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Name="calcBaseHomeResponse", Namespace="http://hes.lbl.gov/scoring_tool/calculate", Order=0)]
    public calcBaseHomeResponseBody Body;
    
    public calcBaseHomeResponse()
    {
    }
    
    public calcBaseHomeResponse(calcBaseHomeResponseBody Body)
    {
        this.Body = Body;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.Runtime.Serialization.DataContractAttribute(Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
public partial class calcBaseHomeResponseBody
{
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
    public int returnCode;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=1)]
    public string returnComment;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=2)]
    public long session_id;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=3)]
    public string zip_city;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=4)]
    public string zip_state;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=5)]
    public float BaseCost;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=6)]
    public float newcost;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=7)]
    public float cars;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=8)]
    public hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType existingHomeGraphData;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=9)]
    public hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType efficientHomeGraphData;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=10)]
    public hes.lbl.gov.scoring_tool.calculate.summaryResultsType existingHome;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=11)]
    public hes.lbl.gov.scoring_tool.calculate.summaryResultsType withUpgrades;
    
    public calcBaseHomeResponseBody()
    {
    }
    
    public calcBaseHomeResponseBody(int returnCode, string returnComment, long session_id, string zip_city, string zip_state, float BaseCost, float newcost, float cars, hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType existingHomeGraphData, hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType efficientHomeGraphData, hes.lbl.gov.scoring_tool.calculate.summaryResultsType existingHome, hes.lbl.gov.scoring_tool.calculate.summaryResultsType withUpgrades)
    {
        this.returnCode = returnCode;
        this.returnComment = returnComment;
        this.session_id = session_id;
        this.zip_city = zip_city;
        this.zip_state = zip_state;
        this.BaseCost = BaseCost;
        this.newcost = newcost;
        this.cars = cars;
        this.existingHomeGraphData = existingHomeGraphData;
        this.efficientHomeGraphData = efficientHomeGraphData;
        this.existingHome = existingHome;
        this.withUpgrades = withUpgrades;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class generateHesLabelRequest
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Name="generateHesLabel", Namespace="http://hes.lbl.gov/scoring_tool/calculate", Order=0)]
    public generateHesLabelRequestBody Body;
    
    public generateHesLabelRequest()
    {
    }
    
    public generateHesLabelRequest(generateHesLabelRequestBody Body)
    {
        this.Body = Body;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.Runtime.Serialization.DataContractAttribute(Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
public partial class generateHesLabelRequestBody
{
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
    public string client_guid;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=1)]
    public int session_id;
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=2)]
    public System.Nullable<int> force_regenerate;
    
    public generateHesLabelRequestBody()
    {
    }
    
    public generateHesLabelRequestBody(string client_guid, int session_id, System.Nullable<int> force_regenerate)
    {
        this.client_guid = client_guid;
        this.session_id = session_id;
        this.force_regenerate = force_regenerate;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class generateHesLabelResponse
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Name="generateHesLabelResponse", Namespace="http://hes.lbl.gov/scoring_tool/calculate", Order=0)]
    public generateHesLabelResponseBody Body;
    
    public generateHesLabelResponse()
    {
    }
    
    public generateHesLabelResponse(generateHesLabelResponseBody Body)
    {
        this.Body = Body;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
[System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
[System.Runtime.Serialization.DataContractAttribute(Namespace="http://hes.lbl.gov/scoring_tool/calculate")]
public partial class generateHesLabelResponseBody
{
    
    [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
    public int returnCode;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=1)]
    public string returnComment;
    
    [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=2)]
    public hes.lbl.gov.scoring_tool.calculate.pdf_urls pdf_urls;
    
    public generateHesLabelResponseBody()
    {
    }
    
    public generateHesLabelResponseBody(int returnCode, string returnComment, hes.lbl.gov.scoring_tool.calculate.pdf_urls pdf_urls)
    {
        this.returnCode = returnCode;
        this.returnComment = returnComment;
        this.pdf_urls = pdf_urls;
    }
}

[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
public interface HesAPI_CalculatePortChannel : HesAPI_CalculatePort, System.ServiceModel.IClientChannel
{
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
public partial class HesAPI_CalculatePortClient : System.ServiceModel.ClientBase<HesAPI_CalculatePort>, HesAPI_CalculatePort
{
    
    public HesAPI_CalculatePortClient()
    {
    }
    
    public HesAPI_CalculatePortClient(string endpointConfigurationName) : 
            base(endpointConfigurationName)
    {
    }
    
    public HesAPI_CalculatePortClient(string endpointConfigurationName, string remoteAddress) : 
            base(endpointConfigurationName, remoteAddress)
    {
    }
    
    public HesAPI_CalculatePortClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
            base(endpointConfigurationName, remoteAddress)
    {
    }
    
    public HesAPI_CalculatePortClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
            base(binding, remoteAddress)
    {
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    calcBaseHomeResponse HesAPI_CalculatePort.calcBaseHome(calcBaseHomeRequest request)
    {
        return base.Channel.calcBaseHome(request);
    }
    
    public int calcBaseHome(string client_guid, ref long session_id, System.Nullable<int> profile, out string returnComment, out string zip_city, out string zip_state, out float BaseCost, out float newcost, out float cars, out hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType existingHomeGraphData, out hes.lbl.gov.scoring_tool.calculate.summaryGraphDataType efficientHomeGraphData, out hes.lbl.gov.scoring_tool.calculate.summaryResultsType existingHome, out hes.lbl.gov.scoring_tool.calculate.summaryResultsType withUpgrades)
    {
        calcBaseHomeRequest inValue = new calcBaseHomeRequest();
        inValue.Body = new calcBaseHomeRequestBody();
        inValue.Body.client_guid = client_guid;
        inValue.Body.session_id = session_id;
        inValue.Body.profile = profile;
        calcBaseHomeResponse retVal = ((HesAPI_CalculatePort)(this)).calcBaseHome(inValue);
        returnComment = retVal.Body.returnComment;
        session_id = retVal.Body.session_id;
        zip_city = retVal.Body.zip_city;
        zip_state = retVal.Body.zip_state;
        BaseCost = retVal.Body.BaseCost;
        newcost = retVal.Body.newcost;
        cars = retVal.Body.cars;
        existingHomeGraphData = retVal.Body.existingHomeGraphData;
        efficientHomeGraphData = retVal.Body.efficientHomeGraphData;
        existingHome = retVal.Body.existingHome;
        withUpgrades = retVal.Body.withUpgrades;
        return retVal.Body.returnCode;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    generateHesLabelResponse HesAPI_CalculatePort.generateHesLabel(generateHesLabelRequest request)
    {
        return base.Channel.generateHesLabel(request);
    }
    
    public int generateHesLabel(string client_guid, int session_id, System.Nullable<int> force_regenerate, out string returnComment, out hes.lbl.gov.scoring_tool.calculate.pdf_urls pdf_urls)
    {
        generateHesLabelRequest inValue = new generateHesLabelRequest();
        inValue.Body = new generateHesLabelRequestBody();
        inValue.Body.client_guid = client_guid;
        inValue.Body.session_id = session_id;
        inValue.Body.force_regenerate = force_regenerate;
        generateHesLabelResponse retVal = ((HesAPI_CalculatePort)(this)).generateHesLabel(inValue);
        returnComment = retVal.Body.returnComment;
        pdf_urls = retVal.Body.pdf_urls;
        return retVal.Body.returnCode;
    }
}
