package es.usc.citius.triapp.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.GsonBuilder;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.iterators.ArrayIterator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.data.manchester.Entry;



public class Manchester {

    private static FlowChart[] workFlow = null;
    private static Entry[] dictionary = null;
    private static Manchester instance = null;
    private static List<FlowChart> workflowList = null;
    private static int currentWorkFlow = 1;
    private static int currentLevel = 0;
    private static long startTime;

    protected Manchester() {
    }

    //Singleton
    public static Manchester getInstance(Context context) {
        if(instance == null) {

            try {
                AssetManager assetManager = context.getAssets();
                InputStream input = assetManager.open("flowCharts.json");
                BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));

                //deserializa el json
                workFlow = new GsonBuilder().create().fromJson(br, FlowChart[].class);
                workflowList = IteratorUtils.toList(new ArrayIterator(workFlow));

                input = assetManager.open("entries.json");
                BufferedReader br1 = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                dictionary = new GsonBuilder().create().fromJson(br1, Entry[].class);

                Dictionary.getInstance(dictionary);
                instance = new Manchester();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static Manchester getInstance() {

        return instance;
    }

    public FlowChart[] getData() {
        return workFlow;
    }

    int getSize() {
        return workFlow.length;
    }

    public Entry[] getDictionary() {
        return dictionary;
    }

    int getDictionarySize() {
        return dictionary.length;
    }

    public FlowChart getByID(int ID) {
        return workFlow[ID];
    }

    public static List<FlowChart> getFlowchartList() {
        return workflowList;
    }

    public static void setCurrentWorkFlow(int current) {
        currentWorkFlow = current;
    }

    public static FlowChart getCurrentWorkFlow() {
        return workFlow[currentWorkFlow];
    }

    public static void setCurrentLevel(int current) {
        currentLevel = current;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setStartTime(long time) {
        startTime = time;
    }
    public static long getElapsedTime(long currentTime) {
        return (currentTime - startTime)/1000;
    }

   /* public static String getCurrentLevel() {
        return workFlow[currentWorkFlow].getLevel(currentLevel).getColor();
    }*/

    public static int levelStringConverter(String color) {
        switch (color) {
            case "red":

                return 0;
            case "orange":

                return 1;
            case "yellow":

                return 2;
            case "green":

                return 3;
        }

        return -1;
    }




}
