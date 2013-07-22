/**
 * 
 */
package fr.unice.i3s.wimmics.radio.utilities;

/**
 * @author Edou
 *
 */

import android.util.Log;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonHelper {
    /**
     * Get an instance of a given class from a json object
     * @param theJson the json object
     * @param classToReturn the class
     * @return instance of the given class
     */
    public static Object getObjectFromJson(Object theJson, Class<?> classToReturn, String jsonKey) {
        try {
            // Creates the json object which will manage the information received
            GsonBuilder builder = new GsonBuilder();
// Register an adapter to manage the date types as long values
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });
            GsonBuilder gsonb = new GsonBuilder();
            //gsonb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            DateDeserializer ds = new DateDeserializer();
            gsonb.registerTypeAdapter(Date.class, ds);
            Gson gson = gsonb
                   // .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat(DateFormat.LONG)
                    .create();
            JSONObject jsonObject = new JSONObject(theJson.toString());
            String str;
            if(!jsonKey.equals(""))
            {
                str =jsonObject.get(jsonKey).toString();
            }  else{
                str =jsonObject.toString();
            }
            return gson.fromJson(str, classToReturn);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Log.v("Exception","Exception " +e.getMessage());
            return null;
        }
    }

    public static String getResponseFromJson(Object theJson) {
        try {
            JSONObject jsonObject = new JSONObject(theJson.toString());
            if(jsonObject.has(ApplicationConstant.RESPONSE_KEY))
            {
                return  jsonObject.getString(ApplicationConstant.RESPONSE_KEY);
            } else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Log.v("Exception",e.getMessage());
            return "";
        }
    }

    public static String JsonKeyValue(Object theJson, String JsonKey) {
        try {
            JSONObject jsonObject = new JSONObject(theJson.toString());
            return jsonObject.getString(JsonKey);

        } catch (Exception e) {
            return "";
        }
    }

    public static boolean JsonHasKey(Object theJson, String JsonKey) {
        try {
            JSONObject jsonObject = new JSONObject(theJson.toString());
            return jsonObject.has(JsonKey);

        } catch (Exception e) {
            return false;
        }
    }

    public static int getResponseCodeFromJson(Object theJson) {
        try {
            JSONObject jsonObject = new JSONObject(theJson.toString());
            if(jsonObject.has(ApplicationConstant.RESPONSE_CODE))
            {
                return   jsonObject.getInt(ApplicationConstant.RESPONSE_CODE);
            } else{
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Log.v("Exception",e.getMessage());
            return -1;
        }
    }

    /**
     * get a list of objects of a given class
     * @param theJsonObject the json object
     * @param typeOfTheList type of the list
     * @param key
     * @return  return the list as an java object
     */
    public static Object getListObjectFromJson(String theJsonObject, Type typeOfTheList, String key) {
        try {
            GsonBuilder gsonb = new GsonBuilder();
            DateDeserializer ds = new DateDeserializer();
            gsonb.registerTypeAdapter(Date.class, ds);
            // gsonb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Gson gson = gsonb
                   // .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat(DateFormat.LONG)
                    .create();
            JSONObject jsonObject = new JSONObject(theJsonObject.toString());
            Object object = gson.fromJson(jsonObject.get(key).toString(), typeOfTheList);
            Log.v("Return", object.toString() + " " + object.toString());
            return object;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public static Object getListObjectFromJson(Object theJsonObject, Type typeOfTheList) {
        try {
            GsonBuilder gsonb = new GsonBuilder();
            DateDeserializer ds = new DateDeserializer();
            gsonb.registerTypeAdapter(Date.class, ds);
            // gsonb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Gson gson = gsonb
                   // .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat(DateFormat.LONG)
                    .create();
           // JSONObject jsonObject = new JSONObject(theJsonObject.toString());
            Object object = gson.fromJson(theJsonObject.toString(), typeOfTheList);
            Log.v("Return", object.toString() + " " + object.toString());
            return object;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }



    public static boolean isResponseSucess(Object theJsonObject)
    {
        try {
            if(theJsonObject !=null)
            {
                JSONObject jsonObject = new  JSONObject(theJsonObject.toString());
                if (jsonObject.has(ApplicationConstant.RESPONSE_CODE))
                {
                    return jsonObject.getBoolean(ApplicationConstant.RESPONSE_CODE);
                } else
                {
                    return false ;
                }
            }  else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }

    }

    /**
     *
     * @param toTransformInJson the instance of class to serialize
     * @return a json object from the given instance or null if error occurs
     */
    public static JSONObject createJsonObject(Object toTransformInJson) {
        GsonBuilder gsonb = new GsonBuilder();
        DateSerializer ds = new DateSerializer();
        gsonb.registerTypeAdapter(Date.class, ds);
        Gson gson = gsonb
               // .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(DateFormat.LONG)
                .create();

        // Gson gson = new GsonBuilder()

        //       .create();
        JSONObject jsonUserModel = new JSONObject();
        try {
            String result = gson.toJson(toTransformInJson);
            jsonUserModel = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return jsonUserModel;
    }

    /**
     * Create a json array from a list of object
     * @param listOfObjectToTransformInJsonArray the list of objects
     * @return a json array
     */
    public static JSONArray createJsonArray(ArrayList<?> listOfObjectToTransformInJsonArray) {
        JSONArray jsonArray = null;
        Gson gson = new GsonBuilder().create();
        try {
            jsonArray = new JSONArray(gson.toJson(listOfObjectToTransformInJsonArray));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            jsonArray = null;
        } finally {
            return jsonArray;
        }
    }
}

