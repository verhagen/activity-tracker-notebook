package nl.verhagen.activitylogger.command.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ActivityRegisteryTest {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	@Test
	public void testName() throws Exception {
		ActivityRegistery activityReg = createActivityRegistery(
				new String[] {
						  "2021.10.01 08:00 | project.mazes-for-programmers | start"
						, "2021.10.01 09:00 | book.mazes-for-programmers.ch1 | start"
						, "2021.10.01 10:30 | book.mazes-for-programmers.ch1 | stop"
						, "2021.10.01 10:45 | project.mazes-for-programmers | start"
						, "2021.10.01 12:00 | lunch | start"
				});
		List<ActivityEventImpl> result = activityReg.search("kermit-the-frog");
		
		assertEquals(5, result.size());

		result = activityReg.search("sam-the-eagle");
		assertEquals(0, result.size());
		
		activityReg.accept(new ActivityConvertor());
//		new ActivityEntryImpl.Builder()
//			.identity(null)
//		activityReg
	}

	private ActivityRegistery createActivityRegistery(String[] content) {
		ActivityTrackerEventConfiguration cfg = new ActivityTrackerEventConfiguration("kermit-the-frog");
		ActivityRegistery activityReg = new ActivityRegistery();
		List<ActivityEventImpl> activities = Arrays.asList(content).stream()
				.map(ar -> ar.split("\\|"))
				.map(x -> new ActivityEventImpl.Builder(cfg)
						.timestamp(LocalDateTime.parse(x[0].trim(), formatter))
						.identity(x[1].split("\\."))
						.command(x[2].trim())
						.create())
				.collect(Collectors.toList());
				
		activities.stream().forEach(a -> activityReg.add(a));
//			String[] array = ;  ew ActivityEntryImpl.Builder().identity(x[0].split("\\.")).command(x[1]).create() 
//			return new ActivityEntryImpl.Builder().identity(array[0].split("\\.")).command(array[1]).create(); });

			//		Arrays.asList(content).stream().map(ar -> {
//			String[] array = ar.split("\\|"); 
//			return new ActivityEntryImpl.Builder().identity(array[0].split("\\.")).command(array[1]).create(); });
		return activityReg;
	}
}
