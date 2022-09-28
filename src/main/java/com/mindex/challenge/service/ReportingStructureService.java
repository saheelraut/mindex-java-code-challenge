package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface ReportingStructureService {
    /**
     * Get a filled out reporting structure
     *
     * @param id Employee id
     * @return Reporting Structure
     */
    ReportingStructure read(String id);
}
