import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ListJsonReader{
     
    
	public ArrayList<Action> ChanceRead(){
		
		ArrayList<Action> Actions = new ArrayList<Action>();
		
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            
            for(Object i:chanceList){
            	
            	Actions.add(new ChanceList((String)((JSONObject)i).get("item")));
				
            }
            
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
		
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
		return Actions;
     }
	public ArrayList<Action> CommunityRead(){
		
		ArrayList<Action> Actions = new ArrayList<Action>();
		
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            
            for(Object i:chanceList){
            		
            }
            
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
				
            	Actions.add(new CommunityChanceList((String)((JSONObject)i).get("item")));
				
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
		return Actions;
     }
}

