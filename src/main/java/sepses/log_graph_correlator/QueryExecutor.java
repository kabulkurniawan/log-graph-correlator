package sepses.log_graph_correlator;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class QueryExecutor {
	
	public  static ArrayList<RDFNode> executeQuery(Model model, String queryString) throws FileNotFoundException{
		ArrayList<RDFNode> node = new ArrayList<RDFNode>();
		 QueryExecution qexec = QueryExecutionFactory.create(queryString, model);
		 ResultSet result = qexec.execSelect();
		 while (result.hasNext())
	       {
	         QuerySolution soln = result.nextSolution() ;
	         RDFNode x = soln.get("s") ;       // Get a result variable by name.
	         node.add(x);
	         //System.out.println(x);
	       }
		 return  node; 
	}

}
