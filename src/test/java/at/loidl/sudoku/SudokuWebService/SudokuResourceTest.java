package at.loidl.sudoku.SudokuWebService;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class SudokuResourceTest extends JerseyTest {

    

	@Override
    protected Application configure() {
        return new ResourceConfig(SudokuResource.class);
    }


    @Test
    public void testGetIt() {
    	String knowValues = 
						      "123000000" 
						    + "000000000" 
						    + "000000000"
						    + "000000000"
						    + "000000000"
						    + "000000000"
						    + "000000000"
						    + "000000000"
						    + "000000123"    
						    ;
    	
    	assertEquals(81, knowValues.length());
    	
    	Response response = target("sudoku/" + knowValues).request().get();

		assertEquals("Http Response should be 200: ", 200, response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

		String content = response.readEntity(String.class);
		System.out.println(content);
    }
}
