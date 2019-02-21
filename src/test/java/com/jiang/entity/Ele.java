package com.jiang.entity;

import java.util.Objects;

/**
 * @description:
 * @author: jiangxy
 * @create: 2019-02-21 20:02
 */
public class Ele {

    Integer id;
    Integer i ;

    public Ele(Integer id, Integer i) {
        this.id = id;
        this.i = i;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ele ele = (Ele) o;
        return Objects.equals(id, ele.id) &&
                Objects.equals(i, ele.i);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, i);
    }
}

