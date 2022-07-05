package org.coderead;

import org.coderead.model.Performance;

public abstract class IPerformanceCalculator {

    abstract double getVolumeCredits(Performance performance);

    abstract double getAmount(Performance performance);

}
