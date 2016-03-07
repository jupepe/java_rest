package errorhandling;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.*;
import javax.ws.rs.ext.*;

/**
 * Maps GenericAppException to corresponding Response describing the exception and it's message
 * as a output.
 * 
 * @author Juha Peltomäki
 *
 */
@Provider
public class RestExceptionHandler implements
		ExceptionMapper<GenericAppException> {
	/**
	 * @return error code 400 (BAD REQUEST) and error message as a plain text.
	 */
	@Override
	public Response toResponse(GenericAppException exception) {
		return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
				.entity(exception.getMessage()).build();
	}
}