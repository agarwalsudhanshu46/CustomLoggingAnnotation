package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * This calls added Junit Test case for Controller class. Note: if in case, we
 * are calling service class from controller layer, then we can use @MockBean
 * annotation to create the instance of service class.
 * 
 * @author sagarwal
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class LoggingControllerJunitTest {

	private static final String REQUEST_PARAM_PHONE_NUMBER_DATA_URI = "phoneNumber";
	private static final String LOGGING_PHONE_NUMBER_DATA_URI = "/api/v1/logging/phoneData";
	private static final String REQUEST_PARAM_NAME_DATA_URI = "name";
	private static final String LOGGING_ITEM_DATA_URI = "/api/v1/logging/itemData/{itemId}";
	private static final String LOGGING_NAME_DATA_URI = "/api/v1/logging/nameData";
	@Autowired
	private MockMvc mockMvc;

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
