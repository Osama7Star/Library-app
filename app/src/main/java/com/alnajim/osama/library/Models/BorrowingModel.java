package com.alnajim.osama.library.Models;

/**
 * Created by Osama Alnajm on 28-Jan-20.
 */
public class BorrowingModel
{
    private String startDate ;
    private String endDate ;

    public BorrowingModel(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
