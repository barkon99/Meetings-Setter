import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calendar
{
    private Hours workingHours;
    private List<Hours> plannedMeetings;

    public Calendar(Hours workingHours, List<Hours> plannedMeetings) {
        if(checkHours(workingHours, plannedMeetings)){ //weryfikacja godzin
            this.workingHours = workingHours;
            this.plannedMeetings = plannedMeetings;
        }
        else{
            System.out.println("Bad data! Check your working hours and planned meetings!");
        }

    }
    //sprawdzenie czy godziny spotkań zawieraja się w godzinach pracy
    private boolean checkHours(Hours workingHours, List<Hours> plannedMeetings){
        return plannedMeetings.stream()
                .allMatch(meeting -> meeting.getStart().compareTo(workingHours.getStart()) >= 0 &&
                          meeting.getEnd().compareTo(workingHours.getEnd()) <= 0);

    }

    //wyznaczam wolne terminy dla kalandarza
    public List<Hours> getFreeTerms(){
        List<Hours> freeTerms = new ArrayList<>();

        for (int i = 0; i < plannedMeetings.size(); i++) {
            LocalTime endOfMeeting = plannedMeetings.get(i).getEnd();
            LocalTime startMeeting = plannedMeetings.get(i).getStart();

            //wyznaczam czas miedzy rozpoczeciem pracy a 1 spotkaniem
            if(i == 0 && startMeeting.compareTo(workingHours.getStart()) > 0){
                freeTerms.add(new Hours(workingHours.getStart(), startMeeting));
            }
            //wyznaczam czas między końcem spotkania a rozpoczęciem następnego spotkania
            if(i < plannedMeetings.size() - 1){
                LocalTime startNextMeeting = plannedMeetings.get(i + 1).getStart();
                if(endOfMeeting.compareTo(startNextMeeting) < 0)
                    freeTerms.add(new Hours(endOfMeeting, startNextMeeting));
            }
            //wyznaczam czas między spotkaniem ostatnim a końcem pracy
            else {
                LocalTime endOfWork = workingHours.getEnd();
                if(endOfMeeting.compareTo(endOfWork) < 0)
                    freeTerms.add(new Hours(endOfMeeting, endOfWork));
            }
        }
        return freeTerms;
    }
}
