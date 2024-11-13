package controllers;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarrierAcessController implements AcessController {
    private final Lock lock = new ReentrantLock();
    CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        System.out.println("As threads alcançaram a barreira e vão iniciar uma nova operação.\n");
    });

    public BarrierAcessController() {

    }

    @Override
    public void requestRead() {

    }

    @Override
    public void releaseRead() {

    }

    @Override
    public void requestWrite() {
        try{
            lock.lock();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            releaseWrite();
        }
    }

    @Override
    public void releaseWrite() {
        lock.unlock();
    }

    public void waitBarrier() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}