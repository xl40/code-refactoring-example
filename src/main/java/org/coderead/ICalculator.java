package org.coderead;

import org.coderead.model.Performance;

public interface ICalculator {
    int getAmount(Performance performance);

    double getVolumeCredits(Performance performance);
}
