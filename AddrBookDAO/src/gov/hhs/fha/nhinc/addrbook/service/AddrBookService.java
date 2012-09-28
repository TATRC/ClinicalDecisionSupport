/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbook.service;

import gov.hhs.fha.nhinc.addrbook.dao.AddressItemDao;
import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import gov.hhs.fha.nhinc.addrbook.model.AddressQueryParams;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AddrBookService {

    private static Log log = LogFactory.getLog(AddrBookService.class);

    /**
     * Save address record.
     *
     * @param addr Address object to save.
     */
    public void saveAddress(AddressItem addr)
    {
        log.debug("Saving a address");

        if (addr != null)
        {
            if (addr.getAddressId() != null)
            {
                log.debug("Performing an update for address: " + addr.getAddressId());
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        AddressItemDao dao = new AddressItemDao();
        dao.save(addr);
    }

    /**
     * Delete a address
     *
     * @param addr Address to delete
     * @throws DBServiceException
     */
    public void deleteAdress(AddressItem addr) throws DBServiceException
    {
        log.debug("Deleting a address");
        AddressItemDao dao = new AddressItemDao();

        if (addr == null)
        {
            throw new DBServiceException("Address to delete was null");
        }

        dao.delete(addr);
    }

    /**
     * Retrieve a address by identifier
     *
     * @param addrId Address identifier
     * @return Retrieved address
     */
    public AddressItem getAddress(Long addrid)
    {
        AddressItemDao dao = new AddressItemDao();
        return dao.findById(addrid);
    }

    /**
     * Retrieves all addresses
     *
     * @return All address records
     */
    public List<AddressItem> getAllAddresses()
    {
        AddressItemDao dao = new AddressItemDao();
        return dao.findAll();
    }

    /**
     * Address query
     *
     * @param params Address query parameters
     * @return Query results
     */
    public List<AddressItem> addressQuery(AddressQueryParams params)
    {
        AddressItemDao dao = new AddressItemDao();
        return dao.findAddresses(params);
    }

}
