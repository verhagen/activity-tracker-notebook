package nl.verhagen.atnb.command;

import nl.verhagen.atnb.command.domain.ActivityTrackerEvent;
import nl.verhagen.atnb.command.domain.Listener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTestRegression {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    private AppRunner appRunner;

    @BeforeEach
    public void setUp() {
        System.setProperty("user.home", "src/test/data/user-home-regression-test");
        appRunner = new AppRunner(new AppRunnerConfig());
//        appRunner.addListener(new Listener<ActivityTrackerEvent>() {
//            @Override
//            public void update(ActivityTrackerEvent event) {
//                assertEquals("2021.12.07 14:35", event.getTimeStamp().format(formatter));
//                assertEquals("test", event.getIdentifier());
//                assertEquals("start", event.getCommand());
//            }
//        });
    }

    @Test
    public void start() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021.12.07 13:35", formatter);
        // TODO [2023.04.23 TV] Throws exception, because issue task is not yet implemented
//        appRunner.execute(timeStamp, "issue.123", "start");
    }

}
