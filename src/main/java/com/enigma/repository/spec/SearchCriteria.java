package com.enigma.repository.spec;

import com.enigma.util.QueryOperator;

public class SearchCriteria {
    private String key;
    private QueryOperator operator;
    private String value;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, QueryOperator operator, String value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public SearchCriteria setKey(String key) {
        this.key = key;
        return this;
    }

    public QueryOperator getOperator() {
        return operator;
    }

    public SearchCriteria setOperator(QueryOperator operator) {
        this.operator = operator;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SearchCriteria setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", operator=" + operator +
                ", value='" + value + '\'' +
                '}';
    }
}
