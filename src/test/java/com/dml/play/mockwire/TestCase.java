package com.dml.play.mockwire;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = SpringGatewayApp.class)
@AutoConfigureMockMvc
public class TestCase {
	
	private int wiremock_port = 8080;
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule( wiremock_port );
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testCase() throws Exception {
		
		stubFor(
				get(
						urlPathMatching("/gerrit/.*")
					).willReturn(
							aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "application/json")
								.withBody("\"testing-library\": \"WireMock\"")
								)
				);
		
	    mvc.perform(
	    			get("/gerrit/employees")
	    				.contentType(MediaType.APPLICATION_JSON)
	    		)
	    	  	.andExpect(status().isOk())
	    	    .andExpect(
	    	    		content()
	    	    		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
	    	    		);
		
	}
	
}
