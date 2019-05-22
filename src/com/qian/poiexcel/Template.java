package com.qian.poiexcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Template {
    private List<String> headers = new ArrayList<String>();
    private List<String> columns = new ArrayList<String>();

    public Template() {
        // empty
    }

    public void addHeaders(String header) {
        headers.add(header);
    }


    public void addColumns(String column) {
        columns.add(column);
    }

    public List<String> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    public List<String> getColumns() {
        return Collections.unmodifiableList(columns);
    }

}
