package restservice;

import java.text.*;
import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import errorhandling.GenericAppException;

/*
 * Return the given date as formatted string including parsed Date object as String
 * http://localhost:9080/restdemo/rest/time/format/12/02/2016
 * 
 * Response is build by using javax.ws.rs.core.Response object. You can also give status of the response. 
 */

@Path("/time")
public class TimeService {

	@GET
	@Path("/format/{day}/{month}/{year}")
	public Response generateDate(@PathParam("year") int year,
			@PathParam("month") int month, @PathParam("day") int day)
			throws GenericAppException {

		String date = day + "." + month + "." + year;

		DateFormat df = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
		Date resultDate = null;

		try {
			resultDate = df.parse(date);
		} catch (ParseException pe) {
			throw new GenericAppException(pe);
		}

		return Response
				.status(200)
				.entity("generateDate, day/month/year : " + date
						+ ", parsed to date " + resultDate).build();
	}

	@GET
	@Path("/counter/{day}/{month}/{year}")
	public Response daysBetween(@PathParam("year") int year,
			@PathParam("month") int month, @PathParam("day") int day)
			throws GenericAppException {

		String date = day + "." + month + "." + year;
		DateFormat df = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
		Date searchDate = null;

		try {
			searchDate = df.parse(date);
		} catch (ParseException pe) {
			throw new GenericAppException(pe);
		}
		Calendar searchCalendar = Calendar.getInstance();
		searchCalendar.setTime(searchDate);
		long countedDays = daysLeft(searchCalendar);

		if (searchCalendar.before(Calendar.getInstance()))
			return Response
					.status(400)
					.entity("daysBetween error! before current date: "
							+ searchDate).build();

		return Response
				.status(200)
				.entity("daysBetween from now to " + searchDate + " is "
						+ countedDays).build();
	}

	public static long daysLeft(Calendar endDate) {
		// note: startDate must be before endDate
		Calendar date = Calendar.getInstance();
		long counter = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			counter++;
		}
		return counter;
	}

}
