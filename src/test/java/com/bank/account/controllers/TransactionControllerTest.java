package com.bank.account.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.account.services.TransactionService;

@WebMvcTest(TransactionController.class)

public class TransactionControllerTest {

	@InjectMocks
	private TransactionController transactionController;


	@MockBean
	private TransactionService transactionService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {

	}

	@DisplayName("Test call create transaction with a valid body")
	@Test
	public void testCreateTransactionStatusCreated() throws Exception {
		this.mockMvc.perform(post("/api/v1/transactions/1").accept(MediaType.APPLICATION_JSON).content(
				"{\"transactionType\":\"DEPOSIT_OPERATION\",\"amount\":\"897\", \"motive\":\"First deposit operation\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	/**
	 * Test create transaction with empty body (expected status 400.
	 * 
	 * @throws Exception
	 */
	@DisplayName("Test call create transaction without a body")
	@Test
	public void testCreateTransactionStatusKO() throws Exception {

		this.mockMvc.perform(post("/api/v1/transactions/1")).andDo(print()).andExpect(status().is(400));
	}

	/**
	 * Test create transaction with empty body (expected status 400.
	 * 
	 * @throws Exception
	 */
	@DisplayName("Test call read account balance")
	@Test
	public void testReadAccountBalance() throws Exception {

		this.mockMvc.perform(get("/api/v1/transactions/balance/1")).andDo(print()).andExpect(status().is(200));
	}
	
	/**
	 * Test create transaction with empty body (expected status 400.
	 * 
	 * @throws Exception
	 */
	@DisplayName("Test call read transactions history")
	@Test
	public void testReadTransactionHistory() throws Exception {

		this.mockMvc.perform(get("/api/v1/transactions/all/1")).andDo(print()).andExpect(status().is(200));
	}
}
