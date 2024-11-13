package controllers;

import message.Message;
import pass.MessagePass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexAcessController implements AcessController {
    private final Lock lock = new ReentrantLock();

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
}
