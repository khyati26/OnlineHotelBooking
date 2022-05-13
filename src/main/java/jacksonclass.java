import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonclass {
	public static void main(String args[]) throws JsonGenerationException, JsonMappingException, IOException
	{		
	      Employee emp1 = new Employee();
	      emp1.setFirstName("Raja");
	      emp1.setLastName("Ramesh");
	      emp1.setId(115);
	      emp1.getTechnologies().add("Java");
	      emp1.getTechnologies().add("Selenium");
	      emp1.getTechnologies().add("Spark");
	      ObjectMapper mapper = new ObjectMapper();
	      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
	    		
	      System.out.println(jsonStr);
	      System.out.println("Deserializing JSON to Object:");
	      Employee emp2 = mapper.readValue(jsonStr, Employee.class);
	      System.out.println(emp2.getId() + " " + emp2.getFirstName() + " " + emp2.getLastName() + " " + emp2.getTechnologies());

		  String json = "{"
		  		+ "\"data\":["
		  		+ "{\"id\":1,\"name\":\"John\"} ,"
				+"{\"name\":\"mkyong\", \"age\":\"37\"}"
				+ "]"
				+ "}";

		  Map<String, String> map = new HashMap<>();
		  
		  map = mapper.readValue(json, Map.class);
		  System.out.println(map);


	    jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);

	    String jsonArray = mapper.writeValueAsString(emp1);

		System.out.println(jsonArray);
		System.out.println("Deserializing JSON to Object:");
		 emp1 = mapper.readValue(jsonStr, Employee.class);
		System.out.println(emp1.getId() + " " + emp1.getFirstName() + " " + emp1.getLastName() + " " + emp1.getTechnologies());
	

			ObjectMapper mapper2 = new ObjectMapper();
			Employee employee = new Employee();
			employee.setFirstName("Java");
			employee.setLastName("Narola");
//			employee.setSalary(10000);
	
			Employee employeev1 = new Employee();
			employeev1.setFirstName("Java");
			employeev1.setLastName("Narola");
//			employeev1.setSalary(10000);
	
			String jsonStr2 = "{\"firstName\":\"Java\",\"lastName\":\"Narola\",\"salary\":10000}";
	
			try {
				System.out.println(mapper.writeValueAsString(employee));
				System.out.println(mapper.writeValueAsString(Arrays.asList(employee, employeev1)));
				Employee employee2 = mapper.readValue(jsonStr2.getBytes(), Employee.class);
				System.out.println(employee2);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
}

//Employee class
class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private List technologies = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List technologies) {
		this.technologies = technologies;
	}
}
