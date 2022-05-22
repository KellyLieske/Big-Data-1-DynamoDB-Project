package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Controller
public class updateController {
			
	
	@GetMapping("/update/{date}/{time}")
	public String updateItem(@PathVariable String date, @PathVariable String time, Model model)
	{
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "localhost"))
                .build();
		
        DynamoDB dynamoDB = new DynamoDB(client);
        
        Table table = dynamoDB.getTable("Appointment");
       
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Appointment savedApp = new Appointment();
       
       savedApp = mapper.load(Appointment.class, date, time);
       
        model.addAttribute("app", savedApp);
        
        System.out.println(model);
        
        
		
		return "update";
		
	}
	@PostMapping("/update")
	public String updateAppointment(@ModelAttribute Appointment app, Model model)
	{
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "localhost"))
                .build();
		
        DynamoDB dynamoDB = new DynamoDB(client);
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        
        mapper.save(app);
		
		
		
		return "redirect:/viewAppointments";
	}
	
	
}
