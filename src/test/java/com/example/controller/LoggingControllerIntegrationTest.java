package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This calls added Integration Test case for Controller class.
 * 
 * @author sagarwal
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class LoggingControllerIntegrationTest {

	private static final String REQUEST_PARAM_PHONE_NUMBER_DATA_URI = "phoneNumber";
	private static final String LOGGING_PHONE_NUMBER_DATA_URI = "/api/v1/logging/phoneData";
	private static final String REQUEST_PARAM_NAME_DATA_URI = "name";
	private static final String LOGGING_ITEM_DATA_URI = "/api/v1/logging/itemData/{itemId}";
	private static final String LOGGING_NAME_DATA_URI = "/api/v1/logging/nameData";
	@Autowired
	private WebApplicationContext applicationContext;
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();

	}

	@Test
	public void testloggingItemData() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LOGGING_ITEM_DATA_URI, "1212").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testloggingInvalidItemData() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(LOGGING_ITEM_DATA_URI, "1").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isConflict());

	}

	@Test
	public void testloggingName() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LOGGING_NAME_DATA_URI)
						.param(REQUEST_PARAM_NAME_DATA_URI, "Sudhanshu").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testInvalidloggingName() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(LOGGING_NAME_DATA_URI).param(REQUEST_PARAM_NAME_DATA_URI, "Sid")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isConflict());

	}

	@Test
	public void testloggingPhoneNumber() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LOGGING_PHONE_NUMBER_DATA_URI)
						.param(REQUEST_PARAM_PHONE_NUMBER_DATA_URI, "8756901252").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testInvalidloggingPhoneNumber() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LOGGING_PHONE_NUMBER_DATA_URI)
						.param(REQUEST_PARAM_PHONE_NUMBER_DATA_URI, "2121").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isConflict());

	}
}
