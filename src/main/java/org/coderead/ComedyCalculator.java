package org.coderead;

import org.coderead.model.Performance;

public class ComedyCalculator implements ICalculator{
    public ComedyCalculator() {
    }

    @Override
    public int getAmount(Performance performance) {
        int thisAmount;
        thisAmount = 30000;
        if (performance.getAudience() > 20) {
            thisAmount += 10000 + 500 * (performance.getAudience() - 20);
        }
        thisAmount += 300 * performance.getAudience();
        return thisAmount;
    }

    @Override
    public double getVolumeCredits(Performance performance) {
        double volumeCredits_v2;
        int max = Math.max(performance.getAudience() - 30, 0);
        double floor = Math.floor(performance.getAudience() / 5);
        volumeCredits_v2 = max + floor;
        return volumeCredits_v2;
    }
}