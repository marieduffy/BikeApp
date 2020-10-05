package com.electro.bikeapp.specifications

class SearchCriteria {
    String key
    Object value
    SearchOperation operation

    SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key
        this.value = value
        this.operation = operation
    }

    @Override
    String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation +
                '}'
    }
}
