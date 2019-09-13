package com.qci.fish.RoomDataBase.sample;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeConverator {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<SampleFishTypeList> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<SampleFishTypeList>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<SampleFishTypeList> someObjects) {
        return gson.toJson(someObjects);
    }
}
