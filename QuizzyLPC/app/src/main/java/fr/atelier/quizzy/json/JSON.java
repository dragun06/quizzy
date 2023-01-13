package fr.atelier.quizzy.json;

import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class JSON {
    public static void JSONWriterSeen(File file, int id) throws JSONException {
        JSONArray jsonArray;
        FileOutputStream out;
        boolean gjfa;

        if (getJSONFromAndroid(file) == null) {
            jsonArray= new JSONArray();
            gjfa = false;
        } else {
            jsonArray = new JSONArray(getJSONFromAndroid(file));
            gjfa = true;
        }

        try {
            out = new FileOutputStream(file, true);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));

            writer.beginArray();
            if (!jsonArray.toString().contains("" + id)) {
                writer.beginObject();
                writer.name("id").value(id);
                writer.endObject();
            }

            if (gjfa) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    writer.beginObject();
                    writer.name("id").value(jsonArray.getJSONObject(i).getInt("id"));
                    writer.endObject();
                }
            }

            writer.endArray();
            writer.close();
            writer.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getJSONFromAndroid(File file) {
        String json;

        try {
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public static boolean isSeen(File file, int id) throws JSONException {
        JSONArray jsonArray;

        if (getJSONFromAndroid(file) != null) {
            jsonArray = new JSONArray(getJSONFromAndroid(file));

            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getInt("id") == id) {
                    return true;
                }
            }
        }

        return false;
    }
}
