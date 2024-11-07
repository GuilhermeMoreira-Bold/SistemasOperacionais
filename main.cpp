#include <iostream>
#include "src/process/Process.h"
#include "src/states/State.h"
#include "src/states/Ready.h"
#include "src/states/Block.h"
#include "src/states/Executing.h"
#include <algorithm>
#include <fstream>

#define QUANTUM 1000

std::ofstream processTable("ProcessTable.txt");


inline void pTable(const std::string& processInfo){
  processTable << "\n==========================================" << "\n" << processInfo;
}

void os(){
  Process* p1 = new Process(10000, new Ready(), "p1");
  Process* p2 = new Process(5000, new Ready(), "p2");
  Process* p3 = new Process(7000, new Ready(), "p3");
  Process* p4 = new Process(3000, new Ready(), "p4");
  Process* p5 = new Process(3000, new Ready(), "p5");
  Process* p6 = new Process(8000, new Ready(), "p6");
  Process* p7 = new Process(2000, new Ready(), "p7");
  Process* p8 = new Process(5000, new Ready(), "p8");
  Process* p9 = new Process(4000, new Ready(), "p9");
  Process* p10 = new Process(10000, new Ready(), "p10");


  std::vector<Process*> processList = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};

  while(true){
      for(const auto& currentProcess : processList){
        if(!currentProcess->isBlock){
          currentProcess->changeState(new Executing);
          pTable(currentProcess->getCurrentInfos());
        };

        for(int i = 0; i < QUANTUM; ++ i){
          currentProcess->handle();
          if(currentProcess->isBlock){
            pTable(currentProcess->getCurrentInfos());
            break;
         
          }

          if(currentProcess->isFinish()){
            auto it = std::find(processList.begin(), processList.end(), currentProcess);
            if(it != processList.end()){
              processList.erase(it);
              break;
            }
          }

        }

        if(!currentProcess->isBlock && !currentProcess->isFinish()){ 
          pTable(currentProcess->getCurrentInfos());
          currentProcess->changeState(new Ready);
          };
      }

      if(processList.empty()) break;
  }
}

int main(){
    os();
    std::cout << "Finished" << std::endl;
    return 0;   
}