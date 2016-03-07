package restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import errorhandling.*;

/*
 * Different ways to use listsum service to calculate sum of the array:
 * http://localhost:9080/restdemo/rest/calculator/listsum?input=1,2,3,4,5,10,20,30,-100,30
 * http://localhost:9080/restdemo/rest/calculator/listsum/1,2,3,4,5,10,20,30,-100,30
 * http://localhost:9080/restdemo/rest/calculator/listsum/1,2,3,4,5,10,20,30,-100,30/xml
 * http://localhost:9080/restdemo/rest/calculator/listsum/1,2,3,4,5,10,20,30,-100,30/text
 */

@Path("calculator")
public class Calculator {
    @GET
    @Path("double")
    @Produces(MediaType.APPLICATION_JSON)
    public Result getDouble(@QueryParam("input") String input)  throws GenericAppException {
        Result result = new Result("double");
        result.setInput(input);
        try {
          result.setOutput(new Double(input) * new Double(input));
      	} catch (NumberFormatException ne) {
          	throw new GenericAppException(ne);
        };

        return result;
    }

    @GET
    @Path("listsum/{input}")
    @Produces(MediaType.APPLICATION_JSON)
    public Result getSum(@PathParam("input") String input)  throws GenericAppException{
        Result result = new Result("listsum");
        result.setInput(input);
        try {
            result.setOutput(countSum(input));
        } catch (NumberFormatException ne) {
        	throw new GenericAppException(ne);
        };

        return result;
    }
    
    @GET
    @Path("listsum/{input}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Result getSum2(@PathParam("input") String input) throws GenericAppException{
        Result result = new Result("listsum");
        result.setInput(input);
        try {
            result.setOutput(countSum(input));
        } catch (NumberFormatException ne) {
        	throw new GenericAppException(ne);
        };
        return result;
    }
    
    @GET
    @Path("listsum/{input}/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSum3(@PathParam("input") String input) throws GenericAppException{
    	double result = 0.0;
    	try {
          result = countSum(input);
    	} catch (NumberFormatException ne) {
        	throw new GenericAppException(ne);
        };
        return "sum of '" + input + "' = " + new Double(result).toString();
    }
    
    @GET
    @Path("listsum")
    @Produces(MediaType.APPLICATION_JSON)
    public Result getSum4(@QueryParam("input") String input) throws GenericAppException{
        Result result = new Result("listsum");
        result.setInput(input);
        try {
            result.setOutput(countSum(input));
        } catch (NumberFormatException ne) {
        	throw new GenericAppException(ne);
        };

        return result;
    }

    public static double countSum(String inputlist) {
    	double sum = 0.0;
    	String spacesRemovedInputList = inputlist.replaceAll("\\s+","");
    	String[] list = spacesRemovedInputList.split(",");
    	
    	for (String item: list ) {
    		double itemAsDouble = new Double(item);
    		sum += itemAsDouble;
    	}
    	return sum;   	
    }
    
    
    @XmlRootElement
    static class Result{
        String input;
        double output;
        String method;

        public Result(){}

        public Result(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }
        

        public double getOutput() {
            return output;
        }

        public void setOutput(double output) {
            this.output = output;
        }
    }
}
