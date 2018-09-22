package com.ups.sender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qvd.model.AccountSegmentStructure;
import com.qvd.model.AddressSegmentStructure;
import com.qvd.model.DeliverySegmentStructure;
import com.qvd.model.EDISegmentStructure;
import com.qvd.model.HeaderSegment;
import com.qvd.model.LIDSegmentStructure;
import com.qvd.model.MQMessage;
import com.qvd.model.MainSegmentStructure;
import com.qvd.model.NotificationSegmentStructure;
import com.qvd.model.SpanSegmentStructure;

@SpringBootApplication
public class SpringBootMessageSender {
	
	private static final String QUEUE_NAME = "JSON_QUEUE";
	private static final String FILE_NAME = "C:\\Users\\hameed\\Desktop\\CPS.txt";

	@Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
 
 
     
    public static void main(String[] args) throws FileNotFoundException 
    {
    	// Launch the application


    	File file = new File(FILE_NAME);

    	BufferedReader br = new BufferedReader(new FileReader(file));
    	List<MQMessage> mqMessages=new ArrayList<>();
    	String st=null;
    	HeaderSegment headerSegment=null;
    	MainSegmentStructure mainSegment =null;
    	DeliverySegmentStructure deliverySegment = null;
    	NotificationSegmentStructure notificationSegment =null;
    	EDISegmentStructure ediSegment = null;
    	AccountSegmentStructure accountSegment = null;
    	LIDSegmentStructure lidSegment=null;
    	AddressSegmentStructure addressSegment=null;
    	SpanSegmentStructure spanSegment=null;
    	MQMessage mqMessage = null;

    	String data=null;
    	try {
    		data = readFileAsString("C:\\Users\\hameed\\Desktop\\CPS.txt");
    	} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    	} 
    	String character = "*h";
    	String[] splitarray=data.split("\\*h");

    	for(String mainString:splitarray)
    	{
    		mainString=character+mainString;

    		if (mainString.startsWith("*h*")) 
    		{
    			mqMessage = new MQMessage();
    			List<AddressSegmentStructure> addressSegmentList = new ArrayList<>();
    			List<AccountSegmentStructure> accountSegmList = new ArrayList<>();
    			List<LIDSegmentStructure> lidSegmList = new ArrayList<>();



    			// Header segment structure
    			headerSegment = new HeaderSegment();
    			//headerSegment.setSegmentId(mainString.substring(0, 3));
    			headerSegment.setRequestType(mainString.substring(3, 4));
    			headerSegment.setSubscriptionId(mainString.substring(4, 20));
    			//headerSegment.setCompanyHashNumber(mainString.substring(20, 28));
    			headerSegment.setTimestamp(mainString.substring(28, 54));
    			headerSegment.setAccountSegmentCount(mainString.substring(54, 59));// 59
    			headerSegment.setlIDSegmentCount(mainString.substring(59, 64));// 64
    			headerSegment.setAddressSegmentCount(mainString.substring(64, 69));// 69
    			headerSegment.setTotalSegmentCount(mainString.substring(69, 74));// 74

    			//span segment structure
    			spanSegment=new SpanSegmentStructure();
    			if (mainString.contains("*s*")) 
    			{
    				String spanSegmentString=mainString.substring(mainString.indexOf("*h*"), mainString.indexOf("*s*"));
    				//spanSegment.setSegmentId(spanSegmentString.substring(0, 3));
    				spanSegment.setTimeStamp(spanSegmentString.substring(3, 29));
    			}

    			// Main segment structure
    			mainSegment = new MainSegmentStructure();
    			if (mainString.contains("*m*")) 
    			{
    				String mainSegmentString = mainString.substring(mainString.indexOf("*m*"), mainString.indexOf("*d*"));
    				//mainSegment.setSegmentId(mainSegmentString.substring(0, 3));
    				mainSegment.setInterNetUserName(mainSegmentString.substring(3, 19));
    				mainSegment.setSubscriptionName(mainSegmentString.substring(19, 54));
    				//mainSegment.setProductCode(mainSegmentString.substring(54, 56));
    				mainSegment.setProductFeatureCode(mainSegmentString.substring(56, 58));
    				mainSegment.setuPSAdminStatusCode(mainSegmentString.substring(58, 60));
    				mainSegment.setUserStatusCode(mainSegmentString.substring(60, 62));
    				mainSegment.setUserExceptionPreference(mainSegmentString.substring(62, 64));
    				if((mainSegmentString.substring(54, 56).equals("01"))){
    					mainSegment.setProductCode("Outbound (OB)");
    				}else 	if((mainSegmentString.substring(54, 56).equals("2"))){

    					mainSegment.setProductCode("LID");

    				}

    			}

    			//Delivery Segment
    			deliverySegment = new DeliverySegmentStructure();
    			if (mainString.contains("*d*")) 
    			{
    				String deliverySegmentString = mainString.substring(mainString.indexOf("*d*"), mainString.indexOf("*/*"));
    				//deliverySegment.setSegmentId(deliverySegmentString.substring(0, 3));
    				deliverySegment.setDataExchangeSoftwareCode(deliverySegmentString.substring(3, 5));
    				deliverySegment.setDataExchangeStandardSpecfCode(deliverySegmentString.substring(5, 7));
    				deliverySegment.setRptLayoutCategoryCode(deliverySegmentString.substring(7, 9));
    				deliverySegment.setMailBoxIdentifier(deliverySegmentString.substring(9, 73));
    				deliverySegment.setElectronicAddressNumber(deliverySegmentString.substring(73, 81));

    			}

    			notificationSegment = new NotificationSegmentStructure();
    			if (mainString.contains("*n*")) 
    			{
    				String notificatinSegmentString = mainString.substring(mainString.indexOf("*n*"), mainString.indexOf("*/*"));
    				//notificationSegment.setSegmentId(notificatinSegmentString.substring(0, 3));
    				notificationSegment.setLanguageTypeCode(notificatinSegmentString.substring(3, 6));
    				notificationSegment.setLanguageCountryCode(notificatinSegmentString.substring(6, 8));
    				notificationSegment.setElectronicAddressText(notificatinSegmentString.substring(8, 72));
    				notificationSegment.setElectronicAddressNumber(notificatinSegmentString.substring(72, 80));

    			}

    			// EDI segment structure
    			ediSegment=new EDISegmentStructure();
    			if(mainString.contains("*e*"))
    			{
    				String ediSegmentString=mainString.substring(mainString.indexOf("*e*"),mainString.indexOf("*/*")); 
    				//System.out.println(ediSegmentString);

    				//ediSegment.setSegmentId(ediSegmentString.substring(0,3));
    				ediSegment.seteDIID(ediSegmentString.substring(3,18));
    				ediSegment.seteDILoadID(ediSegmentString.substring(18,23));
    			}

    			if(mainString.contains("*a*"))
    			{
    				String[] accountSegmentArray=mainString.split("\\*a\\*");

    				//System.out.println(totalaccountSegments);
    				for (int  k= 1; k<accountSegmentArray.length; k++) {
    					String accountSegmentString=accountSegmentArray[k];
    					accountSegmentString="*a*"+accountSegmentString;


    					accountSegment = new AccountSegmentStructure();
    					if (mainString.contains("*a*")) {
    						//accountSegment.setSegmentId(accountSegmentString.substring(0, 3));
    						accountSegment.setAccountNumber(accountSegmentString.substring(3, 13));
    						accountSegment.setAccountRoleCode(accountSegmentString.substring(13, 15));
    						accountSegment.setAccountCountryCode(accountSegmentString.substring(15, 17));
    						accountSegmList.add(accountSegment);
    					}
    				}
    			}

    			if (mainString.contains("*l*"))
    			{

    				String[] lidSegmentStringArray=mainString.split("\\*l\\*");

    				//System.out.println(totalaccountSegments);
    				for (int  l= 1; l<lidSegmentStringArray.length; l++) {
    					String lidSegmentString=lidSegmentStringArray[l];
    					lidSegmentString="*l*"+lidSegmentString;
    					if (mainString.contains("*l*")) {

    						lidSegment=new LIDSegmentStructure();
    						//lidSegment.setSegmentId(lidSegmentString.substring(0, 3));
    						lidSegment.setLidNumber(lidSegmentString.substring(3, 13));
    						lidSegmList.add(lidSegment);
    					}
    				}
    			}
    			if (mainString.contains("*r*")) 
    			{

    				String[] addressSegmenArray=mainString.split("\\*r\\*");

    				//System.out.println(totalaccountSegments);
    				for (int  l= 1; l<addressSegmenArray.length; l++) {
    					String addressSegmentString=addressSegmenArray[l];
    					addressSegmentString="*r*"+addressSegmentString;
    					if (mainString.contains("*r*")) {

    						addressSegment=new AddressSegmentStructure();
    						//addressSegment.setSegmentId(addressSegmentString.substring(0, 3));
    						addressSegment.setAddressToken(addressSegmentString.substring(3, 35));
    						addressSegment.setAddressNickname(addressSegmentString.substring(35, 70));
    						addressSegmentList.add(addressSegment);
    					}
    				}	
    			}

    			mqMessage.setHeaderSegment(headerSegment);
    			mqMessage.setSpanSegmentStructure(spanSegment);
    			mqMessage.setMainSegmentStructure(mainSegment);
    			mqMessage.setDeliverySegmentStructure(deliverySegment);
    			mqMessage.setNotificationSegmentStructure(notificationSegment);
    			mqMessage.seteDISegmentStructure(ediSegment);
    			mqMessage.setAccountSegmentStructures(accountSegmList);
    			mqMessage.setLidSegmentStructures(lidSegmList);
    			mqMessage.setAddressSegmentStructures(addressSegmentList);
    			mqMessages.add(mqMessage);
    			jsonPrint(mqMessage,args);

    			//printMqMessage(headerSegment, spanSegment, mainSegment, deliverySegment, ediSegment, notificationSegment, accountSegment, addressSegment, lidSegment);

    		}

    	}

    }



		public static void jsonPrint(MQMessage mqMessage, String[] args)
		{

			ObjectMapper mapper = new ObjectMapper();
			try {

				// Convert object to JSON string
				String jsonInString= mapper.writeValueAsString(mqMessage);
				//System.out.println(jsonInString);

				// Convert object to JSON string and pretty print
				//jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mqMessages);

				System.out.println(jsonInString);


				FileWriter fileWriter = new FileWriter("C:\\Users\\hameed\\Desktop\\cps.json",true);

				mapper.writeValue(fileWriter, mqMessage);

				PublishMessage(args,jsonInString);

			}  catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static String readFileAsString(String fileName)throws Exception 
		{ 
			String data = ""; 
			data = new String(Files.readAllBytes(Paths.get(fileName))); 
			return data; 
		} 




	private static void PublishMessage(String[] args,String jsonInString) 
	{

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootMessageSender.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		String message=jsonInString;
		System.out.println("Sending a JMS message.");
		jmsTemplate.convertAndSend(QUEUE_NAME, message);
		System.out.println("Successfully post the message.");
	}
}
