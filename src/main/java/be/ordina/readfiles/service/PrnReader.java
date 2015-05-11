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

public class PrnReader implements FileReader {
    private final Resource resource;
 
    public PrnReader(String name) {
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
