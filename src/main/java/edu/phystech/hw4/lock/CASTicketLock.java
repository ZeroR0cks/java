package edu.phystech.hw4.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ticket lock using compare-and-set atomic operations.
 */
public class CASTicketLock {
    private final AtomicInteger nextTicket = new AtomicInteger(0);
    private final AtomicInteger currentTicket = new AtomicInteger(0);

    public void lock() {
        int myTicket = nextTicket.getAndIncrement();

        while (currentTicket.get() != myTicket) {

            Thread.onSpinWait();
        }
    }

    public void unlock() {
        currentTicket.incrementAndGet();
    }
}
