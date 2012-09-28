/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.artifacts;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jerry Goodnough
 */
@XmlRootElement
public class Section{

    public String name;
    public String body;
    public CommonMetadata metadata = new CommonMetadata();
    public CommonErrorState callStatus = new CommonErrorState();


}
