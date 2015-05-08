package be.ordina.readfiles.service;

import java.util.List;

public interface FileReader {
	
	List<String> readHeader();
	
	List<List<String>> readRecords();

}
