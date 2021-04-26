import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class MeetingSetterTest
{
    @Test
    public void shouldReturnCorrectAmountOfMeeting() {
        //given
        Calendar calendar1 = calendar1();
        Calendar calendar2 = calendar2();
        //when
        List<Hours> actualMeetings = MeetingSetter.setMeetings(calendar1, calendar2, 30);

        //then
        Assertions.assertEquals(4,actualMeetings.size());
    }
    @Test
    public void shouldReturnEmptyList() {
        //given
        Calendar calendar1 = calendar1();
        Calendar calendar2 = calendar3();
        //when
        List<Hours> actualMeetings = MeetingSetter.setMeetings(calendar1, calendar2, 30);

        //then
        Assertions.assertEquals(Collections.emptyList(), actualMeetings);

    }
    @Test
    public void shouldReturnCorrectFirstOpportunityToMeeting() {
        //given
        Calendar calendar1 = calendar2();
        Calendar calendar2 = calendar4();
        //when
        List<Hours> actualMeetings = MeetingSetter.setMeetings(calendar1, calendar2, 15);
        //then
        Assertions.assertEquals(new Hours(LocalTime.of(9, 0), LocalTime.of(9, 15)),actualMeetings.get(0));
    }
    @Test
    public void shouldReturnCorrectAmountOfMeeting2() {
        //given
        Calendar calendar1 = calendar1();
        Calendar calendar2 = calendar4();
        //when
        List<Hours> actualMeetings = MeetingSetter.setMeetings(calendar1, calendar2, 20);

        //then
        Assertions.assertEquals(1,actualMeetings.size());
    }



    private Calendar calendar1(){
        Hours hours = new Hours(LocalTime.of(8, 0), LocalTime.of(17, 0));

        Hours meeting1 = new Hours(LocalTime.of(8, 0), LocalTime.of(9, 30));
        Hours meeting2 = new Hours(LocalTime.of(10, 0), LocalTime.of(12, 0));
        Hours meeting3 = new Hours(LocalTime.of(13, 0), LocalTime.of(15, 30));
        return createCalendar(hours, meeting1, meeting2, meeting3);
    }
    private Calendar calendar2(){
        Hours hours = new Hours(LocalTime.of(9, 0), LocalTime.of(17, 0));

        Hours meeting1 = new Hours(LocalTime.of(10, 0), LocalTime.of(11, 30));
        Hours meeting2 = new Hours(LocalTime.of(13, 0), LocalTime.of(14, 0));
        Hours meeting3 = new Hours(LocalTime.of(15, 0), LocalTime.of(16, 30));
        return createCalendar(hours, meeting1, meeting2, meeting3);
    }
    private Calendar calendar3(){
        Hours hours = new Hours(LocalTime.of(9, 0), LocalTime.of(13, 0));

        Hours meeting1 = new Hours(LocalTime.of(9, 0), LocalTime.of(11, 30));
        Hours meeting2 = new Hours(LocalTime.of(11, 30), LocalTime.of(13, 0));

        return createCalendar(hours, meeting1, meeting2);
    }
    private Calendar calendar4(){
        Hours hours = new Hours(LocalTime.of(9, 0), LocalTime.of(16, 0));

        Hours meeting1 = new Hours(LocalTime.of(9, 30), LocalTime.of(11, 0));
        Hours meeting2 = new Hours(LocalTime.of(11, 45), LocalTime.of(12, 45));
        Hours meeting3 = new Hours(LocalTime.of(13, 0), LocalTime.of(15, 30));
        return createCalendar(hours, meeting1, meeting2, meeting3);
    }

    private Calendar createCalendar(Hours workingHours, Hours ... plannedMeetings){
        return new Calendar(workingHours, Arrays.asList(plannedMeetings));
    }
}
