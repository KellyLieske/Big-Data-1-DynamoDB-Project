package com.example.demo;

import com.example.demo.name;
import com.example.demo.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;


@Controller
public class viewAppointmentsController {
	
	@GetMapping("/viewAppointments")
	public String getAppointments( Model model) {
		
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "localhost"))
                .build();
		
		
       
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        
       // List<Appointment> appList = mapper.scan(Appointment.class, scanExpression);
        
        List<Appointment> dynamoReturn = mapper.scan(Appointment.class, scanExpression);
        
        
        
        ArrayList<Appointment> appList = new ArrayList<Appointment>();
        
        for( Appointment app :dynamoReturn)
        {
        	appList.add(app);
        }
        
        ArrayList<ArrayList<Appointment>> allApps = new ArrayList<ArrayList<Appointment>>();
        ArrayList<Appointment> todaysApps = new ArrayList<Appointment>();
        ArrayList<Appointment> futureApps = new ArrayList<Appointment>();
        ArrayList<Appointment> pastApps = new ArrayList<Appointment>();
        
        
        
        String Date =  java.time.LocalDate.now().toString();
  
        
        for( Appointment app :dynamoReturn)
        {
           
        	
        	if(app.getDate().compareTo(Date) == 0)
        		todaysApps.add(app);
        	else if(app.getDate().compareTo(Date) > 0)
        		futureApps.add(app);
        	else
        		pastApps.add(app);
        }
        
        
        Collections.sort(todaysApps, Appointment.appComp);
        Collections.sort(futureApps, Appointment.appComp);
        Collections.sort(pastApps, Appointment.appComp);

        allApps.add(todaysApps);
        allApps.add(futureApps);
        allApps.add(pastApps);
        
        
        model.addAttribute("appList", allApps);
        
		return "viewAppointments";
	}
	
}
