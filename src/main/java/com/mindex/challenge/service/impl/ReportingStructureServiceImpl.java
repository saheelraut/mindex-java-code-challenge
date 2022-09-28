
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get a Reporting structure for an Employee
     *
     * @param id Employee id
     * @return Reporting structure
     */
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading reporting structure with employeeId [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        int numberOfReports = getNumberOfReports(employee);
        if (employee.getDirectReports() != null) {

            employee = fillStructure(employee);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(numberOfReports);
        return reportingStructure;
    }

    /**
     * Get number of reports for an Employee
     *
     * @param employee Employee
     * @return Total number of reports under the employee
     */
    private int getNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null) return 0;

        return employee.getDirectReports().size() +
                employee.getDirectReports().stream()
                        .map(e -> employeeRepository.findByEmployeeId(e.getEmployeeId()))
                        .filter(e -> e.getDirectReports() != null)
                        .map(e -> getNumberOfReports(e))
                        .reduce(0, (sum, e) -> sum + e);
    }


    /**
     * Fill reporting Structure
     *
     * @param employee Employee
     * @return Filled out reporting structure
     */
    private Employee fillStructure(Employee employee) {
        int i = 0;
        if (employee.getDirectReports() != null) {
            while (i < employee.getDirectReports().size()) {

                Employee temp = employeeRepository.findByEmployeeId(employee.getDirectReports().get(i).getEmployeeId());
                employee.getDirectReports().get(i).setFirstName(temp.getFirstName());
                employee.getDirectReports().get(i).setLastName(temp.getLastName());
                employee.getDirectReports().get(i).setPosition(temp.getPosition());
                employee.getDirectReports().get(i).setDepartment(temp.getDepartment());
                if (temp.getDirectReports() != null) {
                    temp = fillStructure(temp);
                }
                employee.getDirectReports().get(i).setDirectReports(temp.getDirectReports());
                i++;
            }
        }
        return employee;
    }

}