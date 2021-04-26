import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingSetter
{
    //ustalam możliwe godziny do spotkania
    public static List<Hours> setMeetings(Calendar person1Calendar, Calendar person2Calendar, int minutesOfMeeting){
        List<Hours> togetherFreeTime = getTogetherFreeTime(person1Calendar, person2Calendar);
        List<Hours> meetingTimes = new ArrayList<>();
        int i = 0;

        //iteruję po wspólnym czasie wolnym
        while (i < togetherFreeTime.size()){
            Hours freeTime = togetherFreeTime.get(i);
            LocalTime temp = freeTime.getStart();

            //dopóki czas między początkiem możliwego spotkania a jego końcem jest większy od długości spotkania, dodaje spotkanie do listy
            while (Duration.between(temp, freeTime.getEnd()).toMinutes() >= minutesOfMeeting){
                LocalTime endOfMeeting = temp.plusMinutes(minutesOfMeeting);
                meetingTimes.add(new Hours(temp, endOfMeeting));
                temp = endOfMeeting;
            }
            i++;
        }
        if(meetingTimes.isEmpty()) System.out.println("No opportunity to meeting!");
        else System.out.println("Opportunities to meeting: " + meetingTimes);
        return meetingTimes;
    }

    //wyznaczam wspólny czas wolny dla dwóch osób(kalendarzy)
    private static List<Hours> getTogetherFreeTime(Calendar person1Calendar, Calendar person2Calendar){
        List<Hours> freeTerms1 = person1Calendar.getFreeTerms();
        List<Hours> freeTerms2 = person2Calendar.getFreeTerms();

        List<Hours> togetherFreeTime = new ArrayList<>();

        //iteruje po pierwszym kalenarzu
        for (int i = 0; i < freeTerms1.size(); i++) {
            LocalTime startFreeTime = freeTerms1.get(i).getStart();
            LocalTime endFreeTime = freeTerms1.get(i).getEnd();

            //iteruje po drugim kalendarzu
            for (int j = 0; j < freeTerms2.size(); j++)
            {
                LocalTime startFreeTime2 = freeTerms2.get(j).getStart();
                LocalTime endFreeTime2 = freeTerms2.get(j).getEnd();

                //wyznaczam koniec wolnego czasu (dla osoby ktorej wcześniej się on kończy)
                LocalTime endTogetherFreeTime = endFreeTime.compareTo(endFreeTime2) > 0 ? endFreeTime2 : endFreeTime;

                //sprawdzam czy wolne czasy maja "część wspólną"
                if(startFreeTime.compareTo(startFreeTime2) >= 0 && startFreeTime.compareTo(endFreeTime2) < 0 ){
                    togetherFreeTime.add(new Hours(startFreeTime, endTogetherFreeTime));
                }
                else if(startFreeTime.compareTo(startFreeTime2) <= 0 && startFreeTime2.compareTo(endFreeTime) < 0 ){
                    togetherFreeTime.add(new Hours(startFreeTime2, endTogetherFreeTime));
                }
            }
        }
        return togetherFreeTime;
    }
}
