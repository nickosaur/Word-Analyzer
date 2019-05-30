package com.wordanalyze.demo.POJO;

import java.text.SimpleDateFormat;
import java.util.*;

public class Result{

    private String originalFileName;
    private String newFileName; // includes modified name
    private boolean stopSetting;
    private List<Word> wordList;

    public Result(){
        this.originalFileName = "";
        this.newFileName = "";
        this.stopSetting = false; // false by default
        this.wordList = new ArrayList<>();
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public void setOriginalFileName(String originalFileName){
        this.originalFileName = originalFileName;
    }

    public void setStopSetting(boolean stopSetting) {
        this.stopSetting = stopSetting;
    }

    public void insertToWordList(Word word){
        this.wordList.add(word);
    }

    public String getNewFileName() {
        return newFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public boolean getStopSetting(){
        return stopSetting;
    }

    public List<Word> getWordList() {
        return wordList;
    }


}
