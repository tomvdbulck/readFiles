package be.ordina.readfiles.service;

import java.net.URISyntaxException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrnReaderTest {
	
	private FileReader prnReader = null;
	
	
	
	@Test
	public void readsHeaderPrn() throws URISyntaxException {
		
		
		
		List<String> header = prnReader.readHeader();
		Assert.assertEquals(6, header.size());
		Assert.assertThat(header.toString(), 
				CoreMatchers.both(CoreMatchers.containsString("Name")).and(CoreMatchers.containsString("Address")));
		Assert.assertThat(header.toString(), 
				CoreMatchers.both(CoreMatchers.containsString("Postcode")).and(CoreMatchers.containsString("Phone")));
		Assert.assertThat(header.toString(), 
				CoreMatchers.both(CoreMatchers.containsString("Credit")).and(CoreMatchers.containsString("Limit")));
		Assert.assertThat(header.toString(), CoreMatchers.containsString("Birthday"));
		
		for (String field : header) {
			System.out.println("headerfield = (" + field + ")");
		}
	}
	
	
	@Test
	public void testLinesPrn() throws Exception {
		List<List<String>> lines = prnReader.readRecords();
		Assert.assertEquals(7, lines.size());
		
		for (List<String> record : lines) {
			System.out.println("line >> " + record.toString());
			Assert.assertEquals(6, record.size());
			for (String recordField : record) {
				System.out.println("field = (" + recordField + ")");
			}
			
		}	
	}
 
	
	@Before
	public void createFileReader() throws URISyntaxException {
		prnReader = new PrnReader("/Workbook2.prn");
	}
}
