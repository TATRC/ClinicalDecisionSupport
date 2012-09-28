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
public class SchemeList {
    @XmlElement(name="scheme")
    @XmlElementWrapper(name = "schemes")
    public List<SchemeInfo> schemes = new LinkedList<SchemeInfo>();

    public void sort()
    {
        Collections.sort(schemes, new SchemeComparitor());

    }

    private class SchemeComparitor implements java.util.Comparator<SchemeInfo>
    {
        public int compare(SchemeInfo l, SchemeInfo r)
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
