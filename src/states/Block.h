#ifndef BLOCK_H
#define BLOCK_H
#include "State.h"
#include <iostream>
#include "../rand/Random.h"
#include "Ready.h"


class Block :public State{
    public:
    void consume() override{
        this->process->nes++;
        this->process->isBlock = true;
        if(this->process->isFirstEs){
            this->process->isFirstEs = false;
        }else{
            if(Random::generateNumber() <= 30){
                this->process->isBlock = false;
                this->process->changeState(new Ready());
                delete this;
            }
        }
    };

    std::string toString() override{
        return "Block";
    }
};

#endif