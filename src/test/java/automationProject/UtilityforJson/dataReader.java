package automationProject.UtilityforJson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	public List<HashMap<String, String>> getJSONData() throws IOException {

//		read json to string 
		String jsonContenet = FileUtils.readFileToString(new File(
				"C://Users//Tushar Yadav//eclipse-workspace//SeleniumTestNgFramework//src//test//java//automationProject//UtilityforJson//dataResource.json"));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=	mapper.readValue(jsonContenet,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
		

	}
}
