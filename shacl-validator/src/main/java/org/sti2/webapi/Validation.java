package org.sti2.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import java.net.MalformedURLException;
import java.net.URL;

import org.sti2.lib.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("validations")
public class Validation {

    /**
     * Method handling SHACL validations
     *
     * @return returns a validation result in turtle format
     */
    @POST
    @Produces("text/turtle")
    @Consumes(MediaType.APPLICATION_JSON)
    public String validate(SHACLGraphs shaclGraphs) {

        Validator validator = new Validator(shaclGraphs.getDataGraph(), shaclGraphs.getShapeGraph());
        try {
            return validator.validate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return e.getMessage();
        }
        
    }

    @POST
    @Produces("text/turtle")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String validateByUri(MultivaluedMap<String, String> shaclGraphs) throws MalformedURLException
    {
        Validator validator;
        try {
            validator = new Validator(new URL(shaclGraphs.get("dataGraphUri").get(0)),
                    new URL(shaclGraphs.get("shapeGraphUri").get(0)));
            
             return validator.validate();
        } catch (Exception e) {
            return e.getMessage();
            
        }

       
    }
}
