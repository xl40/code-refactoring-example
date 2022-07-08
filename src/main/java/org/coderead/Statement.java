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

    private final ICalculator tragedyCalculator = new TragedyCalculator();
    private final ICalculator comedyCalculator = new ComedyCalculator();
    private Invoice invoice;
    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStringBuilder(String.format("Statement for %s", invoice.getCustomer()), plays));
        stringBuilder.append(String.format("Amount owed is %s\n", formatUSD(invoice.getTotalAmount(plays, this))));
        stringBuilder.append(String.format("You earned %s credits\n", invoice.getVolumeCredits(plays, this)));
        return stringBuilder.toString();
    }

    private StringBuilder getStringBuilder(String result, Map<String, Play> plays) {
        StringBuilder stringBuilder_v2 = new StringBuilder(result);
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            stringBuilder_v2.append(String.format(" %s: %s (%d seats)\n", play.getName(), formatUSD(getThisAmount(performance, play)), performance.getAudience()));
        }
        return stringBuilder_v2;
    }

    private String formatUSD(int amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount / 100);
    }

    public int getThisAmount(Performance performance, Play play) {
        return ICalculator.getiCalculator(comedyCalculator, tragedyCalculator, play).getAmount(performance);
    }

    public double getVolumeCredits(Performance performance, Play play) {
        return ICalculator.getiCalculator(comedyCalculator, tragedyCalculator, play).getVolumeCredits(performance);
    }

}
