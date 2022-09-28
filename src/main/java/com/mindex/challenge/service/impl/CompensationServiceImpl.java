package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Write what this method does
     *
     * @param id                  Employee ID
     * @param compensationRequest
     * @return Compensation
     * @throws ParseException
     */
    @Override
    public Compensation insert(String id, CompensationRequest compensationRequest) throws ParseException {
        LOG.debug("Inserting compensation for employeeId [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        Compensation compensation = new Compensation();
        compensation.setEmployeeId(id);
        compensation.setEmployee(employee);
        compensation.setEffectiveDate(simpleDateFormat.parse(compensationRequest.getEffectiveDate()));
        compensation.setSalary(compensationRequest.getSalary());
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * @param id Employee id
     * @return Compensation
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Getting compensation for employeeId [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        Compensation compensation = compensationRepository.findByEmployeeId(id);
        if (compensation == null) {
            throw new RuntimeException("No Compensation Exists for this Employee" + id);
        }
        return compensation;
    }
}
