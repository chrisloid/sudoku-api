package at.loidl.sudoku.SudokuWebService;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import at.loidl.sudoku.model.Sudoku;

public class SudokuResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(SudokuResource.class);
    }


    @Test
    public void testGetIt() {
    	Response response = target("sudoku").request().get();
		int status = response.getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testPostIt() {
		int knownValues[][] =  { 
			 {0,0,0, 0,5,9, 0,0,0 }
			,{4,0,0, 0,0,0, 0,0,5 }
			,{2,0,0, 0,9,0, 0,0,8 }
	
			,{0,4,0, 0,0,0, 0,6,0 }
			,{0,0,0, 0,0,0, 0,0,1 }
			,{0,0,0, 0,0,0, 0,2,0 }
	
			,{8,0,0, 0,2,0, 0,0,6 }
			,{9,0,0, 0,0,0, 0,0,2 }
			,{0,0,0, 3,7,8, 0,0,0 }
		};

		int layout[][] =  { 
			 {0,0,0,1,1,1,1,2,2}
			,{3,5,0,0,0,0,1,2,6}
			,{3,5,0,5,0,1,1,2,6}
			,{3,5,5,5,1,1,2,2,6}
			,{3,3,3,5,2,2,2,6,6}
			,{4,3,5,5,6,6,6,6,7}
			,{4,3,3,4,4,8,7,7,7}
			,{4,4,4,4,8,8,8,8,7}
			,{4,8,8,8,8,7,7,7,7}
		};

		Sudoku sudokuRequest = new Sudoku(knownValues,layout);


        String response = target("sudoku")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(sudokuRequest), String.class);
        
        assertEquals(true, response.contains("solved="));
    }
}
