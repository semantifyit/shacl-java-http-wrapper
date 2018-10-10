package org.sti2.lib;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileUtils;
import org.topbraid.jenax.util.JenaUtil;
import org.topbraid.shacl.validation.ValidationUtil;

/**
 * Validator
 */
public class Validator {

    Model dataModel;
    Model shapeModel;


    public Validator(URL dataUri, URL shapeUri) throws Exception
    {
        try
        {
            init();
            dataModel.read(dataUri.toString(), "urn:sdo-webapi", "JSONLD");
            shapeModel.read(shapeUri.toString(), "urn:sdo-webapi", FileUtils.langTurtle);

        }
        catch (Exception ex) {
            throw new Exception("Error loading models form files: " + ex.getMessage());
        }
    }

    public Validator(String dataModelStr, String shapeModelStr)
    {
        init();
        this.dataModel.read(new ByteArrayInputStream(dataModelStr.getBytes()), "urn:sdo-webapi", FileUtils.langTurtle);
        this.shapeModel.read(new ByteArrayInputStream(dataModelStr.getBytes()), "urn:sdo-webapi", FileUtils.langTurtle);
 

    }

    private void init()
    {
        this.dataModel = JenaUtil.createMemoryModel();
        this.shapeModel = JenaUtil.createMemoryModel();
    }

    public String validate() throws Exception
    {
        try
        {

        Resource report = ValidationUtil.validateModel(dataModel, shapeModel, true);
        StringWriter out = new StringWriter();
        report.getModel().write(out, FileUtils.langTurtle);
        this.shapeModel.close();
        return out.toString();
        }
        catch (Exception ex)
        {
            throw new Exception("Error on validating data with shapes: " + ex.getMessage());
            
        }
        
        
    }
}