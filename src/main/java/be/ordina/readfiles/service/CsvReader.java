package be.ordina.readfiles.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CsvReader implements FileReader {
	private static final String SEPARATOR = ",";
	 
    private final Resource resource;
 
    public CsvReader(String name) {
    	resource = new ClassPathResource(name);
    }
    
    private Reader getSource()  {
    	Reader reader = null;
    	try {
    		InputStream in = resource.getInputStream();
    		reader = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return reader;
    }
    
    
    @Override
    public List<String> readHeader() {
        try (BufferedReader reader = new BufferedReader(getSource())) {
            return (List<String>) reader.lines()
                    .findFirst()
                    .map(line -> Arrays.asList(line.split(SEPARATOR)))
                    .get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }    
    
    
    
    @Override
    public List<List<String>> readRecords() {
        try (BufferedReader reader = new BufferedReader(getSource())) {
            return   reader.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(createRegex(), -1)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    } 
    
    private String createRegex() {
    	String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                ",                         "+ // match a comma
                "(?=                       "+ // start positive look ahead
                "  (                       "+ //   start group 1
                "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                "    %s                    "+ //     match 'quotedString'
                "  )*                      "+ //   end group 1 and repeat it zero or more times
                "  %s*                     "+ //   match 'otherThanQuote'
                "  $                       "+ // match the end of the string
                ")                         ", // stop positive look ahead
                otherThanQuote, quotedString, otherThanQuote);
        
        return regex;
    }
}
