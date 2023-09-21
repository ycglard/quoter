package com.quoter.quoter.dto;

public class RangeDto {
    private int startIndex;
    private int endIndex;

    public RangeDto(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public RangeDto(){
        this.startIndex = 0;
        this.endIndex = 1;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
