package com.dangdang.ddframe.rdb.sharding.sequence;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * From: https://github.com/twitter/snowflake
 * An object that generates IDs.
 * This is broken into a separate class in case
 * we ever want to support multiple worker threads
 * per process
 *
 * @author Linpn
 */
public class SnowflakeTest {

    static class SnowflakeThread implements Runnable {
        private Set<Long> set;
        private Snowflake snowflake;

        public SnowflakeThread(Set<Long> set, Snowflake snowflake) {
            this.set = set;
            this.snowflake = snowflake;
        }

        @Override
        public void run() {
            while (true) {
                long id = snowflake.nextId();
                if (!set.add(id)) {
                    System.out.println("duplicate:" + id);
                }
            }
        }
    }

    @Test
    public void testNextId() throws Exception {
        Set<Long> set = new HashSet<>();
        final Snowflake snowflake1 = new Snowflake(0, 0);
        final Snowflake snowflake2 = new Snowflake(1, 0);
        Thread t1 = new Thread(new SnowflakeThread(set, snowflake1));
        Thread t2 = new Thread(new SnowflakeThread(set, snowflake2));
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
