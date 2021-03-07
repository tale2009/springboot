package org.example.mapper;

import org.example.vo.ServerCostVO;
import java.util.List;

/**
*ServerCost对象mapper
*@author Stephanie
**/

public interface ServerCostMapper
{
    public List<ServerCostVO> list();

    public void insert(ServerCostVO vo);

    public void update(ServerCostVO vo);

    public void delete(String id);

    public ServerCostVO get(String id);

    public void insertBatch(List<ServerCostVO> vo);
}