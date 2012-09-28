/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jerry Goodnough
 */
public class FreebaseImageSearch {

    public static List<String> findImagesForId(int wpid)
    {
        //Format the iamge Query
        String wpidRef = "http://api.freebase.com/api/service/mqlread";
        String query = String.format("{\"query\":[{\"id\":\"/wikipedia/en_id/%d\",\"/common/topic/image\":[{}]}]}", wpid);

        List<String> out = new LinkedList<String>();
        //Format the MQL Query
        Client c = Client.create();
        c.setFollowRedirects(Boolean.TRUE);
        WebResource r = c.resource(wpidRef);
        r = r.queryParam("query", query);
        
        try
        {
            //Make the Query
            String response = r.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

            JSONObject jobj = new JSONObject(response);
            
            JSONArray result = jobj.getJSONArray("result");
            if (result != null)
            {
                JSONObject inner = result.getJSONObject(0);
                if (inner !=null)
                {
                    JSONArray images = inner.getJSONArray("/common/topic/image");
                    if (images != null)
                    {
                        int len = images.length();
                        for (int i = 0; i< len; i++)
                        {
                            JSONObject img = images.getJSONObject(i);
                            String id = img.getString("id");
                            if (id != null)
                            {
                                if (id.length()>0)
                                {
                                    out.add("http://www.freebase.com/api/trans/raw" + id);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(JSONException exp)
        {
            //
            System.out.println(exp.getMessage());
        }
        catch(UniformInterfaceException exp)
        {
            //Find the respose that cause the error
            ClientResponse rs = exp.getResponse();
            int stat = rs.getStatus();
            URI loc = rs.getLocation();
            System.out.println(exp.getMessage());
        }

        return out;
    }
}
