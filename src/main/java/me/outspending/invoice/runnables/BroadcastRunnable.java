package me.outspending.invoice.runnables;

import me.outspending.invoice.managers.AnnouncementManager;

public class BroadcastRunnable implements Runnable {

    @Override
    public void run() {
        AnnouncementManager.broadcastAnnouncement();
    }

}
