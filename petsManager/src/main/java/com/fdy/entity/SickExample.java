package com.fdy.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SickExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SickExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNull() {
            addCriterion("petname is null");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNotNull() {
            addCriterion("petname is not null");
            return (Criteria) this;
        }

        public Criteria andPetnameEqualTo(String value) {
            addCriterion("petname =", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotEqualTo(String value) {
            addCriterion("petname <>", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThan(String value) {
            addCriterion("petname >", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThanOrEqualTo(String value) {
            addCriterion("petname >=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThan(String value) {
            addCriterion("petname <", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThanOrEqualTo(String value) {
            addCriterion("petname <=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLike(String value) {
            addCriterion("petname like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotLike(String value) {
            addCriterion("petname not like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameIn(List<String> values) {
            addCriterion("petname in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotIn(List<String> values) {
            addCriterion("petname not in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameBetween(String value1, String value2) {
            addCriterion("petname between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotBetween(String value1, String value2) {
            addCriterion("petname not between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnumIsNull() {
            addCriterion("petnum is null");
            return (Criteria) this;
        }

        public Criteria andPetnumIsNotNull() {
            addCriterion("petnum is not null");
            return (Criteria) this;
        }

        public Criteria andPetnumEqualTo(Integer value) {
            addCriterion("petnum =", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumNotEqualTo(Integer value) {
            addCriterion("petnum <>", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumGreaterThan(Integer value) {
            addCriterion("petnum >", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("petnum >=", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumLessThan(Integer value) {
            addCriterion("petnum <", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumLessThanOrEqualTo(Integer value) {
            addCriterion("petnum <=", value, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumIn(List<Integer> values) {
            addCriterion("petnum in", values, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumNotIn(List<Integer> values) {
            addCriterion("petnum not in", values, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumBetween(Integer value1, Integer value2) {
            addCriterion("petnum between", value1, value2, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetnumNotBetween(Integer value1, Integer value2) {
            addCriterion("petnum not between", value1, value2, "petnum");
            return (Criteria) this;
        }

        public Criteria andPetTypeIsNull() {
            addCriterion("pet_type is null");
            return (Criteria) this;
        }

        public Criteria andPetTypeIsNotNull() {
            addCriterion("pet_type is not null");
            return (Criteria) this;
        }

        public Criteria andPetTypeEqualTo(String value) {
            addCriterion("pet_type =", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeNotEqualTo(String value) {
            addCriterion("pet_type <>", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeGreaterThan(String value) {
            addCriterion("pet_type >", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pet_type >=", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeLessThan(String value) {
            addCriterion("pet_type <", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeLessThanOrEqualTo(String value) {
            addCriterion("pet_type <=", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeLike(String value) {
            addCriterion("pet_type like", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeNotLike(String value) {
            addCriterion("pet_type not like", value, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeIn(List<String> values) {
            addCriterion("pet_type in", values, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeNotIn(List<String> values) {
            addCriterion("pet_type not in", values, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeBetween(String value1, String value2) {
            addCriterion("pet_type between", value1, value2, "petType");
            return (Criteria) this;
        }

        public Criteria andPetTypeNotBetween(String value1, String value2) {
            addCriterion("pet_type not between", value1, value2, "petType");
            return (Criteria) this;
        }

        public Criteria andSickTypeIsNull() {
            addCriterion("sick_type is null");
            return (Criteria) this;
        }

        public Criteria andSickTypeIsNotNull() {
            addCriterion("sick_type is not null");
            return (Criteria) this;
        }

        public Criteria andSickTypeEqualTo(String value) {
            addCriterion("sick_type =", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeNotEqualTo(String value) {
            addCriterion("sick_type <>", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeGreaterThan(String value) {
            addCriterion("sick_type >", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sick_type >=", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeLessThan(String value) {
            addCriterion("sick_type <", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeLessThanOrEqualTo(String value) {
            addCriterion("sick_type <=", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeLike(String value) {
            addCriterion("sick_type like", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeNotLike(String value) {
            addCriterion("sick_type not like", value, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeIn(List<String> values) {
            addCriterion("sick_type in", values, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeNotIn(List<String> values) {
            addCriterion("sick_type not in", values, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeBetween(String value1, String value2) {
            addCriterion("sick_type between", value1, value2, "sickType");
            return (Criteria) this;
        }

        public Criteria andSickTypeNotBetween(String value1, String value2) {
            addCriterion("sick_type not between", value1, value2, "sickType");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}