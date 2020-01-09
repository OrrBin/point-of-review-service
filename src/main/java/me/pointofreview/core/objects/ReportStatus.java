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
        if (!(reportType.equals("spam") || reportType.equals("badLanguage") || reportType.equals("misleading")))
            return false;
        if (reportType.equals("spam"))
            spam++;
        if (reportType.equals("badLanguage"))
            badLanguage++;
        if (reportType.equals("misleading"))
            misleading++;
        if (spam + badLanguage + misleading >= 3)
            banExpire = System.currentTimeMillis() + 1000 * 60; // 1 minute ban

        return true;
    }

    public boolean isBanned() {
        return System.currentTimeMillis() < banExpire;
    }
}
