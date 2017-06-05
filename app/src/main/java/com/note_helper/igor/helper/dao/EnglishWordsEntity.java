package com.note_helper.igor.helper.dao;

import io.realm.RealmObject;

/**
 * @author Igor Gnes on 3/9/17.
 */

public class EnglishWordsEntity extends RealmObject {

    private String eng_words;

    public String getEng_words() {
        return eng_words;
    }

    public void setEng_words(String eng_words) {
        this.eng_words = eng_words;
    }
}
