package sepses.log_graph_correlator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;

import sepses.vocab.*;

public class EventCorrelator {
	
	public static Model generateEvent(ArrayList<RDFNode> logId, RDFNode ruleId, RDFNode desc) {
		UUID uuid = UUID.randomUUID();
		
		Model m = ModelFactory.createDefaultModel();
		Resource subj = m.createResource(SepsesEvent.NS_INSTANCE+"event-"+uuid.toString());
		 Timestamp curtimestamp = new Timestamp(System.currentTimeMillis());
	      
		for (RDFNode rdfNode : logId) {
			m.add(subj, SepsesEvent.DETECTEDLOG, rdfNode);
			m.add(subj,RDF.type,SepsesEvent.SEPSESEVENT);
			m.add(subj, SepsesEvent.HAS_RULE,ruleId.toString());
			m.add(subj,DCTerms.created,curtimestamp.toString());
			m.add(subj,DCTerms.description,desc);
		}
		
		return m;
	}

}
