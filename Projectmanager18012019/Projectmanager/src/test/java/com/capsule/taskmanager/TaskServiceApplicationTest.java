package com.capsule.taskmanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.capsule.taskmanager.controller.TaskController;
import com.capsule.taskmanager.model.Task;
import com.capsule.taskmanager.service.TaskService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=TaskManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskServiceApplicationTest {	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(taskController)                
				.build();
	}	
	
	@Test
	public void testGetAllTasks() throws Exception {

		Date mockStartDate = getDate("start");
		Date mockEndDate = getDate("end");

		Task mockJavaTask = new Task();		
		mockJavaTask.setTaskId(1234L);
		mockJavaTask.setTaskName("Java String Operations");
		mockJavaTask.setPriorityFrom(0);
		mockJavaTask.setPriorityTo(25);
		mockJavaTask.setTaskStatus("Started");
		mockJavaTask.setStartDate(mockStartDate);
		mockJavaTask.setStartDate(mockEndDate);
		mockJavaTask.setParentId(1L);
		
		
		Task mockPythonTask = new Task();
		mockPythonTask.setTaskId(1236L);
		mockPythonTask.setTaskName("Python numpy");
		mockPythonTask.setPriorityFrom(0);
		mockPythonTask.setPriorityTo(30);
		mockPythonTask.setTaskStatus("Started");
		mockPythonTask.setStartDate(mockStartDate);
		mockPythonTask.setStartDate(mockEndDate);
		mockPythonTask.setParentId(2L);

		List<Task> taskList = new ArrayList<Task>();
		taskList.add(mockJavaTask);
		taskList.add(mockPythonTask);				

		when(taskService.getTask()).thenReturn(taskList);

		mockMvc.perform(get("/taskmanager/alltasks"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].taskId", is(1234)))
		.andExpect(jsonPath("$[0].taskName", is("Java String Operations")))
		.andExpect(jsonPath("$[0].startDate", is(10-12-2018)))
		.andExpect(jsonPath("$[0].endDate", is(10-01-2019)))
		.andExpect(jsonPath("$[0].priorityFrom", is(0)))
		.andExpect(jsonPath("$[0].priorityTo", is(25)))
		.andExpect(jsonPath("$[0].taskStatus", is("Started")))
		.andExpect(jsonPath("$[0].parentId", is(1)))
		
		.andExpect(jsonPath("$[1].taskId", is(1236)))
		.andExpect(jsonPath("$[1].taskName", is("Python numpy")))
		.andExpect(jsonPath("$[1].startDate", is(10-12-2018)))
		.andExpect(jsonPath("$[1].endDate", is(10-01-2019)))
		.andExpect(jsonPath("$[1].priorityFrom", is(0)))
		.andExpect(jsonPath("$[1].priorityTo", is(30)))
		.andExpect(jsonPath("$[1].taskStatus", is("Started")))
		.andExpect(jsonPath("$[1].parentId", is(2)));

		verify(taskService, times(1)).getTask();
		verifyNoMoreInteractions(taskService);
	}
	
	
	@Test
	public void testTaskById() throws Exception {

		Date mockStartDate = getDate("start");
		Date mockEndDate = getDate("end");

		Task mockJavaTask = new Task();		
		mockJavaTask.setTaskId(1234L);
		mockJavaTask.setTaskName("Java String Operations");
		mockJavaTask.setPriorityFrom(0);
		mockJavaTask.setPriorityTo(25);
		mockJavaTask.setTaskStatus("Started");
		mockJavaTask.setStartDate(mockStartDate);
		mockJavaTask.setStartDate(mockEndDate);
		mockJavaTask.setParentId(1L);
		
		when(taskService.findById(1234L)).thenReturn(mockJavaTask);
		mockMvc.perform(get("/taskmanager/alltasks/{taskId}", 1234L))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.taskId", is(1234)))
		.andExpect(jsonPath("$.taskName", is("Java String Operations")))
		.andExpect(jsonPath("$.startDate", is(10-12-2018)))
		.andExpect(jsonPath("$.endDate", is(10-01-2019)))
		.andExpect(jsonPath("$.priorityFrom", is(0)))
		.andExpect(jsonPath("$.priorityTo", is(25)))
		.andExpect(jsonPath("$.taskStatus", is("Started")))
		.andExpect(jsonPath("$.parentId", is(1)));

		verify(taskService, times(1)).findById(1234L);
		verifyNoMoreInteractions(taskService);
	}
	
	
	@Test
	public void testAddTask() throws Exception{

		Date mockStartDate = getDate("start");
		Date mockEndDate = getDate("end");

		Task mockPythonTask = new Task();
		mockPythonTask.setTaskId(1236L);
		mockPythonTask.setTaskName("Python numpy");
		mockPythonTask.setPriorityFrom(0);
		mockPythonTask.setPriorityTo(30);
		mockPythonTask.setTaskStatus("Started");
		mockPythonTask.setStartDate(mockStartDate);
		mockPythonTask.setStartDate(mockEndDate);
		mockPythonTask.setParentId(2L);			
		
		
        when(taskService.createTask(mockPythonTask)).thenReturn(mockPythonTask);
     
        mockMvc.perform(
                post("/taskmanager/alltasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockPythonTask)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/taskmanager/alltasks")));
       
       verify(taskService, times(1)).createTask(mockPythonTask);
       verifyNoMoreInteractions(taskService);	

	}
	
	

	@Test
	public void testUpdateTask() throws Exception {

		Date mockStartDate = getDate("start");
		Date mockEndDate = getDate("end");

		Task mockPythonTask = new Task();
		mockPythonTask.setTaskId(1236L);
		mockPythonTask.setTaskName("Python numpy");
		mockPythonTask.setPriorityFrom(0);
		mockPythonTask.setPriorityTo(30);
		mockPythonTask.setTaskStatus("Started");
		mockPythonTask.setStartDate(mockStartDate);
		mockPythonTask.setStartDate(mockEndDate);
		mockPythonTask.setParentId(2L);	

        when(taskService.findById(mockPythonTask.getTaskId())).thenReturn(mockPythonTask);
        doNothing().when(taskService).editTask(mockPythonTask,mockPythonTask.getTaskId());
		
        mockMvc.perform(
        				put("/taskmanager/alltasks/{taskId}", mockPythonTask.getTaskId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockPythonTask)))
                .andExpect(status().isOk());

        verify(taskService, times(1)).findById(mockPythonTask.getTaskId());
        verify(taskService, times(1)).editTask(mockPythonTask,mockPythonTask.getTaskId());
       verifyNoMoreInteractions(taskService);     
	}
	
	public static Date getDate(String taskStat){
		DateFormat dateFormat;
		Date mockDate = null;
		try {
			dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			if(taskStat.equals("start")) {
				mockDate = dateFormat.parse("10-12-2018");// for example, today's date
			}else {
				mockDate = dateFormat.parse("10-01-2019");// for example, today's date
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return mockDate;
	}
	
	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
