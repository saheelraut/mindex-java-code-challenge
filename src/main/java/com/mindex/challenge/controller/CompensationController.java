package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    @Autowired
    private CompensationService compensationService;

    /**
     * Endpoint for create compensation
     *
     * @param id                  Employee id
     * @param compensationRequest Compensation Request containing Salary and Effective Date
     * @return Compensation
     * @throws ParseException
     */
    @PostMapping("/compensation/{id}")
    public Compensation insertCompensation(@PathVariable String id, @RequestBody CompensationRequest compensationRequest) throws ParseException {

        LOG.debug("Received insert Compensation request for id [{}]", id);
        return compensationService.insert(id, compensationRequest);
    }

    /**
     * Endpoint to get Compensation by Employee id
     *
     * @param id Employee id
     * @return Compensation
     */
    @GetMapping("/compensation/{id}")
    public Compensation getCompensation(@PathVariable String id) {
        LOG.debug("Received read Compensation request for id [{}]", id);
        return compensationService.read(id);
    }
}
