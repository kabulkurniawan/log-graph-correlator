package sepses.log_graph_correlator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;

import sepses.helper.Util;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {   	
       
       Map<String, Object> s = Util.readYamlFile("config.yaml");
       String inputLogGraph= s.get("input-log-graph").toString();
       String logGraphUrl = s.get("rdf-dir").toString()+"/"+inputLogGraph;
       String queryDirectory = s.get("query-dir").toString();
       String outputDir= s.get("output-dir").toString();
       String outputRDF= s.get("output-rdf").toString();
       
       
       Model APT29Day1Model = RDFDataMgr.loadModel(logGraphUrl) ;
       //String qsExample = ut.readSimpleFile(dataCompressedQuery);      
       
       final File queryFolder = new File(queryDirectory);
       ArrayList<String> listFiles = Util.listFilesForFolder(queryFolder);
       Model generatedModel = ModelFactory.createDefaultModel();
 
       for (String filename : listFiles) {
    	   HashMap<String, RDFNode> mapRule = RuleTranslator.readQueryFromRule(queryDirectory+"/"+filename);
           ArrayList<RDFNode> result = QueryExecutor.executeQuery(APT29Day1Model, mapRule.get("query").toString());
           Model tempModel = EventCorrelator.generateEvent(result, mapRule.get("ruleId"), mapRule.get("ruleDesc"));            
           generatedModel.add(tempModel);
	    }
       
       Util.saveToFile(generatedModel, outputDir, outputRDF);
       generatedModel.write(System.out,"TURTLE");
	
    }
}
