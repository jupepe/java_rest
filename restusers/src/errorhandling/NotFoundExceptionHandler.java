package errorhandling;


import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.*;
import javax.ws.rs.ext.*;

 
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> 
{
    @Override
    public Response toResponse(NotFoundException exception) 
    {
        return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();  
    }
}