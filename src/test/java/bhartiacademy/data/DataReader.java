package bhartiacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	// We have pasted this METHOD IN BASE TEST to utilize it in actual test without creation of the object as the BASE TEST IS PARENT CLASS OF 
	//actual test
	/*
	 * public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
	 * //reading json to string String jsonContent = FileUtils.readFileToString(new
	 * File(System.getProperty("user.dir")+
	 * "//src//test//java//bhartiacademy//data//PurchaseOrder.json"),
	 * StandardCharsets.UTF_8); // the purpose of adding the chaset is to remove the
	 * deprecated issue. New system introduced this functionality.
	 * 
	 * // Convert String to HASHMAP we use dependency JACKSON DATABIND
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); List<HashMap<String,String>> data =
	 * mapper.readValue(jsonContent, new
	 * TypeReference<List<HashMap<String,String>>>() { });
	 * 
	 * // now the data stores two HASHMAPS as a LIST of HASHMAP {map,map} return
	 * data;
	 * 
	 * 
	 * }
	 */
}
