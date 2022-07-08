package org.coderead;

import org.coderead.model.Performance;

public class TragedyCalculator implements ICalculator {
    public TragedyCalculator() {
    }

    @Override
    public int getAmount(Performance performance) {
        int thisAmount;
        thisAmount = 40000;
        if (performance.getAudience() > 30) {
            thisAmount += 1000 * (performance.getAudience() - 30);
        }
        return thisAmount;
    }

    @Override
    public double getVolumeCredits(Performance performance) {
        return Math.max(performance.getAudience() - 30, 0);
    }
}