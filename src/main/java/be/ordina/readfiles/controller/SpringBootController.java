package be.ordina.readfiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ordina.readfiles.service.FileReader;


@RestController
public class SpringBootController {
	
	private FileReader csvReader;
	private FileReader prnReader;
	
	@Autowired
	public SpringBootController (FileReader csvReader, FileReader prnReader) {
		this.csvReader = csvReader;
		this.prnReader = prnReader;
	}

    @RequestMapping("/")
    public String index() {
        return "Greetings from the read file app";
    }
    
    @RequestMapping("/CSVTest")
    public String testCsv() {
    	
    	List<String> header = csvReader.readHeader();
    	List<List<String>> records = csvReader.readRecords();
    	
    	String data = printFileContentSimple(header, records);
    	
    	return "<h1>PRINT csv file</h1> <br>" + data ;
    }
    @RequestMapping("/PRNTest")
    public String testPrn() {
    	List<String> header = prnReader.readHeader();
    	List<List<String>> records = prnReader.readRecords();
    	
    	String data = printFileContentSimple(header, records);
    	
    	return "<h1>PRINT prn file</h1> <br>" + data ;
    }

    
	private String printFileContentSimple(List<String> header,
			List<List<String>> records) {
		String data = "<div>";
    	for (String headerField : header) {
    		data = data + "<span style='padding-left:5px;padding-right:5px;'>" + headerField + "</span>";
    	}
    	data = data + "</div>";
    	
    	for (List<String> record : records) {
    		
    		data = data + "<div>";
    		for (String recordField : record) {
    			data = data + "<span style='padding-left:5px;padding-right:5px;'>" + recordField + "</span>";
    		}
    		data = data + "</div>";
    	}
		return data;
	}
    
    
    
    


    
}
