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

public class PrnReader implements FileReader {
    private final String filePath;
 
    public PrnReader(String path) {
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
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
		return reader;
    }
    
    
    
    @Override
    public List<String> readHeader() {
        try (BufferedReader reader = new BufferedReader(getSource())) {
            return (List<String>) reader.lines()
                    .findFirst()
                    .map(line -> Arrays.asList(line.substring(0, 16).trim()
                    		, line.substring(16,38).trim()
                    		, line.substring(38, 47).trim()
                    		, line.substring(47, 61).trim()
                    		, line.substring(61, 74).trim()
                    		, line.substring(74).trim()))
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
                    .map(line -> Arrays.asList(line.substring(0, 16).trim()
                    		, line.substring(16,38).trim()
                    		, line.substring(38, 47).trim()
                    		, line.substring(47, 61).trim()
                    		, line.substring(61, 74).trim()
                    		, line.substring(74).trim()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    } 
    
}
