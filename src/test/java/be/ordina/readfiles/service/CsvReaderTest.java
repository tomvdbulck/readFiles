package be.ordina.readfiles.service;

import java.net.URISyntaxException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CsvReaderTest {
	
	private FileReader csvReader = null;
	
	
	
	@Test
    public void readsHeaderCsv() throws URISyntaxException {
        List<String> header = csvReader.readHeader();
        Assert.assertEquals(6, header.size());
        Assert.assertThat(header.toString(), 
        		CoreMatchers.both(CoreMatchers.containsString("Name")).and(CoreMatchers.containsString("Address")));
        Assert.assertThat(header.toString(), 
        		CoreMatchers.both(CoreMatchers.containsString("Postcode")).and(CoreMatchers.containsString("Phone")));
        Assert.assertThat(header.toString(), 
        		CoreMatchers.both(CoreMatchers.containsString("Credit")).and(CoreMatchers.containsString("Limit")));
        Assert.assertThat(header.toString(), CoreMatchers.containsString("Birthday"));
    }
	
	
	@Test
	public void testLinesCsv() throws Exception {
		List<List<String>> lines = csvReader.readRecords();
		Assert.assertEquals(7, lines.size());
		
		for (List<String> record : lines) {
			System.out.println("line >> " + record.toString());
			Assert.assertEquals(6, record.size());
			
		}	
	}
	
 
	@Before
    public void createCsvReader() throws URISyntaxException {
		csvReader = new CsvReader("/Workbook2.csv");
    }
}
