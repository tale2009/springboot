package org.example.controller;

import org.example.vo.ServerCostVO;
import org.example.service.ServerCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
/**
*ServerCost对象Controller
*@author Stephanie
**/

@RestController
@RequestMapping("/servercost")
@Api(tags = "ServerCost对象")
public class ServerCostController {
@Autowired
private ServerCostService ServerCostService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ApiOperation(value = "查询",notes = "列表查询")
    public List<ServerCostVO> list()
    {
        return ServerCostService.list();
    }


    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增",notes = "新增")
    public void insert(@RequestBody ServerCostVO vo)
    {
        ServerCostService.insert(vo);
    }



    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新",notes = "更新")
    public void update(@RequestBody ServerCostVO vo)
    {
        ServerCostService.update(vo);
    }


    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询",notes = "查询单个")
    public ServerCostVO get(@PathVariable("id") String id)
    {
        return ServerCostService.get(id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除",notes = "删除")
    public void delete(@PathVariable("id") String id)
    {
        ServerCostService.delete(id);
    }
}
