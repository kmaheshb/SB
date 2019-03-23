package com.sony.features.error;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class APIResponseErrorHandler implements ResponseErrorHandler {
	
	private static Logger logger = LogManager.getLogger(APIResponseErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return RestUtil.isError(response.getStatusCode());
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		logger.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());		
	}

}
