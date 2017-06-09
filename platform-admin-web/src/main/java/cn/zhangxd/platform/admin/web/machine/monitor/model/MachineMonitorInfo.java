
package cn.zhangxd.platform.admin.web.machine.monitor.model;

public class MachineMonitorInfo
{
    private String machineNumber;
    private int efficiency;
    private int status;
    private int speed;

    public MachineMonitorInfo( String machineNumber,int efficiency,int status,
            int speed )
    {
        super();
        this.machineNumber = machineNumber;
        this.efficiency = efficiency;
        this.status = status;
        this.speed = speed;
    }

    public String getMachineNumber()
    {
        return machineNumber;
    }

    public void setMachineNumber( String machineNumber )
    {
        this.machineNumber = machineNumber;
    }

    public int getEfficiency()
    {
        return efficiency;
    }

    public void setEfficiency( int efficiency )
    {
        this.efficiency = efficiency;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus( int status )
    {
        this.status = status;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed( int speed )
    {
        this.speed = speed;
    }

}
