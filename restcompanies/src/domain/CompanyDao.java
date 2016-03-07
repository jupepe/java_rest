package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* 
 * In a real DAO implementation, we can fetch the values from a database on a GET and add new rows on a POST. 
 * In a demo application we can add a static variable that is an instance of our DAO to the DAO class.
 * So we are using Design Patterns called a singleton, a class that has a single instance.
 */

public class CompanyDao {

      static CompanyDao instance = new CompanyDao(); 

      public static CompanyDao getInstance() {
            return instance;
      }

      HashMap<Integer, Company> companies = new HashMap<Integer, Company>();
      int lastId = 13;

      private CompanyDao() {
            int key = 13;
            Company company = new Company(key, "My Company", "Hill Street", "Steel");
            companies.put(key, company);
      }

      public Company get(int key){
            return (Company)companies.get(key);
      }

      
      public List<Company> getAll(){
            List<Company> companyList = new ArrayList<Company>();
            for (Map.Entry<Integer, Company> entry : companies.entrySet()) {    
            	companyList.add(entry.getValue());
            }
            return companyList;
      }
      

      public Integer addCompany(Company company) {
            int nextKey = getNextKey();
            company.setId(nextKey);
            companies.put(nextKey, company);
            System.out.println("adding company: " + company);
            return nextKey;
      }

      private int getNextKey() {
            return ++lastId;
      }
}