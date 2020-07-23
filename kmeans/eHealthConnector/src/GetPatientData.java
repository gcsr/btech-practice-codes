
/**
 * TDJIR EHCON-349: Description of the class.
 * 
 * @author EHP
 * 
 * 
 * 
 */
/*
 * Copyright (c) eHealth
 */



import javax.xml.bind.JAXBException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import be.ehealth.businessconnector.genins.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.genins.domain.RequestParameters;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;


public class GetPatientData {
	public static void main(String[] gcs){
		try {


			ConfigFactory.setConfigLocation(
					"config/be.ehealth.technicalconnector.properties");

			System.out.println(ConfigFactory.getConfigValidator().getProperty("KEYSTORE_DIR"));

			SessionManager sessionmgmt = Session.getInstance();

			if (!sessionmgmt.hasValidSession()) {
				sessionmgmt.createSessionEidOnly() ;
			
			}
			testCreateGetInsurabilityRequest();
			

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		
	}
	

    @Test
    public static void testCreateGetInsurabilityRequest() throws TechnicalConnectorException, GenInsBusinessConnectorException, InstantiationException, JAXBException, TransformerConfigurationException, TransformerFactoryConfigurationError {


        // Request parameter
        RequestParameters requestParameters = new RequestParameters();

        // INSS hi is this patient inss?
        requestParameters.setInss("51092302453");

        // Mutuality
        requestParameters.setMutuality("Socialiste Mutuality");

        // RegNrWithMut
        requestParameters.setRegNrWithMut("306");

        // Date
        DateTime dateTime = new DateTime();

        requestParameters.setPeriodStart(dateTime);
        requestParameters.setPeriodEnd(dateTime);

        // InsurabilityReference
        requestParameters.setInsurabilityReference("x2102138001");

        // ContactId
        requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.HOSPITALIZED_FOR_DAY);

        // RequestType
        requestParameters.setInsurabilityRequestType(InsurabilityRequestTypeType.INVOICING);

        GetInsurabilityAsXmlOrFlatRequestType getInsurabilityAsXmlOrFlatRequestType = RequestObjectBuilderFactory.getRequestObjectBuilder().createGetInsurabilityRequest(requestParameters, true);
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType);

        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getInputReference());

        // Common input

        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage().getLicense());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage().getName());
        // Assert.assertEquals("eHealth genins connector",
        // getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage().getName().getValue());
        // Assert.assertEquals("ehrelther",
        // getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage().getLicense().getUsername());
        // Assert.assertEquals("ehealthrel",
        // getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getPackage().getLicense().getPassword());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getNihii());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getNihii());
        // Assert.assertEquals("doctor",
        // getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getNihii().getQuality());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getPhysicalPerson());
        // Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getPhysicalPerson().getNihii());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getPhysicalPerson().getName());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getOrigin().getCareProvider().getPhysicalPerson().getSsin());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getCommonInput().getInputReference());

        // Record Common input
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRecordCommonInput());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRecordCommonInput().getInputReference());


        // Request
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId());
        Assert.assertEquals("51092302453", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getInss());
        Assert.assertEquals("306", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getRegNrWithMut());
        Assert.assertEquals("Socialiste Mutuality", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getMutuality());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail());
        Assert.assertEquals(InsurabilityRequestTypeType.INVOICING, getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityRequestType());

        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod());

        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod().getPeriodStart());
        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod().getPeriodEnd());

        Assert.assertEquals(InsurabilityContactTypeType.HOSPITALIZED_FOR_DAY, getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityContactType());

        Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityReference());

    }

}

