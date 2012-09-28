/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.artifacts;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jerry Goodnough
 */
@XmlRootElement
public class SearchResult {

    public String searchCodeScheme = "Unknown";
    public String searchCode ;
    @XmlElement(name="matches")
    @XmlElementWrapper(name = "codes")
    public List<Article> codes = new LinkedList<Article>();
}
