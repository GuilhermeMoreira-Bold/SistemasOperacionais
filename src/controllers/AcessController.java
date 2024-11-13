package controllers;

public interface AcessController {
    void requestRead();
    void releaseRead();
    void requestWrite();
    void releaseWrite();
}
