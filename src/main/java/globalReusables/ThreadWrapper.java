package globalReusables;

public class ThreadWrapper {
    private BaseRun baseRun;

    public ThreadWrapper(BaseRun baseRun) {
        this.baseRun = baseRun;
    }

    public void go() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                baseRun.runThread();
            }
        });
        thread.start();
        try {
            if (baseRun.shouldWait()) thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


