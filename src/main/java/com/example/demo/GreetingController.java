package com.example.demo;

import com.example.demo.name;
import com.example.demo.Patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greetingForm(@ModelAttribute Appointment app, Model model) {
		
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "localhost"))
                .build();
        
        
        model.addAttribute("app", new Appointment());
        
        
       
// 			All testing Different features of spring boot, dynamo and thymeleaf
//        app = new Appointment("2022-04-28","14:15","Blurry vision");
//        Patient newPT =  new Patient("Winston Binston", "1922-09-08", "223 Danburr lane", 85);
//        
//        app.setPatient(newPT);
//        DynamoDBMapper mapper = new DynamoDBMapper(client);
//        mapper.save(app);
//        
//
//            DynamoDB dynamoDB = new DynamoDB(client);
//            
//            Table table = dynamoDB.getTable("Thread");
//            
//            Item item = table.getItem("ForumName", "Amazon DynamoDB", "Subject", "Tagging tables" );
//            item.get("LastPostedBy");
//        
//        System.out.println(  item.get("LastPostedBy"));
//		
//		model.addAttribute("name", item.get("LastPostedBy"));
//		name newName = new name();
//		model.addAttribute("object", newName);
		
		
		return "greeting";
	}
	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Appointment app, Model model) {
		
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "localhost"))
                .build();
		
        //System.out.println(model);
		System.out.println(app);
		 model.addAttribute("app", new Appointment());
		
      DynamoDBMapper mapper = new DynamoDBMapper(client);
      mapper.save(app);
		
		return "greeting";
	
	}
}