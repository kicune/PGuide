package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Embed;

import  java.util.Formatter;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 27.06.13
 * Time: 23:37
 */
@Embed
public class OpeningHours {
    private DayOpeningHours[] dayOpeningHours;

    public OpeningHours() {
        dayOpeningHours = new DayOpeningHours[7];
    }

    public String toHTMLTable() {
        //TODO: closed
        //TODO: midday break
        StringBuilder sb = new StringBuilder();

        Formatter fmt = new Formatter(sb);
        fmt.format("<table><tr><td>Mo</td><td>%1$s - %2$s</td></tr>" +
                "<tr><td>Tu</td><td>%3$s - %4$s</td></tr>" +
                "<tr><td>Wed</td><td>%5$s - %6$s</td></tr>" +
                "<tr><td>Thu</td><td>%7$s - %8$s</td></tr>" +
                "<tr><td>Fri</td><td>%9$s - %10$s</td></tr>" +
                "<tr><td>Sa</td><td>%11$s - %12$s</td></tr>" +
                "<tr><td>Sun</td><td>%13$s - %14$s</td></tr></table>",
                dayOpeningHours[0].getMorning(), dayOpeningHours[0].getAfternoon(),
                dayOpeningHours[1].getMorning(), dayOpeningHours[1].getAfternoon(),
                dayOpeningHours[2].getMorning(), dayOpeningHours[2].getAfternoon(),
                dayOpeningHours[3].getMorning(), dayOpeningHours[3].getAfternoon(),
                dayOpeningHours[4].getMorning(), dayOpeningHours[4].getAfternoon(),
                dayOpeningHours[5].getMorning(),  dayOpeningHours[5].getAfternoon(),
                dayOpeningHours[6].getMorning(),  dayOpeningHours[6].getAfternoon());

        return fmt.toString();
    }

    public void setOpeningHours(int dayOfWeek, String morning, String afternoon) {
        if(dayOfWeek>6) return;
        dayOpeningHours[dayOfWeek].setMorning(morning);
        dayOpeningHours[dayOfWeek].setAfternoon(afternoon);
    }


    private class DayOpeningHours {
        private String morning;
        private String afternoon;

        public String getAfternoon() {
            return afternoon;
        }

        public void setAfternoon(String afternoon) {
            this.afternoon = afternoon;
        }

        public String getMorning() {
            return morning;
        }

        public void setMorning(String morning) {
            this.morning = morning;
        }


    }
}