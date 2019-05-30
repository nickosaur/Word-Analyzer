package com.wordanalyze.demo.Utilities;


import com.wordanalyze.demo.POJO.Word;

import java.util.Comparator;

/**
 * Comparator class used to build the priority queue in Result.java to get
 * top 10 most frequent words
 */
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