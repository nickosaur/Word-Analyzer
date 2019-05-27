package com.wordanalyze.demo;

import com.wordanalyze.demo.Utilities.Stemmer;
import org.junit.Test;

public class StemmerTest {

    private Stemmer stemmer = new Stemmer();

    @Test
    public void test1ForPlurals() throws Exception{
        stemmer.setOriginalWord("caresses");
        assert ("caress".equals(stemmer.Stem()));
    }

    @Test
    public void test2ForPlurals() throws Exception{
        stemmer.setOriginalWord("ponies");
        assert ("poni".equals(stemmer.Stem()));
    }

    @Test
    public void test3ForPlurals() throws Exception{
        stemmer.setOriginalWord("caress");
        assert ("caress".equals(stemmer.Stem()));
    }

    @Test
    public void test4ForPlurals() throws Exception{
        stemmer.setOriginalWord("dogs");
        assert ("dog".equals(stemmer.Stem()));
    }

    @Test
    public void test1ForPastTense() throws Exception{
        stemmer.setOriginalWord("feed");
        assert ("feed".equals(stemmer.Stem()));
    }

    @Test
    public void test2ForPastTense() throws Exception{
        stemmer.setOriginalWord("agreed");
        assert ("agre".equals(stemmer.Stem()));
    }

    @Test
    public void test3ForPastTense() throws Exception{
        stemmer.setOriginalWord("agree");
        assert ("agre".equals(stemmer.Stem()));
    }

    @Test
    public void test4ForPastTense() throws Exception{
        stemmer.setOriginalWord("hammered");
        assert ("hammer".equals(stemmer.Stem()));
    }

    @Test
    public void test5ForPastTense() throws Exception{
        stemmer.setOriginalWord("conflated");
        assert ("conflat".equals(stemmer.Stem()));
    }

    @Test
    public void test6ForPastTense() throws Exception{
        stemmer.setOriginalWord("probated");
        assert ("probat".equals(stemmer.Stem()));
    }

    @Test
    public void test7ForPastTense() throws Exception{
        stemmer.setOriginalWord("rate");
        assert ("rate".equals(stemmer.Stem()));
    }

    @Test
    public void test8ForPastTense() throws Exception{
        stemmer.setOriginalWord("cried");
        assert ("cri".equals(stemmer.Stem()));
    }

    @Test
    public void test9ForPastTense() throws Exception{
        stemmer.setOriginalWord("cries");
        assert ("cri".equals(stemmer.Stem()));
    }


    @Test
    public void test1ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("singing");
        assert ("sing".equals(stemmer.Stem()));
    }


    @Test
    public void test2ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("sing");
        assert ("sing".equals(stemmer.Stem()));
    }

    @Test
    public void test3ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("trying");
        assert ("try".equals(stemmer.Stem()));
    }

    @Test
    public void test4ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("amazingly");
        assert ("amaz".equals(stemmer.Stem()));
    }

    @Test
    public void test5ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("amazing");
        assert ("amaz".equals(stemmer.Stem()));
    }


    @Test
    public void test6ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("falling");
        assert ("fall".equals(stemmer.Stem()));
    }

    @Test
    public void test7ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("running");
        assert ("run".equals(stemmer.Stem()));
    }

    @Test
    public void test8ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("failing");
        assert ("fail".equals(stemmer.Stem()));
    }

    @Test
    public void test9ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("filing");
        assert ("file".equals(stemmer.Stem()));
    }

    @Test
    public void test10ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("controlling");
        assert ("control".equals(stemmer.Stem()));
    }

    @Test
    public void test11ForPresentContTense() throws Exception{
        stemmer.setOriginalWord("rolling");
        assert ("roll".equals(stemmer.Stem()));
    }

    @Test
    public void test1ForWordsEndWithY() throws Exception{
        stemmer.setOriginalWord("happy");
        assert ("happi".equals(stemmer.Stem()));
    }

    @Test
    public void test2ForWordsEndWithY() throws Exception{
        stemmer.setOriginalWord("sky");
        assert ("sky".equals(stemmer.Stem()));
    }

    @Test
    public void test1ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("relational");
        assert ("relat".equals(stemmer.Stem()));
    }

    @Test
    public void test2ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("vietnamization");
        assert ("vietnam".equals(stemmer.Stem()));
    }

    @Test
    public void test3ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("callousness");
        assert ("callous".equals(stemmer.Stem()));
    }

    @Test
    public void test4ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("triplicate");
        assert ("triplic".equals(stemmer.Stem()));
    }

    @Test
    public void test5ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("hopefully");
        assert ("hope".equals(stemmer.Stem()));
    }

    @Test
    public void test6ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("equipment");
        assert ("equip".equals(stemmer.Stem()));
    }

    @Test
    public void test7ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("reverence");
        assert ("rever".equals(stemmer.Stem()));
    }

    @Test
    public void test8ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("revere");
        assert ("rever".equals(stemmer.Stem()));
    }

    @Test
    public void test9ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("compulse");
        assert ("compuls".equals(stemmer.Stem()));
    }

    @Test
    public void test10ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("compulsive");
        assert ("compuls".equals(stemmer.Stem()));
    }

    @Test
    public void test11ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("adoption");
        assert ("adopt".equals(stemmer.Stem()));
    }

    @Test
    public void test12ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("analogous");
        assert ("analog".equals(stemmer.Stem()));
    }

    @Test
    public void test13ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("anachronistically");
        assert ("anachronist".equals(stemmer.Stem()));
    }

    @Test
    public void test14ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("rational");
        assert ("ration".equals(stemmer.Stem()));
    }

    @Test
    public void test15ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("home");
        assert ("home".equals(stemmer.Stem()));
    }

    @Test
    public void test16ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generationally");
        assert ("generation".equals(stemmer.Stem()));
    }

    @Test
    public void test17ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generational");
        assert ("generation".equals(stemmer.Stem()));
    }

    @Test
    public void test18ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generic");
        assert ("generic".equals(stemmer.Stem()));
    }

    @Test
    public void test19ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generous");
        assert ("generou".equals(stemmer.Stem()));
    }

    @Test
    public void test20ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generously");
        assert ("generou".equals(stemmer.Stem()));
    }

    @Test
    public void test21ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("unceremoniously");
        assert ("unceremoni".equals(stemmer.Stem()));
    }

    @Test
    public void test22ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("generating");
        assert ("generate".equals(stemmer.Stem()));
    }

    @Test
    public void test23ForComplicatedWords() throws Exception{
        stemmer.setOriginalWord("probate");
        assert ("probat".equals(stemmer.Stem()));
    }
    
}
