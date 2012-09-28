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
public class OldCodesInScheme {
    String scheme;

    @XmlElement(name="code")
    @XmlElementWrapper(name = "codes")
    public List<OldCodeReference> schemes = new LinkedList<OldCodeReference>();
}
