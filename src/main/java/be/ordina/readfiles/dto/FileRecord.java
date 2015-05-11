package be.ordina.readfiles.dto;

import java.util.ArrayList;
import java.util.List;

public class FileRecord {
	
	private HeaderRecord header;
	
	private List<PersonRecord> personRecords;
	
	public FileRecord() {
		header = new HeaderRecord();
		personRecords = new ArrayList<PersonRecord>();
	}

	public HeaderRecord getHeader() {
		return header;
	}

	public List<PersonRecord> getPersonRecords() {
		return personRecords;
	}
	
	
	public void setHeaderRecord(HeaderRecord header) {
		this.header = header;
	}
	
	

}
