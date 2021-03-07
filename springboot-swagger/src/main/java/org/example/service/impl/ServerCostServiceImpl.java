package org.example.service.impl;

import org.example.vo.ServerCostVO;
import org.example.service.ServerCostService;
import org.example.mapper.ServerCostMapper;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;


import java.util.List;

/**
*
*
*
*@author Stephanie
**/

@Service
public class ServerCostServiceImpl implements ServerCostService
{
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ServerCostServiceImpl.class);


    private ServerCostMapper ServerCostmapper;

    public List<ServerCostVO> list()
    {
        return ServerCostmapper.list();
    }

    public void insert(ServerCostVO vo)
    {
        ServerCostmapper.insert(vo);
    }

    public void update(ServerCostVO vo){
        ServerCostmapper.update(vo);
    }

    public void delete(String id)
    {
        ServerCostmapper.delete(id);
    }

    public ServerCostVO get(String id)
    {
        return ServerCostmapper.get(id);
    }

    public void insertBatch(List<ServerCostVO> serverCostVOList)
    {
        ServerCostmapper.insertBatch(serverCostVOList);
    }
}