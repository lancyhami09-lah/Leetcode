import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int curr = 1;

    private Semaphore num = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void next() {
        if (curr > n) {
            fizz.release();
            buzz.release();
            fizzbuzz.release();
            num.release();
            return;
        }

        if (curr % 15 == 0)
            fizzbuzz.release();
        else if (curr % 3 == 0)
            fizz.release();
        else if (curr % 5 == 0)
            buzz.release();
        else
            num.release();
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizz.acquire();
            if (curr > n) return;
            printFizz.run();
            curr++;
            next();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzz.acquire();
            if (curr > n) return;
            printBuzz.run();
            curr++;
            next();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzbuzz.acquire();
            if (curr > n) return;
            printFizzBuzz.run();
            curr++;
            next();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            num.acquire();
            if (curr > n) {
                next();
                return;
            }

            if (curr % 3 != 0 && curr % 5 != 0) {
                printNumber.accept(curr);
                curr++;
            }

            next();
        }
    }
}
