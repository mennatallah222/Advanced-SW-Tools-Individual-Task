
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/calculatorApi")
public class CalculatorService {
	
	@PersistenceContext(unitName="calculator")
	private EntityManager em;

	@POST
	@Path("/calc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCalc(Calculation calc) {
		
		try {
			Integer res;
			switch(calc.getOperation()) {
				case "+":
					res = calc.getNumber1() + calc.getNumber2();
					break;
				case "-":
					res = calc.getNumber1() - calc.getNumber2();
					break;
				case "*":
					res = calc.getNumber1() * calc.getNumber2();
					break;
				case "/":
					if (calc.getNumber2() == 0) {
						throw new IllegalArgumentException("Division by zero is NOT allowed!");
		            }
					res = calc.getNumber1() / calc.getNumber2();
					break;
				default:
					throw new IllegalArgumentException("Invalid operation!");
			}
			
			em.persist(calc); 
			return Response.ok().entity("{\"Result\" :" +res+"}").build();

        }
		catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
		
	
	@GET
	@Path("/calculations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCalculation() {
		try {
			List<Calculation> calculations = em.createQuery("select c from Calculation c", Calculation.class).getResultList();
            
			return Response.ok().entity(calculations).build();
		}
		
		catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
	}

}
