package fr.unice.i3s.wimmics.radio.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Edou
 * Date: 1/26/12
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateSerializer implements JsonSerializer<Object>
{
    public JsonElement serialize(Date date, Type typeOfT, JsonSerializationContext context)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = dateFormat.format(date);
        //objectMapper.setDateFormat(df);
        return new JsonPrimitive(dateString);
    	// return new JsonPrimitive("/Date(" + date.getTime() + ")/");
    }

    public JsonElement serialize(Object arg0, Type arg1,
                                 JsonSerializationContext arg2) {

        Date date = (Date) arg0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = dateFormat.format(date);
        //objectMapper.setDateFormat(df);
        return new JsonPrimitive(dateString);
    }
}

