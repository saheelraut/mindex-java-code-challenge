package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;

import java.text.ParseException;

public interface CompensationService {
    /**
     * @param id                  Employee ID
     * @param compensationRequest Compensation Request
     * @return Compensation
     * @throws ParseException
     */
    Compensation insert(String id, CompensationRequest compensationRequest) throws ParseException;

    /**
     * @param id Employee id
     * @return Compensation
     */
    Compensation read(String id);
}
