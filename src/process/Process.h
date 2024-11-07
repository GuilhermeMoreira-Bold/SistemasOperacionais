
#ifndef PROCESS_H
#define PROCESS_H

#include "../states/State.h"

class Process{
    int ciclesToBeReady;
   
    std::string pid;
    
    public:
    int tp;
    int cp;
    int nes;
    int n_cpu;
    bool isBlock = false;
    State* state; //EP
    bool isFirstEs = true;

    bool isFinish(){
        return ciclesToBeReady == tp;
    }

    Process(int ciclesToBeReady, State* state, std::string pid) : pid(pid), ciclesToBeReady(ciclesToBeReady){
        this->state = state;
        state->setProcess(this);
    };

    void handle(){
        state->consume();
    };

    inline std::string getCurrentInfos(){
        return "PID:" + pid  + "\nTP:" +  std::to_string(tp) + "\nCP:" + std::to_string((tp  + 1)) + "\nEP:" + state->toString() + "\nNes:" + std::to_string(nes) + "\nN_CPU:" + std::to_string(n_cpu) + ".";
    };

    inline void changeState(State* s){
        if(state!= nullptr) std::cout << "{" << pid << "} changing state: [" << state->toString() << "] to: [" << s->toString() << "]" << std::endl;
        this->state = s;
        s->setProcess(this);
    };
    
};

#endif