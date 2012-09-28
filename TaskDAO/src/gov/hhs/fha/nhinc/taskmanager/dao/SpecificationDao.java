/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.dao;

import gov.hhs.fha.nhinc.taskmanager.model.Specification;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class SpecificationDao {

    ObjectDao<Specification> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(SpecificationDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param specification Specification object to save.
     */
    public void save(Specification specification) {
        log.debug("Performing specification save");

        try {
            objectDao.save(specification);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed specification save");
    }

    /**
     * Delete a specification
     *
     * @param specification Specification to delete
     */
    public void delete(Specification specification) {
        log.debug("Performing specification delete");

        try {
            objectDao.delete(specification);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed specification delete");
    }

    /**
     * Retrieve a specification by identifier
     *
     * @param specificationId Specification identifier
     * @return Retrieved specification
     */
    public Specification findById(Long specificationId) {
        Specification specification = null;

        log.debug("Performing specification retrieve using id: " + specificationId);

        try {
            specification = objectDao.findById(specificationId, Specification.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed specification retrieve by id");

        return specification;
    }

    /**
     * Retrieves all specifications
     *
     * @return All specification records
     */
    @SuppressWarnings("unchecked")
    public List<Specification> findAll() {
        List<Specification> specifications = null;

        log.debug("Performing retrieve of all specifications");

        try {
            specifications = objectDao.findAll(Specification.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed specification retrieve all");

        return specifications;
    }

}

