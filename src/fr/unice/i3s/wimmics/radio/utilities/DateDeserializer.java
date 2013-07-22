package fr.unice.i3s.wimmics.radio.utilities;

/**
 * Created by IntelliJ IDEA.
 * User: Edou
 * Date: 1/27/12
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */

import android.util.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateDeserializer implements JsonDeserializer<Date> {
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
        Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
        Matcher matcher = pattern.matcher(json.getAsJsonPrimitive().getAsString());
        String result = matcher.replaceAll("$2");
        Log.v("Dateeeeeeeeeeeeee", result);
        return new Date(new Long(result));
    }
}
