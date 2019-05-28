package com.wordanalyze.demo.POJO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Result {

    private String fileName;
    private boolean stopSetting;
    private List<Word> wordList;

    public Result(){
        this.fileName = "";
        this.stopSetting = true; // true by default
        this.wordList = new ArrayList<>();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStopSetting(boolean stopSetting) {
        this.stopSetting = stopSetting;
    }

    public void insertToWordList(Word word){
        this.wordList.add(word);
    }

    public String getFileName() {
        return fileName;
    }

    public boolean getStopSetting(){
        return stopSetting;
    }

    public List<Word> getWordList() {
        return wordList;
    }
}
