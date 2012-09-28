/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.artifacts;

import java.util.Collections;
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
public class StandardSections {
    @XmlElement(name="standardsections")
    @XmlElementWrapper(name = "sections")
    public List<SectionMetadata> sections = new LinkedList<SectionMetadata>();

   public void sort()
    {
        Collections.sort(sections, new SectionComparitor());

    }

    private class SectionComparitor implements java.util.Comparator<SectionMetadata>
    {
        public int compare(SectionMetadata l, SectionMetadata r)
        {
            System.out.println("Compare "+l.name+" to "+r.name);

            if (l.name == null)
            {
                if (r.name == null)
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
            if (r == null)
            {
                return -1;
            }

            return l.name.compareToIgnoreCase(r.name);
        }
    }
}
