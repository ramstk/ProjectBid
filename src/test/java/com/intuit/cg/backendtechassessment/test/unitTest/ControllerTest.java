///**
// * Testing sample for verifying Controllers
// */
//package com.intuit.cg.backendtechassessment.test.unitTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import com.intuit.cg.backendtechassessment.controller.BuyerController;
//import com.intuit.cg.backendtechassessment.domain.Buyer;
//
///**
// * @author RAM
// *
// */
//@RunWith(SpringRunner.class)
////@SpringBootTest
//@WebMvcTest(BuyerController.class)
//public class ControllerTest {
//
//	 
//	    @Autowired
//	    private MockMvc mvc;
//	 
//	    @MockBean
//	    private BuyerController service;
//	 
//	    @Test
//	    public void givenProjects_whenfindAll_thenReturnJsonArray()
//	      throws Exception {
//	         
//	        Buyer buy = new Buyer();
//	        buy.setbName("Ram");
//	        buy.setID((long) 1);
//	     
//	        List<Buyer> allBuyers = Arrays.asList(buy);
//	     
//	        assertThat(service.findAllBuyers()).isEqualTo(allBuyers);
//	     
//	        mvc.perform(get("/buyers")
//	          .contentType(MediaType.APPLICATION_JSON))
//	          .andExpect(status().isOk())
//	          .andExpect((ResultMatcher) ((ResultActions) content())
//	          .andExpect((ResultMatcher) jsonPath("$[0].name", is(buy.getbName()))));
//	    }
//	}
//
