package io.pivotal.pal.tracker;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;
    private HashMap<Long, TimeEntry> timeEntryMap = new HashMap();

    public InMemoryTimeEntryRepository(long id, long projectId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;
    }

    public InMemoryTimeEntryRepository(){

    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = timeEntryMap.size() + 1L;
        TimeEntry newTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        this.timeEntryMap.put(id, newTimeEntry);

        return newTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = new TimeEntry(id, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntryMap.remove(id);
    }
}
