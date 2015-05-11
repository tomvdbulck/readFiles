package be.ordina.readfiles.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import org.springframework.stereotype.Service;

import be.ordina.readfiles.dto.FileRecord;
import be.ordina.readfiles.dto.HeaderRecord;
import be.ordina.readfiles.dto.PersonRecord;
import be.ordina.readfiles.exception.InvalidHeaderException;
import be.ordina.readfiles.exception.InvalidRecordException;

@Service
public class PrintService {
	
	private DateTimeFormatter formatter;
	
	
	public PrintService() {
		formatter = new DateTimeFormatterBuilder()
			.appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
			.appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
			.toFormatter();
		
		//formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	}
	
	
	
	public FileRecord generateFileRecord(List<String> header, List<List<String>> records) throws InvalidHeaderException, InvalidRecordException {
		
		FileRecord file = new FileRecord();
		
		if (header.size() != 6) {
			throw new InvalidHeaderException("invalid header");
		} else {
			
			HeaderRecord headerRecord = new HeaderRecord();
			headerRecord.setNameLabel(header.get(0));
			headerRecord.setAddressLabel(header.get(1));
			headerRecord.setPostcodeLabel(header.get(2));
			headerRecord.setPhoneLabel(header.get(3));
			headerRecord.setCreditLimitLabel(header.get(4));
			headerRecord.setBirthdayLabel(header.get(5));
			
			file.setHeaderRecord(headerRecord);
		}
		
		for (List<String> record : records) {
			if (record.size() != 6) {
				throw new InvalidRecordException("invalid record (" + record + ")" );
			} else {
				
				PersonRecord personRecord = new PersonRecord(record.get(0), record.get(1), record.get(2), record.get(3)
						, convertCreditLimit(record.get(4)), LocalDate.parse(record.get(5), formatter));
				file.getPersonRecords().add(personRecord );
			}
		}
		
		
		
		return file;
	}
	
	private BigDecimal convertCreditLimit(String creditLimit) {
        if(creditLimit.endsWith("0")){
            return new BigDecimal(creditLimit).divide(new BigDecimal("100"));
        }else{
            return new BigDecimal(creditLimit);
        }
    }

}
