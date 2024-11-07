#ifndef READY_H
#define READY_H
#include "State.h"
#include <iostream>

class Ready : public State{
    public:

    void consume() override{};
      std::string toString() override{
        return "Ready";
    }
};

#endif