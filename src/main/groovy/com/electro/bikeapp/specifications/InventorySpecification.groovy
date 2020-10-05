package com.electro.bikeapp.specifications

import com.electro.bikeapp.domains.BikeDomain
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class InventorySpecification implements Specification<BikeDomain> {

    private List<SearchCriteria> list

    InventorySpecification() {
        this.list = new ArrayList<>()
    }

    void add(SearchCriteria criteria) {
        list.add(criteria)
    }

    @Override
    Predicate toPredicate(Root<BikeDomain> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>()

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation() == SearchOperation.GREATER_THAN) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()))
            } else if (criteria.getOperation() == SearchOperation.LESS_THAN) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()))
            } else if (criteria.getOperation() == SearchOperation.GREATER_THAN_EQUAL) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()))
            } else if (criteria.getOperation() == SearchOperation.LESS_THAN_EQUAL) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()))
            } else if (criteria.getOperation() == SearchOperation.NOT_EQUAL) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()))
            } else if (criteria.getOperation() == SearchOperation.EQUAL) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()))
            } else if (criteria.getOperation() == SearchOperation.MATCH) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"))
            } else if (criteria.getOperation() == SearchOperation.MATCH_END) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"))
            } else if (criteria.getOperation() == SearchOperation.MATCH_START) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()))
            } else if (criteria.getOperation() == SearchOperation.IN) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.value))
            } else if (criteria.getOperation() == SearchOperation.NOT_IN) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.value))
            }
        }

        return builder & (predicates.toArray(new Predicate[0]) as Predicate)
    }
}