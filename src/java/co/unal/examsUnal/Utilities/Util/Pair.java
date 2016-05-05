/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

/**
 *
 * @author yeisondavid
 */


import java.io.Serializable;


public class Pair<K extends Object, V extends Object> implements Serializable {

    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Pair(K k, V v) {
        key = k;
        value = v;
    }

    @Override
     public int hashCode() {
         return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o instanceof Pair) {
             Pair pair = (Pair) o;
           if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
            if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
             return true;
         }
        return false;
    }
}

