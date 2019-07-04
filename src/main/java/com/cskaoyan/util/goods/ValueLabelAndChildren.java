package com.cskaoyan.util.goods;

/**
 * @author YangShuo
 * @date 2019-07-04 9:45
 */
public class ValueLabelAndChildren<T> {
    Integer value;
    String label;
    T children;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getChildren() {
        return children;
    }

    public void setChildren(T children) {
        this.children = children;
    }
}
