package sepses.vocab;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Sigma {
	  /**
     * <p>The namespace of the vocabulary as a string</p>
     */
    public static final String NS = "http://w3id.org/sepses/vocab/rule/sigma#";
    public static final String NS_INSTANCE = "http://w3id.org/sepses/resource/sigma/";
    /**
     * <p>The RDF model that holds the vocabulary terms</p>
     */
    private static Model m_model = ModelFactory.createDefaultModel();
    /**
     * <p>The namespace of the vocabulary as a resource</p>
     */
    public static final Resource NAMESPACE = m_model.createResource(NS);
    public static final Property SPARQLQUERY = m_model.createProperty(NS+"sparqlQuery");
          
    
        
    
    }
