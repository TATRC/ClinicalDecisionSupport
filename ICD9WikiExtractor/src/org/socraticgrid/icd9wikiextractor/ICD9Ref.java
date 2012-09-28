/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.icd9wikiextractor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jerry Goodnough
 */
public class ICD9Ref {
    public String ICD9Code = null;
    public String ICD9Link ="";
    private static String hICDRef = "http://www.icd9data.com/getICD9Code.ashx?icd9=";

    LinkedList<String> links = new LinkedList<String>();

    public ICD9Ref()
    {

    }

    public ICD9Ref(String ICD9Link)
    {
        this.setICD9Link(ICD9Link);


    }

    public void setICD9Link(String ICD9Link)
    {
        this.ICD9Link=ICD9Link;
        //Parse code
        if (ICD9Link.startsWith(hICDRef))
        {
            ICD9Code = ICD9Link.substring(hICDRef.length());
        }

    }


    public void setICD9Code(String ICD9Code )
    {
        this.ICD9Code = ICD9Code;

    }
    public String getICD9Code()
    {
        return this.ICD9Code;
    }

    public List<String> getLinks()
    {
        return links;
    }

    public void addLink(String link)
    {
        links.add(link);
    }

    public String toString()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("ICD9 Code Reference to code ");
        buff.append(ICD9Code);
        buff.append(", links: ");
        Iterator<String> itr = links.iterator();
        while(itr.hasNext())
        {
            buff.append(itr.next());
            if (itr.hasNext())
            {
                buff.append(", ");
            }
        }
        return buff.toString();
    }

}
