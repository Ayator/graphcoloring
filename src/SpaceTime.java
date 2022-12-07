import java.util.concurrent.atomic.AtomicBoolean;

public class SpaceTime extends Thread{
    private final long intervalTime = 1L;
    private long initialTime;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final long megabyte = 1024L * 1024L;
    private final long gigabyte = megabyte * 1024L;
    private long maxMemoryUsed;
    
    private Runtime runtime = Runtime.getRuntime();

    public void printFinalTime(){
        System.out.println("Runtime: " + (System.currentTimeMillis() - initialTime) + "ms");
    }

    public void printMaxMemoryUsed(){
        System.out.println("Max memory: " + bytesToMb(maxMemoryUsed) + "mb");
    }

    private long bytesToMb(long kb){
        return kb / megabyte;
    }

    private long bytesToGb(long kb){
        return kb / gigabyte;
    }

    @Override
    public void run() {
        running.set(true);
        runtime.gc();
        maxMemoryUsed = 0;
        initialTime = System.currentTimeMillis();
        while(running.get()){
            try {
                Thread.sleep(intervalTime);
                long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
                if(memoryUsed > maxMemoryUsed)
                    maxMemoryUsed = memoryUsed;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public void stopThread() {
        running.set(false);
    }

    public void printData(boolean printTime, boolean printMaxMemory) {
        if(printTime)
            printFinalTime();
        if(printMaxMemory)
            printMaxMemoryUsed();
    }
    
}
