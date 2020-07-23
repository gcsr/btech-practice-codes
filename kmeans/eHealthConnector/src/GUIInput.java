import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.joda.time.DateTime;
import org.junit.Assert;

import be.ehealth.businessconnector.genins.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.genins.domain.RequestParameters;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;


public class GUIInput extends JFrame{
	
	private JTextField INSSField;
	private JTextField mutualityField;
	private JTextField registrationNumberWithMutualityField;
	private JTextField insurabilityReferenceField;
	private JTextField periodStartField;
	private JTextField periodEndField;
	String list1[] = {"INVOICING","INFORMATION"};
	JList jlist1;
	
	String list2[] = {"ambulatory_care","hospitalized_for_day","hospitalized_elsewhere","other"};
	JList jlist2;

	
	private JLabel INSSLabel;
	private JLabel mutualityLabel;
	private JLabel registrationNumberWithMutualityLabel;
	private JLabel insurabilityReferenceLabel;
	private JLabel insurabilityRequestLabel;
	private JLabel periodStartLabel;
	private JLabel periodEndLabel;
	private JLabel insurabilityContactTypeLabel;
	private JButton submitButton;
	
	
	
	public GUIInput(){
		super("Inputs");
		setLayout(new BorderLayout());
		Inputs panel=new Inputs();
		//panel.setLayout(new GridLayout(7,2));
		add(new Inputs(),BorderLayout.CENTER);
		submitButton=new JButton("Submit Request");
		add(submitButton,BorderLayout.SOUTH);
		//pack();
		
		submitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					// TODO Auto-generated method stub
					RequestParameters requestParameters = new RequestParameters();

					// INSS
					String inss=INSSField.getText();
					if(inss!=null && !inss.equals(""))
						requestParameters.setInss(inss);

					// Mutuality
					String socialMutuality=mutualityField.getText();
					if(socialMutuality!=null && !socialMutuality.equals(""))
						requestParameters.setMutuality(socialMutuality);
					
					// 	RegNrWithMut
					String registrationNumberWithMutualityFieldText=registrationNumberWithMutualityField.getText();
					requestParameters.setRegNrWithMut(registrationNumberWithMutualityFieldText);
					
					// Date
					DateTime dateTime = new DateTime();
					
					
					
					requestParameters.setPeriodStart(dateTime);
					requestParameters.setPeriodEnd(dateTime);
					
					// 	InsurabilityReference
					//	requestParameters.setInsurabilityReference("000124578");

					// ContactId
		        
					if(jlist1.getSelectedValue().equals(list1[0]))
						requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.AMBULATORY_CARE);
					else if(jlist1.getSelectedValue().equals(list1[1]))
						requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.HOSPITALIZED_FOR_DAY);
					else if(jlist1.getSelectedValue().equals(list1[2]))
						requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.HOSPITALIZED_ELSEWHERE);
					else if(jlist1.getSelectedValue().equals(list1[3]))
						requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.OTHER);
		        

					// RequestType
					if(jlist2.getSelectedValue().equals(list2[0]))			        
						requestParameters.setInsurabilityRequestType(InsurabilityRequestTypeType.INVOICING);
					else
						requestParameters.setInsurabilityRequestType(InsurabilityRequestTypeType.INFORMATION);
					
					GetInsurabilityAsXmlOrFlatRequestType getInsurabilityAsXmlOrFlatRequestType = RequestObjectBuilderFactory.getRequestObjectBuilder().createGetInsurabilityRequest(requestParameters, true);
					
					/*Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType);
	
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
		        	//Assert.assertEquals("82071702968", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getInss());
		        	//Assert.assertEquals("1234", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getRegNrWithMut());
		        	//Assert.assertEquals("Socialiste Mutuality", getInsurabilityAsXmlOrFlatRequestType.getRequest().getCareReceiverId().getMutuality());
		        	Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail());
		        	Assert.assertEquals(InsurabilityRequestTypeType.INVOICING, getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityRequestType());
	
		        	Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod());
	
		        	Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod().getPeriodStart());
		        	Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getPeriod().getPeriodEnd());

		        	Assert.assertEquals(InsurabilityContactTypeType.HOSPITALIZED_FOR_DAY, getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityContactType());

		        	Assert.assertNotNull(getInsurabilityAsXmlOrFlatRequestType.getRequest().getInsurabilityRequestDetail().getInsurabilityReference());
		        */
			}catch(Exception ex){
				
			}
				

			}
		
		});
		
		
	}
	
	private void makeRequest(){
		
	}
	
	public static void main(String[] gcs){
		try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
		      System.err.println("Look and feel not set.");
		}
		GUIInput input=new GUIInput();
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.setSize(500,650);
		input.setVisible(true);
	}

	class Inputs extends JPanel{
		public Inputs(){
			setLayout(new GridLayout(8,2));			
			INSSField=new JTextField(20);
			INSSField.setToolTipText("Field 300");
			mutualityField=new JTextField(20);
			mutualityField.setToolTipText("Field 301");
			registrationNumberWithMutualityField=new JTextField();
			registrationNumberWithMutualityField.setToolTipText("Field 302");
			periodStartField=new JTextField(12);
			periodStartField.setToolTipText("Field 304");
			periodEndField=new JTextField(12);
			periodEndField.setToolTipText("Field 305");
			
			insurabilityReferenceField=new JTextField(20);
			insurabilityReferenceField.setToolTipText("Field 307");
			
			String INSSLabelHtml="<html>Care Receiver Id<sup>*</sup></html>";
			INSSLabel=new JLabel(INSSLabelHtml);
			String mutualityLabelHtml="<html>Mutuality Number</html>";
			mutualityLabel=new JLabel(mutualityLabelHtml);
			String registrationNumberWithMutualityLabelHtml="<html>Registration Number with Mutuality</html>";
			registrationNumberWithMutualityLabel=new JLabel(registrationNumberWithMutualityLabelHtml);
			String insurabilityReferenceLabelHtml="<html>Insurability Reference</html>";
			insurabilityReferenceLabel=new JLabel(insurabilityReferenceLabelHtml);
			String insurabilityRequestLabelHtml="<html>Request Type<sup>*</sup></html>";
			insurabilityRequestLabel=new JLabel(insurabilityRequestLabelHtml);
			String periodStartLabelHtml="<html>Insurability period start date<sup>*</sup></html>";
			periodStartLabel=new JLabel(periodStartLabelHtml);
			String periodEndLabelHtml="<html>Insurability period end date<sup>*</sup></html>";
			periodEndLabel=new JLabel(periodEndLabelHtml);
			String insurabilityContactTypeLabelHtml="<html>Insurability Contact Type<sup>*</sup></html>";
			insurabilityContactTypeLabel=new JLabel(insurabilityContactTypeLabelHtml);			
			
			jlist1 = new JList(list1);
			jlist1.setVisibleRowCount(1);
			jlist2 = new JList(list2);
			jlist2.setVisibleRowCount(1);
			
			
			add(INSSLabel);
			add(INSSField);
			add(mutualityLabel);
			add(mutualityField);
			add(registrationNumberWithMutualityLabel);
			add(registrationNumberWithMutualityField);
			add(insurabilityReferenceLabel);
			add(insurabilityReferenceField);

			
			add(periodStartLabel);
			add(periodStartField);
			add(periodEndLabel);
			add(periodEndField);
			add(insurabilityRequestLabel);
			add(jlist1);
			add(insurabilityContactTypeLabel);
			add(jlist2);
			
			
			
		}
	}
}
