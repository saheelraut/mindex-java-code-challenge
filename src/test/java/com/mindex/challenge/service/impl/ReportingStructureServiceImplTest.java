package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String ReportingStructureUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        ReportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testReportingStructure() {

        Employee employee1 = new Employee();
        employee1.setEmployeeId("62c1084e-6e34-4630-93fd-9153afb65309");
        employee1.setFirstName("Pete");
        employee1.setLastName("Best");
        employee1.setDepartment("Engineering");
        employee1.setPosition("Developer II");
        employee1.setDirectReports(null);

        Employee employee2 = new Employee();
        employee2.setEmployeeId("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        employee2.setFirstName("George");
        employee2.setLastName("Harrison");
        employee2.setDepartment("Engineering");
        employee2.setPosition("Developer III");
        employee2.setDirectReports(null);

        Employee employee = new Employee();
        employee.setEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        employee.setFirstName("Ringo");
        employee.setLastName("Starr");
        employee.setDepartment("Engineering");
        employee.setPosition("Developer V");
        List<Employee> directReports = new ArrayList<>();
        directReports.add(employee1);
        directReports.add(employee2);
        employee.setDirectReports(directReports);

        ReportingStructure expected = new ReportingStructure();
        expected.setEmployee(employee);
        expected.setNumberOfReports(2);

        // Read Reporting Structure
        ReportingStructure actual = restTemplate.getForEntity(ReportingStructureUrl, ReportingStructure.class, "03aa1462-ffa9-4978-901b-7c001562cf6f").getBody();
        assertNotNull(actual);
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());

        assertEquals(expected.getEmployee().getDirectReports().get(0).getEmployeeId(), actual.getEmployee().getDirectReports().get(0).getEmployeeId());
        assertEquals(expected.getEmployee().getDirectReports().get(0).getFirstName(), actual.getEmployee().getDirectReports().get(0).getFirstName());
        assertEquals(expected.getEmployee().getDirectReports().get(0).getLastName(), actual.getEmployee().getDirectReports().get(0).getLastName());
        assertEquals(expected.getEmployee().getDirectReports().get(0).getDepartment(), actual.getEmployee().getDirectReports().get(0).getDepartment());
        assertEquals(expected.getEmployee().getDirectReports().get(0).getPosition(), actual.getEmployee().getDirectReports().get(0).getPosition());

        assertEquals(expected.getEmployee().getDirectReports().get(1).getEmployeeId(), actual.getEmployee().getDirectReports().get(1).getEmployeeId());
        assertEquals(expected.getEmployee().getDirectReports().get(1).getFirstName(), actual.getEmployee().getDirectReports().get(1).getFirstName());
        assertEquals(expected.getEmployee().getDirectReports().get(1).getLastName(), actual.getEmployee().getDirectReports().get(1).getLastName());
        assertEquals(expected.getEmployee().getDirectReports().get(1).getDepartment(), actual.getEmployee().getDirectReports().get(1).getDepartment());
        assertEquals(expected.getEmployee().getDirectReports().get(1).getPosition(), actual.getEmployee().getDirectReports().get(1).getPosition());
        assertEquals(expected.getNumberOfReports(),actual.getNumberOfReports());

    }
}
