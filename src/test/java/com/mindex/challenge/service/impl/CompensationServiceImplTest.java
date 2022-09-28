package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation/{id}";

    }

    @Test
    public void testCreateReadUpdate() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


        // Create Compensation

        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Lennon");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Development Manager");
        Compensation compensation = new Compensation();
        compensation.setEmployee(testEmployee);
        compensation.setEffectiveDate(simpleDateFormat.parse("01-01-2000"));
        compensation.setSalary(20000.0);

        CompensationRequest testCompensation = new CompensationRequest();
        testCompensation.setEffectiveDate("01-01-2000");
        testCompensation.setSalary(20000.0);

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertNotNull(createdCompensation.getEffectiveDate());
        assertNotNull(createdCompensation.getSalary());
        assertCompensationEquivalence(compensation, createdCompensation);

        // Read Compensation
        Compensation readCompensation = restTemplate.getForEntity(compensationUrl, Compensation.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertNotNull(readCompensation.getEffectiveDate());
        assertNotNull(readCompensation.getSalary());
        assertCompensationEquivalence(compensation, readCompensation);

    }

    private void assertCompensationEquivalence(Compensation expected, Compensation actual) {

        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
