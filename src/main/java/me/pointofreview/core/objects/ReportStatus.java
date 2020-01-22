package me.pointofreview.core.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ReportStatus {
    int spam = 0;
    int badLanguage = 0;
    int misleading = 0;
    Long banExpire = System.currentTimeMillis();

    public boolean addReport(String reportType){
        if (isBanned())
            return true;
        if (!(reportType.equals("spam") || reportType.equals("badLanguage") || reportType.equals("misleading")))
            return false;
        if (reportType.equals("spam"))
            spam++;
        if (reportType.equals("badLanguage"))
            badLanguage++;
        if (reportType.equals("misleading"))
            misleading++;
        int totalReports = spam + badLanguage + misleading;

        if (totalReports > 0 && totalReports % 3 == 0) { // each 3 reports
            int times = totalReports / 3; // number of bans
            int day = 1000 * 60 * 60 * 24;
            int length = (int) Math.pow(2, times); // first ban is 2 days, after that 4, 8...
            banExpire = System.currentTimeMillis() + day * length; // 1 minute ban
        }

        return true;
    }

    public boolean isBanned() {
        return System.currentTimeMillis() < banExpire;
    }
}
