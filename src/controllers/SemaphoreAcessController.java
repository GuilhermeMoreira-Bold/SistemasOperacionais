package controllers;

import java.util.concurrent.Semaphore;

public class SemaphoreAcessController implements AcessController {

    private final Semaphore semaphore = new Semaphore(1);

    @Override
    public void requestRead() {
        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            semaphore.release();
        }
    }


    @Override
    public void requestWrite() {
        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            releaseWrite();
        }
    }

    @Override
    public void releaseWrite() {
        semaphore.release();
    }

    @Override
    public void releaseRead() {
        semaphore.release();
    }

}
