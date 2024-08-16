import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;



public class PropertyJsonReader {
     
     
     
     public ArrayList<Square> Read(){
         
    	 ArrayList<Square> Squares = new ArrayList<Square>();
    	 
    	 JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             
                          
             
             for(Object i:Land){
            	 
            	 Squares.add(new Land(Integer.parseInt((String)((JSONObject)i).get("id")),
            			 			 	(String)((JSONObject)i).get("name"),
            			 			 	Integer.parseInt((String)((JSONObject)i).get("cost"))));
				 
                 
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
            	 
            	 Squares.add(new RailRoad(Integer.parseInt((String)((JSONObject)i).get("id")),
			 			 	(String)((JSONObject)i).get("name"),
			 			 	Integer.parseInt((String)((JSONObject)i).get("cost"))));
            	 	 
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
            	 
            	 Squares.add(new Company(Integer.parseInt((String)((JSONObject)i).get("id")),
			 			 	(String)((JSONObject)i).get("name"),
			 			 	Integer.parseInt((String)((JSONObject)i).get("cost"))));

             }
             
         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }
		return Squares;
     }
     //You can add function(s) if you want
}