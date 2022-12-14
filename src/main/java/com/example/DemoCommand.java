package com.example;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.Region;
import oracle.nosql.driver.iam.SignatureProvider;
import oracle.nosql.driver.ops.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command(name = "demo", description = "...",
        mixinStandardHelpOptions = true)
public class DemoCommand implements Runnable {

    private static NoSQLHandle getNoSQLConnection() {

        SignatureProvider authProvider = SignatureProvider.createWithInstancePrincipal();
        NoSQLHandleConfig config = new NoSQLHandleConfig(System.getenv("OCI_REGION"), authProvider);
        config.setDefaultCompartment(System.getenv("NOSQL_COMP_ID")) ;
        System.out.println("Application Running");
        System.out.println(System.getenv("OCI_REGION"));
        System.out.println(System.getenv("NOSQL_COMP_ID"));
        return( NoSQLHandleFactory.createNoSQLHandle(config) );
    }
    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(DemoCommand.class, args);
    }
    public void run() {

       MachineId machineId = new MachineId();
       machineId.setName( "Computer");
       machineId.setVersion( "Lenovo");

       HashMap<String, String> settings = new HashMap<String, String>();
       settings.put("keyboard", "French");
       settings.put("size", "4");
       settings.put("color", "Grey");

       ArrayList<Transition> transitions = new ArrayList<Transition>();
       Transition trans = new Transition();
       trans.setSource("source");
       trans.setDestination("destination");
       trans.setAction("action");
       transitions.add(trans);
       transitions.add(trans);
       transitions.add(trans);

       // Create a machine instance
       Machine machine = new Machine();
       machine.setMachineId(machineId);
       machine.setSetting (settings);
       machine.setTransitions (transitions);

        try {
            NoSQLHandle client = getNoSQLConnection();
            MachineRepo machineRepo = new MachineRepoImpl(client);
            //machineRepo.createTable();
            machineRepo.save(machine);
            Machine result = machineRepo.findById( machineId);
            System.out.println("------------- machineRepo.findById ---------------------------");
            System.out.println(result);
            List<Machine> machines = machineRepo.findAll();
            System.out.println("------------- machineRepo.findById ---------------------------");
            for (Machine res : machines) {
                    System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            //handle.close();
        }



        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
        System.exit(0);
    }
}
