package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private Invoice invoice;
    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getStringBuilder(String.format("Statement for %s", invoice.getCustomer())));
        stringBuilder.append(String.format("Amount owed is %s\n", formatUSD(getTotalAmount())));
        stringBuilder.append(String.format("You earned %s credits\n", getVolumeCredits()));
        return stringBuilder.toString();
    }

    private StringBuilder getStringBuilder(String result) {
        StringBuilder stringBuilder_v2 = new StringBuilder(result);
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            stringBuilder_v2.append(String.format(" %s: %s (%d seats)\n", play.getName(), formatUSD(getThisAmount(performance, play)), performance.getAudience()));
        }
        return stringBuilder_v2;
    }

    private int getTotalAmount() {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            totalAmount += getThisAmount(performance, play);
        }
        return totalAmount;
    }

    private int getVolumeCredits() {
        int volumeCredits = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            volumeCredits += getVolumeCredits(performance, play);
        }
        return volumeCredits;
    }

    private String formatUSD(int amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount / 100);
    }

    private int getThisAmount(Performance performance, Play play) {
        int thisAmount = 0;
        switch (play.getType()) {
            case "tragedy":
                thisAmount = getTragedyAmount(performance);
                break;
            case "comedy":
                thisAmount = getComedyAmount(performance);
                break;
            default:
                throw new RuntimeException("unknown type:" + play.getType());
        }
        return thisAmount;
    }

    private int getComedyAmount(Performance performance) {
        int thisAmount;
        thisAmount = 30000;
        if (performance.getAudience() > 20) {
            thisAmount += 10000 + 500 * (performance.getAudience() - 20);
        }
        thisAmount += 300 * performance.getAudience();
        return thisAmount;
    }

    private int getTragedyAmount(Performance performance) {
        int thisAmount;
        thisAmount = 40000;
        if (performance.getAudience() > 30) {
            thisAmount += 1000 * (performance.getAudience() - 30);
        }
        return thisAmount;
    }

    private double getVolumeCredits(Performance performance, Play play) {
        double volumeCredits_v2 = 0;
        if ("tragedy".equals(play.getType())) {
            volumeCredits_v2 = getTragedyVolumeCredits(performance);
        }
        if ("comedy".equals(play.getType())) {
            volumeCredits_v2 = getComedyVolumeCredits(performance);
        }
        return volumeCredits_v2;
    }

    private double getComedyVolumeCredits(Performance performance) {
        double volumeCredits_v2;
        int max = Math.max(performance.getAudience() - 30, 0);
        double floor = Math.floor(performance.getAudience() / 5);
        volumeCredits_v2 = max + floor;
        return volumeCredits_v2;
    }

    private int getTragedyVolumeCredits(Performance performance) {
        return Math.max(performance.getAudience() - 30, 0);
    }
}
