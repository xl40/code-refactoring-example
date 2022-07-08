package org.coderead.model;

import org.coderead.ICalculator;
import org.coderead.Statement;

import java.util.List;
import java.util.Map;

/**
 * 发票
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Invoice {

    private String customer;

    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public int getTotalAmount(Map<String, Play> plays, Statement statement) {
        int totalAmount = 0;
        for (Performance performance : getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            totalAmount += performance.getThisAmount(play);
        }
        return totalAmount;
    }

    public int getVolumeCredits(Map<String, Play> plays, Statement statement) {
        int volumeCredits = 0;
        for (Performance performance : getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            volumeCredits += ICalculator.getiCalculator(play).getVolumeCredits(performance);
        }
        return volumeCredits;
    }
}
