package models;

public class ExecutionResult {
    String name;
    double timeMs;
    double memUsedMB;
    double cpuLoadChangePercent;
    boolean success;

    public String getName() {
        return name;
    }
    public double getTimeMs() {
        return timeMs;
    }

    public double getMemUsedMB() {
        return memUsedMB;
    }

    public double getCpuLoadChangePercent() {
        return cpuLoadChangePercent;
    }

    public boolean isSuccess() {
        return success;
    }

    public ExecutionResult(String name,double timeMs, double memUsedMB, double cpuLoadChangePercent, boolean success) {
        this.name = name;
        this.timeMs = timeMs;
        this.memUsedMB = memUsedMB;
        this.cpuLoadChangePercent = cpuLoadChangePercent;
        this.success = success;
    }
}
