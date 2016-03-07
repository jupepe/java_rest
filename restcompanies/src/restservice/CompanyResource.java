package restservice;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import domain.Company;
import domain.CompanyDao;

/*
 * curl -X GET http://localhost:9080/restcompanies/rest/companies
 * curl -X POST -H "Content-Type: application/xml" -d '<company><id>100</id><name>My Next Company</name><address>Harley Street</address><industry>Metal</industry></company>' http://localhost:9080/restcompanies/rest/companies/new
 * curl -X POST -H "Content-Type: application/xml" -d '<company><id>100</id><name>Shining Company</name><address>Savile Row</address><industry>Tele</industry></company>' http://localhost:9080/restcompanies/rest/companies/new
 * 

 */

@Path("/companies")
public class CompanyResource {
	CompanyDao dao = CompanyDao.getInstance();

	public CompanyResource() {
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Company> getCompanies() {
		return dao.getAll();
	}
	
	
	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Company> getCompaniesJson(){
		return getCompanies();
	}

	@GET
	@Path("text")
	@Produces(MediaType.TEXT_PLAIN)
    public String getCompaniesText(){
		return getCompanies().toString();
	}


	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Company getCompany(@PathParam(value = "key") int key) {
		return dao.get(key);
	}

	@Path("/new")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@POST
	public String addCompany(Company comp) {
		dao.addCompany(comp);
		return "OK";
	}
}
