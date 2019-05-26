package com.wordanalyze.demo;

import com.wordanalyze.demo.Utilities.Stemmer;
import org.junit.Test;

public class StemmerTest {

    @Test
    public void test1Step1a() throws Exception{
        assert ("caress".equals(Stemmer.Stem("caresses")));
    }
}
