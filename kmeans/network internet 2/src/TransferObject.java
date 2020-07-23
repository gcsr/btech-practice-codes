/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleco_client_server;

import java.io.Serializable;

/**
 *
 * @author gc
 */
public class TransferObject implements Serializable {
    int id;
    int numberOfNodes;
    int numberOfHubs;
    int numberOfSwitches;
    double networkLatency;
    double mtbf;
    String topologySructure="";
    String country="";
    String status="";
    String operation="";
    
    public TransferObject(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public int getNumberOfHubs() {
        return numberOfHubs;
    }

    public void setNumberOfHubs(int numberOfHubs) {
        this.numberOfHubs = numberOfHubs;
    }

    public int getNumberOfSwitches() {
        return numberOfSwitches;
    }

    public void setNumberOfSwitches(int numberOfSwitches) {
        this.numberOfSwitches = numberOfSwitches;
    }

    public double getNetworkLatency() {
        return networkLatency;
    }

    public void setNetworkLatency(double networkLatency) {
        this.networkLatency = networkLatency;
    }

    public double getMtbf() {
        return mtbf;
    }

    public void setMtbf(double mtbf) {
        this.mtbf = mtbf;
    }

    public String getTopologySructure() {
        return topologySructure;
    }

    public void setTopologySructure(String topologySructure) {
        this.topologySructure = topologySructure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
    
}
