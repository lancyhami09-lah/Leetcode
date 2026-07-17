class Foo {
    boolean x;
    boolean y;

    public Foo() {
     x = false;
     y = false;   
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        x = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        while(!x){
            wait();
        }
        printSecond.run();
        y = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
        while(!y){
            wait();
        }
        printThird.run();
    }
}
