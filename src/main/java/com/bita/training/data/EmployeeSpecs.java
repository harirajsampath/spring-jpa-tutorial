package com.bita.training.data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bita.training.model.Employee;

public class EmployeeSpecs {

	public static Specification<Employee> isSenior(int seniorAge) {
		Specification<Employee> majorCriteria = new Specification<Employee>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.gt(root.get("age"), seniorAge);
			}
		};
		return majorCriteria;
	}

}
