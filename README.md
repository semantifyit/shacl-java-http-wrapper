# SHACL Java HTTP Wrapper

This is a small application that exposes basic SHACL validation functionality from Topbraid SHACL Java API over HTTP.

Check application.wadl file for parameter names and endpoints.
Requests /validations resource with form-url-encoded content type is working well. The values of the parameters dataGraphUri and shapeGraphUri should be the respective URLs of the files in JSON-LD and Turtle format respectively at the moment.

See https://github.com/TopQuadrant/shacl for more information regarding SHACL and the API itself.