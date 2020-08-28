package com.bank.account.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.common.Constants;
import com.bank.account.dtos.TransactionDetailsDto;
import com.bank.account.dtos.TransactionDTO;
import com.bank.account.services.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/transactions")
@Api(value = "Management of customer transactions")
public class TransactionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "Create Transaction", response = TransactionDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"), @ApiResponse(code = 400, message = "You are sending an invalid request"),
			@ApiResponse(code = 404, message = "A resource is not found") })
	@PostMapping(path = "/{idAccount}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionDTO createTransaction(@PathVariable(name = "idAccount") Long idAccount,
			@RequestBody TransactionDetailsDto transactionDetails) {
		LOGGER.info(Constants.BEGIN_CREATION_TRANSACTION, idAccount);
		final TransactionDTO transactionDTO = transactionService.createTransaction(idAccount, transactionDetails);
		LOGGER.info(Constants.END_CREATION_TRANSACTION);
		return transactionDTO;
	}
	@ApiOperation(value = "Get Bank Account transactions history", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "You are sending an invalid request"),
			@ApiResponse(code = 404, message = "A resource is not found") })
	@GetMapping(path = "/all/{idAccount}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionDTO> getBankAccountTransactionHistory(@PathVariable(name = "idAccount") Long idAccount) {
		LOGGER.info(Constants.BEGIN_RETRIEVE_ACCOUNT_TRANSACTIONS_HISTORY, idAccount);
		final List<TransactionDTO> transactionDTOs = transactionService.getBankAccountTransactionHistory(idAccount);
		LOGGER.info(Constants.END_RETRIEVE_ACCOUNT_TRANSACTIONS_HISTORY);
		return transactionDTOs;
	}
	@ApiOperation(value = "Get Bank Account balanace", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "You are sending an invalid request"),
			@ApiResponse(code = 404, message = "A resource is not found") })
	@GetMapping(path="/balance/{idAccount}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Double getBalance(@PathVariable(name = "idAccount") Long idAccount) {
		return transactionService.readAccountBalance(idAccount);
	}

}
