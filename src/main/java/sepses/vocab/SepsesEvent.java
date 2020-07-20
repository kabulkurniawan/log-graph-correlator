package sepses.vocab;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class SepsesEvent {
	  /**
     * <p>The namespace of the vocabulary as a string</p>
     */
    public static final String NS = "http://w3id.org/sepses/vocab/event/core#";
    public static final String NS_INSTANCE = "http://w3id.org/sepses/resource/event/core/";
    /**
     * <p>The RDF model that holds the vocabulary terms</p>
     */
    private static Model m_model = ModelFactory.createDefaultModel();
    /**
     * <p>The namespace of the vocabulary as a resource</p>
     */
    public static final Resource NAMESPACE = m_model.createResource(NS);
    public static final Resource SEPSESEVENT = m_model.createResource(NAMESPACE+"SepsesEvent");
    public static final Property DETECTEDLOG = m_model.createProperty(NAMESPACE+"detectedLog");
    public static final Property HAS_RULE = m_model.createProperty(NAMESPACE+"hasRule");
    
    
    
    }
