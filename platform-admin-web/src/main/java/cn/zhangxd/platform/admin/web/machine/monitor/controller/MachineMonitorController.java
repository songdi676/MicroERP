
package cn.zhangxd.platform.admin.web.machine.monitor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhangxd.platform.admin.web.common.controller.BaseController;
import cn.zhangxd.platform.admin.web.machine.monitor.model.MachineMonitorInfo;

/**
 * 1、<对类的功能的概要描述。通过阅读概要描述，使用者可以了解类的整体功能，以便初步判断是否需要使用这个类。>
 * 2、<对类的功能的详细描述。对类用到的关键技术、原理等进行详细的描述。如有必要，请使用简单样例进行说明。>
 * 3、<使用该类需要遵循该类的那些具体约束进行描述。>
 * 
 * @author sWX239227
 * @version UniSTAR CPI V001
 * @see <相关的类或者类的某个方法>
 * @since UniSTAR CPI V001
 */
@Validated
@RestController
@RequestMapping( "/machine" )
public class MachineMonitorController extends BaseController
{

    /**
     * Get map.
     *
     * @return the map
     */
    @PreAuthorize( "isAuthenticated()" )
    @GetMapping( value = "monitor" )
    public List<MachineMonitorInfo> getMachineMonitorInfo()
    {
        // 1.获取机器列表
        List<String> machineList = Arrays.asList( "#M001", "#M002", "#M003",
                "#M004", "#M005", "#M006" );

        List<MachineMonitorInfo> result = new ArrayList<MachineMonitorInfo>(
                machineList.size() );

        MachineMonitorInfo monitorInfo;
        for ( String machineNumber : machineList )
        {
            Random random = new Random();
            monitorInfo = new MachineMonitorInfo( machineNumber,
                    random.nextInt( 100 ) + 1, random.nextInt( 2 ),
                    random.nextInt( 500 ) + 500 );
            result.add( monitorInfo );
        }
        return result;
    }

}
