package org.coderead;

import org.coderead.model.Performance;
import org.coderead.model.Play;

public interface ICalculator {

    static ICalculator of(Play play) {
        ICalculator iCalculator = null;
        if ("tragedy".equals(play.getType())) {
            iCalculator = new TragedyCalculator();
        }
        if ("comedy".equals(play.getType())) {
            iCalculator = new ComedyCalculator();
        }
        return iCalculator;
    }

    int getAmount(Performance performance);

    double getVolumeCredits(Performance performance);
}
