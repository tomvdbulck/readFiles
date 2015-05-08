package be.ordina.readfiles.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        try {
        	URL url = this.getClass().getResource("/Workbook2.csv");
        	Path path = Paths.get(url.toURI());
            Reader reader = Files.newBufferedReader(
                path, Charset.forName("ISO-8859-1"));
           csvReader = new CsvReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
