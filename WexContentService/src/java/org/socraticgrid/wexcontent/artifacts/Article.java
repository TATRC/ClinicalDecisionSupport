/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.socraticgrid.wexcontent.artifacts;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Jerry Goodnough
 */
public class Article {

    public String name;
    public String codeScheme;
    public String code;
    @XmlElement(name = "section")
    @XmlElementWrapper(name = "sections")
    public List<SectionRef> sections = new LinkedList<SectionRef>();
}
