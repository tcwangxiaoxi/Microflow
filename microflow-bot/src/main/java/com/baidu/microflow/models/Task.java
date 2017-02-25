package com.baidu.microflow.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Getter
@Setter
@ToString
public abstract class Task implements ITask {
    protected Long id = -1L;
    protected String name;
    protected Integer retries = 1;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, Integer retries) {
        this.name = name;
        this.retries = retries;
    }

    public int getRetries() {
        return retries;
    }

    /**
     * EqualsTo
     *
     * @param obj
     * @return boolean
     */
    public boolean equalsTo(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        return name.equals(((Task) obj).getName());
    }
}
