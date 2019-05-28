package com.wordanalyze.demo.POJO;


import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word w1, Word w2){

        if (w1.getCount() > w2.getCount()){
            return -1;
        }

        if (w1.getCount() < w2.getCount()){
            return 1;
        }
        return 0;
    }
}