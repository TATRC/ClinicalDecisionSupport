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
public class SchemeInfo {
    String scheme;

    @XmlElement(name="scheme")
    public String name;
    public String codes;
    public String canonicalId;
    @XmlElementWrapper(name = "aliases")
    public List<String> aliases = new LinkedList<String>();


}
