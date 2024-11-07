#ifndef RANDOM_H
#define RANDOM_H

#include <random>

struct Random
{
    static int generateNumber(){
       std::random_device rd;
       std::mt19937 gen(rd());
       std::uniform_int_distribution<> dist(1, 100); 
        return dist(gen);
    }
};

#endif
