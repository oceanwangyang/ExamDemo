package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.*;

/*
 *类名和方法不能修改
 */
public class Schedule
{
    private List<Integer> nodes = new ArrayList<Integer>();

    private List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();

    private Map<Integer, Integer> consumptions = new HashMap<Integer, Integer>();

    public int init()
    {
        // TODOED 方法未实现
        boolean flag = false;
        if (getNodes() != null && getNodes().size() > 0)
        {
            List<Integer> states = new ArrayList<Integer>();
            for (Integer node : getNodes())
            {
                int state = unregisterNode(node);

                states.add(state);
            }
            flag = states.contains(ReturnCodeKeys.E006);
        }
        if (getTaskInfos() != null && getTaskInfos().size() > 0)
        {
            List<Integer> states = new ArrayList<Integer>();
            for (TaskInfo taskInfo : getTaskInfos())
            {
                int state = deleteTask(taskInfo.getTaskId());

                states.add(state);
            }
            flag = flag && states.contains(ReturnCodeKeys.E006);
        }
        if (!flag)
        {
            //TODO:待实现
        }
        setNodes(new ArrayList<Integer>());
        setTaskInfos(new ArrayList<TaskInfo>());
        return ReturnCodeKeys.E001;
    }

    public int registerNode(int nodeId)
    {
        // TODOED 方法未实现
        if (nodeId < 0)
        {
            return ReturnCodeKeys.E004;//服务节点编号非法
        }
        if (getNodes().contains(nodeId))
        {
            return ReturnCodeKeys.E005;//:服务节点已注册
        }
        if (!getNodes().add(nodeId))
        {
            //TODO:待实现
        }
        return ReturnCodeKeys.E003;//服务节点注册成功;
    }

    public int unregisterNode(int nodeId)
    {
        // TODOED 方法未实现
        if (nodeId < 0)
        {
            return ReturnCodeKeys.E004;//服务节点编号非法
        }
        if (!getNodes().contains(nodeId))
        {
            return ReturnCodeKeys.E007;//服务节点不存在
        }

        moveTaskInfosByNodeId(nodeId);

        return ReturnCodeKeys.E006;//服务节点注销成功
    }

    public int addTask(int taskId, int consumption)
    {
        // TODOED 方法未实现
        if (taskId < 1)
        {
            return ReturnCodeKeys.E009;//:任务编号非法;
        }

        if (containsByTaskId(taskId))
        {
            return ReturnCodeKeys.E010;//任务已添加
        }
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setNodeId(-1);
        taskInfo.setTaskId(taskId);
        getTaskInfos().add(taskInfo);

        getConsumptions().put(taskId, consumption);
        return ReturnCodeKeys.E008;
    }

    public int deleteTask(int taskId)
    {
        // TODOED 方法未实现
        if (taskId < 1)
        {
            return ReturnCodeKeys.E009;//:任务编号非法;
        }

        if (!containsByTaskId(taskId))
        {
            return ReturnCodeKeys.E012;//任务不存在
        }
        return ReturnCodeKeys.E011;//任务删除成功
    }

    public int scheduleTask(int threshold)
    {
        // TODO 方法未实现
        if (threshold < 1)
        {
            return ReturnCodeKeys.E002;
        }

        return ReturnCodeKeys.E000;
    }

    public int queryTaskStatus(List<TaskInfo> tasks)
    {
        // TODOED 方法未实现
        if (tasks == null)
        {
            return ReturnCodeKeys.E016;//:参数列表非法
        }
        Collections.sort(taskInfos, new Comparator<TaskInfo>()
        {
            public int compare(TaskInfo o1, TaskInfo o2)
            {
                if (o1.getTaskId() > o2.getTaskId())
                {
                    return 1;
                }
                else if (o1.getTaskId() == o2.getTaskId())
                {
                    return 0;
                }
                return -1;
            }
        });
        tasks = taskInfos;
        System.out.println(tasks);
        return ReturnCodeKeys.E015;
    }

    private void moveTaskInfosByNodeId(int nodeId)
    {
        List<TaskInfo> taskInfosByNodeId = new ArrayList<TaskInfo>();

        for (TaskInfo taskInfo : getTaskInfos())
        {
            if (taskInfo.getNodeId() == nodeId)
            {
                taskInfo.setNodeId(-1);
            }
        }
    }

    private boolean containsByTaskId(int taskId)
    {
        for (TaskInfo taskInfo : getTaskInfos())
        {
            if (taskInfo.getTaskId() == taskId)
            {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<Integer> nodes)
    {
        this.nodes = nodes;
    }

    public List<TaskInfo> getTaskInfos()
    {
        return taskInfos;
    }

    public void setTaskInfos(List<TaskInfo> taskInfos)
    {
        this.taskInfos = taskInfos;
    }

    public Map<Integer, Integer> getConsumptions()
    {
        return consumptions;
    }

    public void setConsumptions(Map<Integer, Integer> consumptions)
    {
        this.consumptions = consumptions;
    }
}
