package com.queuesystem.queuesystem.util;

/**
 * Created by IntelliJ IDEA.
 *
 * @author
 * description:
 * @since 2021/3/14 5:59pm
 * version: 1.0.0
 */
public class IdWorkerUtil {

        private long workerId;
        private long dataCenterId;
        private long sequence;

        public IdWorkerUtil(long workerId, long dataCenterId, long sequence){
            // sanity check for workerId
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
            }
            if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
                throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0",maxDataCenterId));
            }
            System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerId %d",
                    timestampLeftShift, dataCenterIdBits, workerIdBits, sequenceBits, workerId);

            this.workerId = workerId;
            this.dataCenterId = dataCenterId;
            this.sequence = sequence;
        }

        private long twEpoch = 1288834974657L;

        private long workerIdBits = 5L;
        private long dataCenterIdBits = 5L;
        private long maxWorkerId = -1L ^ (-1L << workerIdBits);
        private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
        private long sequenceBits = 12L;

        private long workerIdShift = sequenceBits;
        private long dataCenterIdShift = sequenceBits + workerIdBits;
        private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
        private long sequenceMask = -1L ^ (-1L << sequenceBits);

        private long lastTimestamp = -1L;

        public long getWorkerId(){
            return workerId;
        }

        public long getDataCenterId(){
            return dataCenterId;
        }

        public long getTimestamp(){
            return System.currentTimeMillis();
        }

        public synchronized long nextId() {
            long timestamp = timeGen();

            if (timestamp < lastTimestamp) {
                System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        lastTimestamp - timestamp));
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = timestamp;
            return ((timestamp - twEpoch) << timestampLeftShift) |
                    (dataCenterId << dataCenterIdShift) |
                    (workerId << workerIdShift) |
                    sequence;
        }

        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        private long timeGen(){
            return System.currentTimeMillis();
        }

        //---------------test---------------
        public static void main(String[] args) {
            IdWorkerUtil worker = new IdWorkerUtil(1,1,1);
            for (int i = 0; i < 30; i++) {
                System.out.println(worker.nextId());
            }
        }

}
