//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.comcast.xml.homesecurity.types;

import com.comcast.xml.homesecurity.types.BillingSystemType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(
    name = "GetCustomerPermitRequirementsRequest",
    propOrder = {"billingArrangementID", "billingSystemId", "csgHouseKey", "installDate", "streetAddress", "city", "state", "zipCode"}
)
public class GetCustomerPermitRequirementsRequest {
    
    protected String billingArrangementID;
    
    protected BillingSystemType billingSystemId;
   
    protected String csgHouseKey;
   
    protected String city;
    protected String state;
    
    protected String zipCode;

    public GetCustomerPermitRequirementsRequest() {
    }

    public String getBillingArrangementID() {
        return this.billingArrangementID;
    }

    public void setBillingArrangementID(String value) {
        this.billingArrangementID = value;
    }

   

    public String getCsgHouseKey() {
        return this.csgHouseKey;
    }

    public void setCsgHouseKey(String value) {
        this.csgHouseKey = value;
    }

   
    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String value) {
        this.streetAddress = value;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String value) {
        this.zipCode = value;
    }
}
