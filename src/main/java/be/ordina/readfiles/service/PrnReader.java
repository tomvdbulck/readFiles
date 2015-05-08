package be.ordina.readfiles.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrnReader implements FileReader {
	private static final String SEPARATOR = ",";
	 
    private final Reader source;
 
    public PrnReader(Reader source) {
        this.source = source;
    }
    
    
    @Override
    public List<String> readHeader() {
        try (BufferedReader reader = new BufferedReader(source)) {
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
        try (BufferedReader reader = new BufferedReader(source)) {
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
