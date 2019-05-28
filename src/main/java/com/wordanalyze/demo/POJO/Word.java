package com.wordanalyze.demo.POJO;

/**
 * The word class is meant to be the value of the frequencyHashMap
 * It holds the frequency and holds the original shortest length word of
 * what Stemmer parses.
 * For example:
 *      If "swimming", "swims", "swim" were to appear in the text
 *      there will be one Word object that looks like this:
 *      {
 *          frequency : 3,
 *          word : "swim"
 *      }
 *
 */
public class Word {
    private String word;
    private int frequency;

    public Word(){
        frequency = 0;
        word = "";
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void incrementFrequency(){
        this.frequency++;
    }

    public int getCount(){
        return this.frequency;
    }

    public String getWord(){
        return this.word;
    }

    public void updateWord(String word){
        if(this.word.length() == 0 || this.word.length() > word.length()){
            setWord(word);
        }
        incrementFrequency();
    }
}
