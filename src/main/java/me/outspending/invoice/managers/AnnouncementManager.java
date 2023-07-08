package me.outspending.invoice.managers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;

public final class AnnouncementManager implements InvoiceManager {

    private static final Deque<AutoAnnouncement> announcements = new ArrayDeque<>();

    public static void registerAll(Supplier<List<AutoAnnouncement>> supplier) {
        announcements.addAll(supplier.get());
    }

    public static AutoAnnouncement createAutoAnnouncement(String firstLine, String secondLine) {
        AutoAnnouncement announcement = new AutoAnnouncement(firstLine, secondLine);
        announcements.addLast(announcement);
        return announcement;
    }

    private static AutoAnnouncement shuffleAnnouncements() {
        AutoAnnouncement announcement = announcements.getFirst();
        announcements.removeFirst();
        announcements.addLast(announcement);
        return announcement;
    }

    public static void broadcastAnnouncement() {
        shuffleAnnouncements().broadcast();
    }
}
