package be.ordina.readfiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ordina.readfiles.service.FileReader;
import be.ordina.readfiles.service.PrintService;


@Controller
public class SpringBootController {
	
	private FileReader csvReader;
	private FileReader prnReader;
	
	private PrintService printService;
	
	private static final String INDEX_TEMPLATE = "index";
	private static final String FILE_TEMPLATE = "file";
	
	@Autowired
	public SpringBootController (FileReader csvReader, FileReader prnReader, PrintService printService) {
		this.csvReader = csvReader;
		this.prnReader = prnReader;
		
		this.printService = printService;
	}

    @RequestMapping("/")
    public String index(ModelMap model) {
    	
    	model.put("csv", "print csv file simple");
    	model.put("prn", "print prn file simple");
    	model.put("csv_with_dto", "print csv file into the dto template");
    	model.put("prn_with_dto", "print prn file into the dto template");
    	
        return INDEX_TEMPLATE;
    }
    
    
    @RequestMapping("/csvFile")
    public String csvFile(ModelMap model) {
    	
    	List<String> header = csvReader.readHeader();
    	List<List<String>> records = csvReader.readRecords();
    	
    	model.put("file", printService.generateFileRecord(header, records));
    	
    	return FILE_TEMPLATE;
    }
    
    
    @RequestMapping("/prnFile")
    public String prnFile(ModelMap model) {
    	
    	List<String> header = prnReader.readHeader();
    	List<List<String>> records = prnReader.readRecords();
    	
    	model.put("file", printService.generateFileRecord(header, records));
    	
    	return FILE_TEMPLATE;
    }
    
    


    
}
