
package com.springdemo.springComplete;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.springdemo.springComplete.dao.EmployeeDao;
import com.springdemo.springComplete.entity.Employee;
import com.springdemo.springComplete.resources.EmployeeController;
import com.springdemo.springComplete.service.EmployeeService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;



@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(controllers=EmployeeController.class)
//@WithMockUser(username="user",roles={"USER","ADMIN"})
public class EmployeeControllerTest {

		@Autowired
	 	private MockMvc mockMvc;
	    
	    @Autowired
	    protected WebApplicationContext wac;
	  
	    @Autowired
	    private EmployeeController employeController;
	  
	    private List<Employee> employees;
	
	    @MockBean
		private EmployeeService service1;
		
	    @Before
	    public void setup() throws Exception {
	     //  this.mockMvc = webAppContextSetup(wac).build();// Standalone context
	     //   this.mockMvc = standaloneSetup(this.employeController).build();// Standalone context
	        this.employees  = getTestData();
	    }
	    
		@Ignore
	    @Test
		public void testGetEmployees() throws Exception {
	    		when(service1.getEmploees()).thenReturn(employees);
	    		mockMvc.perform(get("/employees")
//	    				.with(httpBasic("user", "password"))
	    				.contentType(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$[0].id", is(1001)))
	             .andExpect(jsonPath("$[1].id", is(1002)))
	             .andDo(print());
	    
	    }
	    
	    private List<Employee> getTestData(){
	    	Employee e1=new Employee();
			e1.setId(1001);
			e1.setName("rakesh");
			e1.setAge(23);
			Employee e2=new Employee();
			e2.setId(1002);
			e2.setName("rohit");
			e2.setAge(254);
			List <Employee> employees=new ArrayList<Employee>();
			employees.add(e1);
			employees.add(e2);
			return employees;
		}
	    
	
}

/*
 * 
	    
	    @Test(expected=BussinessException.class)
		public void testGetEmployeesException() throws SQLException {
	    		when(daoMock.getEmployees()).thenThrow(SQLException.class);
	    		service1.getEmployees();
	   	  }
	    
	    @Ignore
	     @Test
		public void testSave() {
	    		when(daoMock.saveEmployee(any(Employee.class)))
	    					.thenReturn(true);
	    		//assertTrue(service1.insertEmployee(new Employee()));
	    		assertThat(service1.insertEmployee(new Employee()),is(true));
		}

	    @Ignore
	    @Test
	 	public void testGetEmployeeById() {
	    			Employee dummy=new Employee();
	    			dummy.setId(4534);
	     		
	    			when(daoMock.getEmployeeById(any(Integer.class)))
	     			.thenReturn(Optional.of(dummy));
	    			
	    			Employee d=service1.getEmployeeById(1001);
	     		System.out.println(d.getId());
	     		assertThat(service1.getEmployeeById(1001), is(notNullValue())); 	
	     		}
	    
	    
	    @Ignore
	    @Test
	 	public void testGetEmployeeByIdAnswer() {
	     		when(daoMock.getEmployeeById(any(Integer.class)))
	     			.thenAnswer(
	     					 invocation->{
	     						 Object[] arguments = invocation.getArguments();
	     		                if (arguments != null && arguments.length > 0 && arguments[0] != null){
	     		                		int id=(Integer)arguments[0];
	     		                		if(id>0)
	     		                			return Optional.of(new Employee());
	     		                	}
	     		                	return Optional.empty();
	     					 });
	     		assertThat(service1.getEmployeeById(1), is(notNullValue())); 
	     		}
	    
	    
	    
	    @Ignore
	    @Test(expected = EmployeeNotFoundException.class)
	    public void testGetEmployeeByIdException() {
	    	when(daoMock.getEmployeeById(any(Integer.class)))
	    				.thenReturn(Optional.empty());
	    			//	.thenReturn(Optional.of(new Employee()));
	     	service1.getEmployeeById(34);  
	    }
	    
	    
	    @Ignore
	    @Test
	    public void testFindTopOneEmployee() throws SQLException {
			when(daoMock.getEmployees()).thenReturn(getTestData());
				Employee e=service1.findTopOneEmployee();
				System.out.println(e.getName());
				//assertNotNull(e);
				//assertEquals(e.getName(), "ramesh");
		}*/
