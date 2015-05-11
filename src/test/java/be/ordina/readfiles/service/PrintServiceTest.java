package be.ordina.readfiles.service;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import be.ordina.readfiles.dto.FileRecord;
import be.ordina.readfiles.exception.InvalidHeaderException;
import be.ordina.readfiles.exception.InvalidRecordException;

public class PrintServiceTest {
	
	private PrintService printService = null;
	private DateTimeFormatter dateTimeFormatter;
	
	@Before
	public void createFileReader() throws URISyntaxException {
		printService = new PrintService();
		
		dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	@Test
	public void testHeaderOnly() throws Exception {
		List<String> header = generateCorrectHeader();
		
		FileRecord record = printService.generateFileRecord(header, new ArrayList<List<String>>());
		
		Assert.assertEquals("name", record.getHeader().getNameLabel());
		Assert.assertEquals("address", record.getHeader().getAddressLabel());
		Assert.assertEquals("postcode", record.getHeader().getPostcodeLabel());
		Assert.assertEquals("phone", record.getHeader().getPhoneLabel());
		Assert.assertEquals("credit", record.getHeader().getCreditLimitLabel());
		Assert.assertEquals("birthday", record.getHeader().getBirthdayLabel());
	}
	@Test
	public void testCorrectFileCSV() throws Exception {
		List<String> header = generateCorrectHeader();
		
		List<String> record1 = new ArrayList<String>();
		record1.add("Gibson, Mal");
		record1.add("Vredenburg 21");
		record1.add("3209 DD");
		record1.add("06-48958986");
		record1.add("54.5");
		record1.add("09/11/1978");
		
		List<String> record2 = new ArrayList<String>();
		record2.add("Friendly, User");
		record2.add("Sint Jansstraat 32");
		record2.add("4420 EE");
		record2.add("0885-291029");
		record2.add("54");
		record2.add("10/08/1980");
		
		List<List<String>> fileData = new ArrayList<List<String>>();
		fileData.add(record1);
		fileData.add(record2);
		
		FileRecord record = printService.generateFileRecord(header, fileData);
		Assert.assertEquals(2, record.getPersonRecords().size());
		
		Assert.assertEquals(new BigDecimal("54.5"), record.getPersonRecords().get(0).getCreditLimit());
		Assert.assertEquals(new BigDecimal("54"), record.getPersonRecords().get(1).getCreditLimit());
		Assert.assertEquals(LocalDate.parse("09/11/1978", dateTimeFormatter), record.getPersonRecords().get(0).getBirthday());
		Assert.assertEquals(LocalDate.parse("10/08/1980", dateTimeFormatter), record.getPersonRecords().get(1).getBirthday());
		
		
		
	} 
	
	@Test
	public void testCorrectFilePRN() throws Exception {
		List<String> header = generateCorrectHeader();
		
		List<String> record1 = new ArrayList<String>();
		record1.add("Gibson, Mal");
		record1.add("Vredenburg 21");
		record1.add("3209 DD");
		record1.add("06-48958986");
		record1.add("5450");
		record1.add("19781109");
		
		List<String> record2 = new ArrayList<String>();
		record2.add("Friendly, User");
		record2.add("Sint Jansstraat 32");
		record2.add("4420 EE");
		record2.add("0885-291029");
		record2.add("54");
		record2.add("19990920");
		
		List<List<String>> fileData = new ArrayList<List<String>>();
		fileData.add(record1);
		fileData.add(record2);
		
		FileRecord record = printService.generateFileRecord(header, fileData);
		
		Assert.assertEquals(2, record.getPersonRecords().size());
		
		Assert.assertEquals(new BigDecimal("54.5"), record.getPersonRecords().get(0).getCreditLimit());
		Assert.assertEquals(new BigDecimal("54"), record.getPersonRecords().get(1).getCreditLimit());
		Assert.assertEquals(LocalDate.parse("09/11/1978", dateTimeFormatter), record.getPersonRecords().get(0).getBirthday());
		Assert.assertEquals(LocalDate.parse("20/09/1999", dateTimeFormatter), record.getPersonRecords().get(1).getBirthday());
		
		
	} 
	
	@Test(expected=InvalidRecordException.class)
	public void testInvalidFile() throws Exception {
		List<String> record1 = new ArrayList<String>();
		record1.add("Gibson, Mal");
		record1.add("Vredenburg 21");
		
		List<List<String>> fileData = new ArrayList<List<String>>();
		fileData.add(record1);
		
		printService.generateFileRecord(generateCorrectHeader(), fileData);
		
	} 
	
	@Test(expected=InvalidHeaderException.class)
	public void testInvalidHeader() throws Exception {
		List<String> header = new ArrayList<String>();
		
		printService.generateFileRecord(header, new ArrayList<List<String>>());
		
	} 
	
	private List<String> generateCorrectHeader() {
		List<String> header = new ArrayList<String>();
		header.add("name");
		header.add("address");
		header.add("postcode");
		header.add("phone");
		header.add("credit");
		header.add("birthday");
		return header;
	} 
}

