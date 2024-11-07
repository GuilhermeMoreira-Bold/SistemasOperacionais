#ifndef STATE_H
#define STATE_H

class Process;

class State{
    public:
    Process* process;
    void setProcess(Process* process){
        this->process = process;
    }
    virtual void consume() = 0;
    virtual std::string toString() {
        return "Generic";
        };

};

#endif