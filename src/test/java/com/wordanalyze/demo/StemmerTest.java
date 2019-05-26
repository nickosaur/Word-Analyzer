package com.wordanalyze.demo;

import com.wordanalyze.demo.Utilities.Stemmer;
import org.junit.Test;

public class StemmerTest {

    private Stemmer stemmer = new Stemmer();

    @Test
    public void test1forStep1a() throws Exception{
        stemmer.setOriginalWord("caresses");
        assert ("caress".equals(stemmer.Stem()));
    }

    @Test
    public void test1forIrregularVerbs() throws Exception{
        stemmer.setOriginalWord("ate");
        assert ("eat".equals(stemmer.Stem()));
    }
}
