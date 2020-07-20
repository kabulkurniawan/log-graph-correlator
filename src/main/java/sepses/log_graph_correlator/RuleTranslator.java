package sepses.log_graph_correlator;

import java.io.FileNotFoundException;
import java.util.HashMap;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.DCTerms;
import sepses.vocab.Sigma;

public class RuleTranslator {


	
	public static HashMap<String, RDFNode> readQueryFromRule(String RDFRule) throws FileNotFoundException{
		HashMap<String, RDFNode> mapRule = new HashMap<String, RDFNode>();
		String queryString = "SELECT ?s ?query ?desc WHERE {?s <"+Sigma.SPARQLQUERY+"> ?query. "
		 		+ "?s <"+DCTerms.title+"> ?desc}";
		 
		 Model model = RDFDataMgr.loadModel(RDFRule) ;
		 QueryExecution qexec = QueryExecutionFactory.create(queryString, model);
		 ResultSet result = qexec.execSelect();
		 
		 while (result.hasNext())
	       {
	         QuerySolution soln = result.nextSolution() ;
	         RDFNode q = soln.get("query") ;       // Get a result variable by name.
	         RDFNode ruleID = soln.get("s") ;       // Get a result variable by name.
	         RDFNode desc = soln.get("desc") ;       // Get a result variable by name.
	         mapRule.put("query", q);
	         mapRule.put("ruleId", ruleID);
	         mapRule.put("ruleDesc", desc);
	       }
		 return mapRule;
	}
}
