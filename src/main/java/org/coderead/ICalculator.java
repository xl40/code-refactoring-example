package org.coderead;

import org.coderead.model.Performance;
import org.coderead.model.Play;

public interface ICalculator {

    static ICalculator getiCalculator(ICalculator comedyCalculator, ICalculator tragedyCalculator, Play play) {
        ICalculator iCalculator = null;
        if ("tragedy".equals(play.getType())) {
            iCalculator = tragedyCalculator;
        }
        if ("comedy".equals(play.getType())) {
            iCalculator = comedyCalculator;
        }
        return iCalculator;
    }

    int getAmount(Performance performance);

    double getVolumeCredits(Performance performance);
}
