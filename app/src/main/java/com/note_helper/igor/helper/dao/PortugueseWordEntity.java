package com.note_helper.igor.helper.dao;

import io.realm.RealmObject;

/**
 * @author Igor Gnes on 3/10/17.
 */

public class PortugueseWordEntity extends RealmObject {

    private String por_words;

    public String getPor_words() {
        return por_words;
    }

    public void setPor_words(String por_words) {
        this.por_words = por_words;
    }
}
