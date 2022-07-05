package org.coderead;

import org.coderead.model.Performance;

public abstract class AbstractPerformanceCalculator {

    static AbstractPerformanceCalculator getPerformanceCalculator(String type) {
        if ("tragedy".equals(type)) {
            return new TragedyCalculator();
        }

        if ("comedy".equals(type)) {
            return new ComedyCalculator();
        }
        return null;
    }

    abstract double getVolumeCredits(Performance performance);

    abstract double getAmount(Performance performance);

}
