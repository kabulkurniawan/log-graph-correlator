package sepses.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.jena.rdf.model.Model;
import org.yaml.snakeyaml.Yaml;


public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

	
	
	private static BufferedReader buf;

	public static InputStream readFile(String file) throws FileNotFoundException {
		final File initialFile = new File(file);
		//System.out.print(initialFile);
	    final InputStream input = new FileInputStream(initialFile);
	    return input;

	}
	
   public static String readSimpleFile(String file) throws IOException {
	   InputStream is = new FileInputStream(file);
	   buf = new BufferedReader(new InputStreamReader(is));
	           
	   String line = buf.readLine();
	   StringBuilder sb = new StringBuilder();
	           
	   while(line != null){
	      sb.append(line).append("\n");
	      line = buf.readLine();
	   }
	           
	   String fileAsString = sb.toString();
	   return fileAsString;
	   
   }
	public static Map<String, Object> readYamlFile(String file) throws FileNotFoundException{
		InputStream input = readFile(file);
	    
	    Yaml yaml = new Yaml();
	    
	    Map<String, Object> yamlContent = yaml.load(input);
	    
	    
		return yamlContent;
		
	}
	
	public static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> listFiles = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	listFiles.add(fileEntry.getName());
	        }
	    }
	   return listFiles; 
	}

	public static String saveToFile(Model model, String outputDir, String fileName) {
        String outputFileName = outputDir + "/" + fileName;
        File outputFile = new File(outputFileName);
        outputFile.getParentFile().mkdirs();

        try {
            FileWriter out = new FileWriter(outputFile);
            model.write(out, "TURTLE");
            out.close();
        } catch (IOException e) {
        	
            log.error(e.getMessage(), e);
            
        }

        return outputFileName;
    }
}
