package org.example.app;

import java.util.List;

public interface MachineRepo {

    //void createTable();

    Machine save(Machine machine);
    Machine findById(MachineId machineId);
    List<Machine> findAll() ;

}
