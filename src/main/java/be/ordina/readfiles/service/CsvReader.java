package be.ordina.readfiles.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader implements FileReader {
	private static final String SEPARATOR = ",";
	 
    private final String filePath;
 
    public CsvReader(String path) {
        this.filePath = path;
    }
    
    private Reader getSource()  {
    	Reader reader = null;
    	try {
    		URL url = this.getClass().getResource(filePath);
    		Path path = Paths.get(url.toURI());
			reader = Files.newBufferedReader(path,
					Charset.forName("ISO-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
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
