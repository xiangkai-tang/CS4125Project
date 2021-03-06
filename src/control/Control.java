package control;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import bll.EnterpriseAccountBLL;
import bll.FamilyAccountBLL;
import bll.RegionBLL;
import account.EnterpriseAccount;
import account.FamilyAccount;
import region.Region;
import service.Service;
import service.ServiceFactory;
/*
 * @author Lucian Epure
 */
public class Control {
	
	private static final Control controlInstance= new Control();
	private List<Service> services;
	private List<Region> availableRegions;
	private List<String> types;
	private List<String> registeredNumbers;
	private RegionBLL regionBLL;
	Random rand;
	String outputFile = "generatedServices.csv";
	BufferedWriter writer;
	
	private Control() {
		types = new ArrayList<String>(Arrays.asList("Message", "Call", "Internet"));
		registeredNumbers =new ArrayList<String>();
		services= new ArrayList<Service>();
		regionBLL = new RegionBLL();
		availableRegions = regionBLL.getRegions();
		rand = new Random();
	}
	
	public static Control getInstance(){
		return controlInstance;
	}
	

	public int generateRandomNumber(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
	public void generateServices(Configuration configuration) {
		String generatedType = new String();
		Region from=null;
		Region to= null;
		int duration;
		String number;
		int quantity = configuration.getQuantity();
		List<EnterpriseAccount> enterpriseAccount;
		List<FamilyAccount> familyAccount;
		EnterpriseAccountBLL enterpriseAccountBLL = new EnterpriseAccountBLL();
		enterpriseAccount = enterpriseAccountBLL.getAll();
		FamilyAccountBLL familyAccountBLL = new FamilyAccountBLL();
		familyAccount = familyAccountBLL.getAll();
		for(int i = 0;i< quantity;i++)
		{
			int s = generateRandomNumber(0,enterpriseAccount.size());
			int s2 = generateRandomNumber(0,familyAccount.size());
			registeredNumbers.add(familyAccount.get(s2).getNumbersString());
			registeredNumbers.add(enterpriseAccount.get(s).getNumber());
		}
		try {
			writer = new BufferedWriter(new FileWriter(outputFile));

		for (int i = 0; i < quantity; i++) {

			number= registeredNumbers.get(rand.nextInt(registeredNumbers.size()));
			if (configuration.getType().equals("Any")) {
				generatedType = types.get(rand.nextInt(types.size()));
			} 
			if (configuration.getType().equals("Any")==false)
				generatedType = configuration.getType();

			if (configuration.getFrom().equals("Any")) {
				from = availableRegions.get(rand.nextInt(availableRegions.size()));
			} 
			if (configuration.getFrom().equals("Any")==false)
				from = regionBLL.search(configuration.getFrom());
			if (configuration.getTo().equals("Any")) {
				to = availableRegions.get(rand.nextInt(availableRegions.size()));
			} 
			if (configuration.getTo().equals("Any")==false)
				to = regionBLL.search(configuration.getTo());
			duration = rand.nextInt((configuration.getMaxDuration() - configuration.getMinDuration()) + 1) + configuration.getMinDuration();
			Service S= ServiceFactory.getService(generatedType, from, number, to, duration);
			services.add(S);
			
			writer.write(S.getClass().getSimpleName() + "," + S.toString());
			writer.write("\n");
			System.out.println(S.toString());
			
		}
		writer.close();
		}
		
		catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Region> getAvailableRegions() {
		return availableRegions;
	}

	public void setAvailableRegions(List<Region> availableRegions) {
		this.availableRegions = availableRegions;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
		}
}