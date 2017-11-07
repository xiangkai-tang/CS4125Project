package control;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import region.Region;
import region.Romania;
import service.Service;
import service.ServiceFactory;

public class Control {
	private List<Service> services;
	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	private List<Region> availableRegions;
	private List<String> types;
	private List<String> registeredNumbers;
	private String numi;
	Random rand;
	String outputFile = "generatedServices.txt";
	BufferedWriter writer;
	public Control() {
		types = new ArrayList<String>(Arrays.asList("Message", "Call", "Internet"));
		
		availableRegions = new ArrayList<Region>();
		registeredNumbers =new ArrayList<String>();
		services= new ArrayList<Service>();
		Region romania= new Romania();
		
		numi= "1112121";
		availableRegions.add(romania);
		registeredNumbers.add(numi);
		rand = new Random();
	}
	public Region search(String name)
	{
		for (Region r:availableRegions)
		{
			if(r.getRegionName().equals(name))
				return r;
		}
		System.out.println("Region not found");
		return null;
	}


	public void generateServices(Configuration C) {
		String generatedType = new String();
		Region from = null;
		Region to = null;
		int duration;
		String number;
		int quantity = C.getQuantity();
		try {
			writer = new BufferedWriter(new FileWriter(outputFile));

		for (int i = 0; i < quantity; i++) {

			number= registeredNumbers.get(rand.nextInt(registeredNumbers.size()));
			if (C.getType().equals("Any")) {
				generatedType = types.get(rand.nextInt(types.size()));
			} 
			if (C.getType().equals("Any")==false)
				generatedType = C.getType();

			if (C.getFrom().equals("Any")) {
				from = availableRegions.get(rand.nextInt(availableRegions.size()));
			} 
			if (C.getFrom().equals("Any")==false)
				from = this.search(C.getFrom());
			if (C.getTo().equals("Any")) {
				to = availableRegions.get(rand.nextInt(availableRegions.size()));
			} 
			if (C.getTo().equals("Any")==false)
				to = this.search(C.getTo());
			duration = rand.nextInt((C.getMaxDuration() - C.getMinDuration()) + 1) + C.getMinDuration();
			Service S= ServiceFactory.getService(generatedType, from, number, to, duration);
			services.add(S);
			
			writer.write(S.toString());
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


}
