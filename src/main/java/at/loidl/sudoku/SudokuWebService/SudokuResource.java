package at.loidl.sudoku.SudokuWebService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.loidl.sudoku.bl.SudokuService;
import at.loidl.sudoku.model.Sudoku;


@Path("sudoku")
public class SudokuResource {

	@GET
	@Path("/{knownvalues}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneSudoku(@PathParam("knownvalues") String parmKnownValues, @QueryParam("layout") String parmLayout) {
		int[][] values = Sudoku.convertToArray(parmKnownValues);
		int[][] layout= (parmLayout==null || parmLayout.isEmpty()) ? null : Sudoku.convertToArray(parmLayout);
		
		if (values==null || layout==null) {
			return Response.serverError().build();
		}
		Sudoku result = new SudokuService().solve(new Sudoku(values,layout));
		return Response.ok().entity(result).header("Access-Control-Allow-Origin", "*").build();
    }

}
