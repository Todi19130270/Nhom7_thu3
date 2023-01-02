package web.java.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
    
    private String value;
    
    public HttpUtil (String value) {
	this.value = value;
    }
    
    public static HttpUtil of (BufferedReader reader) {
	StringBuilder sb = new StringBuilder();
	String line;
	try {
	    while((line = reader.readLine()) != null ) {
	        sb.append(line);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    System.err.print(e.getMessage());
	}
	return new HttpUtil(sb.toString());
	
    }
}
