#ifndef EXECUTING_H
#define EXECUTING_H

#include "State.h"
#include "../rand/Random.h"
#include <iostream>
#include "Block.h"

class Executing : public State{
    public:
    void consume() override{
        this->process->tp ++;
        this->process->n_cpu++;
        if(Random::generateNumber() == 1){
             this->process->changeState( new Block());
                delete this;
        }
    };

      std::string toString() override{
        return "Executing";
    }
};

#endif