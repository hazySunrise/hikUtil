package com.jimi.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于生成AGV调度任务单并发送到服务器
 * Date: Created in 2019/6/26 11:59
 * Author: ct
 */
public class AgvSchedulingTask {


    /**
     * @param url 访问RCS-2000的完整路径
     * @param userCallCode 主叫号
     * @return  服务器应答结果
     * @throws Exception
     */
    public static String request(String url, String userCallCode) throws Exception{
        if (url == null || userCallCode == null){
            throw new NullPointerException("参数不能为null");
        }
        SchedulingTaskPackage task = new SchedulingTaskPackage(null,userCallCode,null);
        return HikSender.send(url, JSONObject.toJSONString(task));
    }


    /**
     * @param url 访问RCS-2000的完整路径
     * @param start 起始点
     * @param end  终点
     * @return  服务器应答结果
     * @throws Exception
     */
    public static String request(String url, String start, String end) throws Exception{
       return request(url,null,start,end);
    }


    /**
     * @param url  访问RCS-2000的完整路径
     * @param taskTyp 任务类型 可以为null
     * @param start  起始点
     * @param end 终点
     * @return 服务器应答结果
     * @throws Exception
     */
    public static String request(String url, String taskTyp, String start, String end) throws Exception{
        if (url == null || start == null ||end ==null){
            throw new NullPointerException("参数不能为null");
        }
        List<String> userCallCodePath = new ArrayList<>();
        userCallCodePath.add(start);
        userCallCodePath.add(end);
        SchedulingTaskPackage task = new SchedulingTaskPackage(taskTyp,null,userCallCodePath);
        return HikSender.send(url, JSONObject.toJSONString(task));
    }

}
